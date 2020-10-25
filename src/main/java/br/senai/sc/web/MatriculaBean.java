/**
 * 
 */
package br.senai.sc.web;

import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.event.RowEditEvent;
import org.primefaces.event.SelectEvent;
import br.senai.sc.models.Matricula;
import br.senai.sc.services.MatriculaService;


/**
 * @author ismael
 *
 */
@Named("matriculaBean")
@RequestScoped
public class MatriculaBean {

	@Inject
	private MatriculaService matriculaService;

	private Matricula matriculaSelecionada;

	List<Matricula> matriculas;

	public MatriculaBean() {
	}

	@PostConstruct
	public void inicializar() {
		// Iniciamos as variaveis
		this.matriculas = matriculaService.findAll();
		this.matriculaSelecionada = new Matricula();

	}

	public void editListener(RowEditEvent event) {
		matriculaSelecionada = (Matricula) event.getObject();
	}

	public void onRowSelect(SelectEvent<Matricula> event) {
		matriculaSelecionada = (Matricula) event.getObject();
	}

	public Matricula getMatriculaSelecionada() {
		return matriculaSelecionada;
	}

	public void setMatriculaSelecionada(Matricula matriculaSelecionada) {
		this.matriculaSelecionada = matriculaSelecionada;
	}

	public List<Matricula> getMatriculas() {
		return matriculas;
	}

	public void setMatriculas(List<Matricula> matriculas) {
		this.matriculas = matriculas;
	}
	
	public void getMatriculasByCurso() {
		if (matriculaSelecionada != null && !matriculaSelecionada.getCurso().trim().isEmpty()) {
			this.matriculas = matriculaService.findByCurso(matriculaSelecionada);
		}
	}

	public void salvarMatricula() {

		boolean validacoesPendentes = false;

		if (matriculaSelecionada.getPessoa() != null && matriculaSelecionada.getPessoa().getIdPessoa() != null) {

			//Valida se nome do curso contem pelo menos 5 caracteres
			if ((matriculaSelecionada.getCurso() == null) || (matriculaSelecionada.getCurso().length() < 5)) {

				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN,
						"Nome do curso deve conter pelo menos 5 caracteres.", "Curso");
				FacesContext.getCurrentInstance().addMessage("inputTextCurso", message);
				validacoesPendentes = true;

			}
			
			//valida se aluno já está matriculado no curso
			if (matriculaService.alunoMatAtiva(matriculaSelecionada)) {

				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN,
						"Aluno já está matriculado neste curso.", "Datas");
				FacesContext.getCurrentInstance().addMessage("aluno", message);
				validacoesPendentes = true;

			}
			
			//valida se data da matricula é hoje ou antes
			if (!matriculaService.dataMatValida(matriculaSelecionada)) {

				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN,
						"Data da matrícula invalida.", "Datas");
				FacesContext.getCurrentInstance().addMessage("dataMat", message);
				validacoesPendentes = true;

			}
			
		}
		
		if (!validacoesPendentes)
		{
			if (matriculaSelecionada.getIdMatricula() != null) {
				this.matriculas.remove(matriculaSelecionada);
				this.matriculaService.update(matriculaSelecionada);
				this.matriculas.add(matriculaSelecionada);				
			} else {
				this.matriculaService.save(matriculaSelecionada);
				this.matriculas.add(matriculaSelecionada);
			}
			this.matriculaSelecionada = null;
		}
	}

	public void deletarMatricula() {
		this.matriculaService.delete(matriculaSelecionada);
		this.matriculas.remove(this.matriculaSelecionada);
		this.matriculaSelecionada = null;
	}

	public void reiniciarMatriculaSelecionada() {
		this.matriculaSelecionada = new Matricula();
	}

}
