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

import br.senai.sc.models.Pessoa;
import br.senai.sc.services.PessoaService;

/**
 * @author ismael
 *
 */
@Named("pessoaBean")
@RequestScoped
public class PessoaBean {

	@Inject
	private PessoaService pessoaService;

	private Pessoa pessoaSelecionada;

	List<Pessoa> pessoas;

	public PessoaBean() {
	}

	@PostConstruct
	public void inicializar() {
		// Iniciamos as variaveis
		this.pessoas = pessoaService.findAll();
		this.pessoaSelecionada = new Pessoa();

	}

	public void editListener(RowEditEvent event) {
		pessoaSelecionada = (Pessoa) event.getObject();
	}

	public void onRowSelect(SelectEvent<Pessoa> event) {
		pessoaSelecionada = (Pessoa) event.getObject();
	}

	public Pessoa getPessoaSelecionada() {
		return pessoaSelecionada;
	}

	public void setPessoaSelecionada(Pessoa pessoaSelecionada) {
		this.pessoaSelecionada = pessoaSelecionada;
	}

	public List<Pessoa> getPessoas() {
		return pessoas;
	}

	public void setPessoas(List<Pessoa> pessoas) {
		this.pessoas = pessoas;
	}

	public void getPessoasByName() {
		if (pessoaSelecionada != null && !pessoaSelecionada.getNome().trim().isEmpty()) {
			this.pessoas = pessoaService.findByNome(pessoaSelecionada);
		}
	}
	
	public void salvarPessoa() {

		boolean validacoesPendentes = false;

		if ((pessoaSelecionada.getNome() == null) || (pessoaSelecionada.getNome().length() < 10)) {

			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN,
					"Nome deve conter pelo menos 10 caracteres.", "Nome");
			FacesContext.getCurrentInstance().addMessage("inputTextNome", message);
			validacoesPendentes = true;

		}

		if (!pessoaService.dataNascValida(pessoaSelecionada)) {
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Data de nascimento inválida.", "Data");
			FacesContext.getCurrentInstance().addMessage("inputTextDataNasc", message);
			validacoesPendentes = true;
		}

		if (!validacoesPendentes) {
			this.pessoaService.save(pessoaSelecionada);
			//this.pessoas.add(pessoaSelecionada);
			inicializar();
			this.pessoaSelecionada = null;
		}

	}

	public void deletarPessoa() {
		this.pessoaService.delete(pessoaSelecionada);
		this.pessoas.remove(this.pessoaSelecionada);
		this.pessoaSelecionada = null;
	}

	public void reiniciarPessoaSelecionada() {
		this.pessoaSelecionada = new Pessoa();
	}

}
