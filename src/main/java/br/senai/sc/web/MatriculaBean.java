/**
 * 
 */
package br.senai.sc.web;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.RowEditEvent;
import org.primefaces.event.SelectEvent;

import br.senai.sc.models.Curso;
import br.senai.sc.models.Matricula;
import br.senai.sc.models.Pessoa;
import br.senai.sc.services.CursoService;
import br.senai.sc.services.DateServices;
import br.senai.sc.services.MatriculaService;
import br.senai.sc.services.PessoaService;
import br.senai.sc.services.impl.PessoaServiceImpl;

/**
 * @author ismael
 *
 */
@Named("matriculaBean")
@RequestScoped
public class MatriculaBean {

	@Inject
	private MatriculaService matriculaService;

	@Inject
	private CursoService cursoService;

	@Inject
	private PessoaService pessoaService;

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

	public void salvarMatricula() {

		boolean validacoesPendentes = false;

		if (matriculaSelecionada.getCurso() != null && matriculaSelecionada.getCurso().getIdCurso() != null
				&& matriculaSelecionada.getPessoa() != null && matriculaSelecionada.getPessoa().getIdPessoa() != null) {

			Pessoa pessoa = pessoaService.findById((matriculaSelecionada.getPessoa().getIdPessoa()));

			Curso curso = cursoService.findById((matriculaSelecionada.getCurso().getIdCurso()));

			if (matriculaService.findByCurso(matriculaSelecionada).size() > curso.getNroVagas()) {

				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Número de vagas excedido.",
						"Vagas");
				FacesContext.getCurrentInstance().addMessage("optOneTextCurso", message);
				validacoesPendentes = true;

			}

			if (matriculaSelecionada.getDataMatricula().after(curso.getDataInicio())) {

				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN,
						"Data da matrícula não pode ser após inicio do curso.", "Datas");
				FacesContext.getCurrentInstance().addMessage("dataMat", message);
				validacoesPendentes = true;

			}

			/// Validar se aluno não está matriculado no curso
			if (pessoa != null && pessoa.getIdPessoa() != null && matriculaService.findByAluno(pessoa).size() > 0) {

				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN,
						"Aluno possui matrícula ativa em outro curso.", "Matricula Ativa");
				FacesContext.getCurrentInstance().addMessage("optOneTextAluno", message);
				validacoesPendentes = true;

			}

			/// Validar se aluno tem idade minima para realizar o curso
			if (curso != null && curso.getIdCurso() != null && pessoa != null && pessoa.getIdPessoa() != null) {

//				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN,
//						"Aluno não possui idade mínima para matricula neste curso.", "Idade minima.");
//				FacesContext.getCurrentInstance().addMessage("optOneTextAluno", message);
//				validacoesPendentes = true;

			}
		}
		
		if (!validacoesPendentes)

		{

			if (matriculaSelecionada.getIdMatricula() != null) {
				this.matriculaService.update(matriculaSelecionada);
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

	public void findAlunoByMatricula() {

	}

}
