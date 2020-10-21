package br.senai.sc.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Pessoa {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idPessoa;
	private String nome;
	private String cpf;
	private Date dataNasc;

	@OneToMany(mappedBy = "pessoa", cascade = CascadeType.ALL)
	private List<Matricula> matriculas = new ArrayList<>();

	public Pessoa() {
	}

	public Pessoa(Long id) {
		super();
		this.idPessoa = id;
	}

	public Pessoa(String nome, String cpf, Date dataNasc) {
		super();
		this.nome = nome;
		this.cpf = cpf;
		this.dataNasc = dataNasc;
	}

	/**
	 * @return the idPessoa
	 */
	public Long getIdPessoa() {
		return idPessoa;
	}

	/**
	 * @param idPessoa the idPessoa to set
	 */
	public void setIdPessoa(Long idPessoa) {
		this.idPessoa = idPessoa;
	}

	/**
	 * @return the nome
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * @param nome the nome to set
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * @return the cpf
	 */
	public String getCpf() {
		return cpf;
	}

	/**
	 * @param cpf the cpf to set
	 */
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	/**
	 * @return the dataNasc
	 */
	public Date getDataNasc() {
		return dataNasc;
	}

	/**
	 * @param dataNasc the dataNasc to set
	 */
	public void setDataNasc(Date dataNasc) {
		this.dataNasc = dataNasc;
	}

	@Override
	public String toString() {
		return nome + " | CPF: " + cpf;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idPessoa == null) ? 0 : idPessoa.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pessoa other = (Pessoa) obj;
		if (idPessoa == null) {
			if (other.idPessoa != null)
				return false;
		} else if (!idPessoa.equals(other.idPessoa))
			return false;
		return true;
	}
	
	

	
	
}
