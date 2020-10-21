/**
 * 
 */
package br.senai.sc.web;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.RowEditEvent;
import org.primefaces.event.SelectEvent;

import br.senai.sc.models.Curso;
import br.senai.sc.services.DateServices;
import br.senai.sc.services.CursoService;

/**
 * @author ismael
 *
 */
@Named("cursoBean")
@RequestScoped
public class CursoBean {

	@Inject
	private CursoService cursoService;

	private Curso cursoSelecionado;

	List<Curso> cursos;

	public CursoBean() {
	}

	@PostConstruct
	public void inicializar() {
		// Iniciamos as variaveis
		this.cursos = cursoService.findAll();
		this.cursoSelecionado = new Curso();

	}

	public void editListener(RowEditEvent event) {
		cursoSelecionado = (Curso) event.getObject();
	}

	public void onRowSelect(SelectEvent<Curso> event) {
		cursoSelecionado = (Curso) event.getObject();
	}

	public Curso getCursoSelecionada() {
		return cursoSelecionado;
	}

	public void setCursoSelecionada(Curso cursoSelecionada) {
		this.cursoSelecionado = cursoSelecionada;
	}

	public List<Curso> getCursos() {
		return cursos;
	}

	public void setCursos(List<Curso> cursos) {
		this.cursos = cursos;
	}

	public void salvarCurso() {

		boolean validacoesPendentes = false;
		String[] palavras = cursoSelecionado.getNome().split(" ");

		if ((cursoSelecionado.getNome() == null) || (cursoSelecionado.getNome().length() < 10)) {

			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN,
					"Nome deve conter pelo menos 10 caracteres.", "Nome");
			FacesContext.getCurrentInstance().addMessage("inputTextNome", message);
			validacoesPendentes = true;

		}

		for (int i = 0; i < palavras.length; i++) {
			if ( (palavras[i].length()>3)&&(!palavras[i].startsWith(palavras[i].substring(0, 1).toUpperCase()))) {
				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN,
						"Nome e sobrenome deve iniciar com letra maiúscula.", "Nome");
				FacesContext.getCurrentInstance().addMessage("inputTextNome", message);
				validacoesPendentes = true;
				break;
			}

		}

		if (!validacoesPendentes) {

			if (cursoSelecionado.getIdCurso() != null) {
				this.cursoService.update(cursoSelecionado);
			} else {
				this.cursoService.save(cursoSelecionado);
				this.cursos.add(cursoSelecionado);
			}
			this.cursoSelecionado = null;
		}

	}

	public void deletarCurso() {
		this.cursoService.delete(cursoSelecionado);
		this.cursos.remove(this.cursoSelecionado);
		this.cursoSelecionado = null;
	}

	public void reiniciarCursoSelecionada() {
		this.cursoSelecionado = new Curso();
	}

}
