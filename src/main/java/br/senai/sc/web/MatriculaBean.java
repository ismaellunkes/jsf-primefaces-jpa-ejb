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

	public void salvarMatricula() {

		boolean validacoesPendentes = false;

		if (matriculaSelecionada.getCurso() != null && matriculaSelecionada.getCurso().getIdCurso() != null
				&& matriculaSelecionada.getPessoa() != null && matriculaSelecionada.getPessoa().getIdPessoa() != null) {

			///Valida se curso possui vagas
			if (!matriculaService.possuiVagas(matriculaSelecionada)) {

				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Número de vagas excedido.",
						"Vagas");
				FacesContext.getCurrentInstance().addMessage("optOneTextCurso", message);
				validacoesPendentes = true;

			}

			//valida se data da matricula é antes do inicio do curso
			if (!matriculaService.dataMatriculaAntesInicioCurso(matriculaSelecionada)) {

				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN,
						"Data da matrícula não pode ser após inicio do curso.", "Datas");
				FacesContext.getCurrentInstance().addMessage("dataMat", message);
				validacoesPendentes = true;

			}
	
			/// Valida se aluno não está matriculado no curso
			if (matriculaService.possuiMatriculaCurso(matriculaSelecionada)) {

				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN,
						"Aluno possui matrícula ativa no curso.", "Matricula Ativa");
				FacesContext.getCurrentInstance().addMessage("optOneTextAluno", message);
				validacoesPendentes = true;

			}

			/// Validar se aluno tem idade minima para realizar o curso
			if (!matriculaService.possuiIdadeMinima(matriculaSelecionada)) {
				
				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN,
						"Aluno não possui idade mínima para matricula neste curso.", "Idade minima.");
				FacesContext.getCurrentInstance().addMessage("optOneTextAluno", message);
				validacoesPendentes = true;

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
