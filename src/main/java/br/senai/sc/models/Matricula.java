package br.senai.sc.models;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Matricula {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idMatricula;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name = "id_aluno", nullable = false)
	private Pessoa pessoa;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name = "id_curso", nullable = false)
	private Curso curso;
		
	private Date dataMatricula;
	
	public Matricula() {
	}

	
	public Matricula(Pessoa pessoa, Curso curso, Date dataMatricula) {
		super();
		this.pessoa = pessoa;
		this.curso = curso;
		this.dataMatricula = dataMatricula;
	}


	/**
	 * @return the idMatricula
	 */
	public Long getIdMatricula() {
		return idMatricula;
	}

	/**
	 * @param idMatricula the idMatricula to set
	 */
	public void setIdMatricula(Long idMatricula) {
		this.idMatricula = idMatricula;
	}

	/**
	 * @return the pessoa
	 */
	public Pessoa getPessoa() {
		return pessoa;
	}


	/**
	 * @param pessoa the pessoa to set
	 */
	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}


	/**
	 * @return the curso
	 */
	public Curso getCurso() {
		return curso;
	}


	/**
	 * @param curso the curso to set
	 */
	public void setCurso(Curso curso) {
		this.curso = curso;
	}


	/**
	 * @return the dataMatricula
	 */
	public Date getDataMatricula() {
		return dataMatricula;
	}


	/**
	 * @param dataMatricula the dataMatricula to set
	 */
	public void setDataMatricula(Date dataMatricula) {
		this.dataMatricula = dataMatricula;
	}
	
	@Override
	public String toString() {
		return "Matricula [idMatricula=" + idMatricula + "]";
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idMatricula == null) ? 0 : idMatricula.hashCode());
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
		Matricula other = (Matricula) obj;
		if (idMatricula == null) {
			if (other.idMatricula != null)
				return false;
		} else if (!idMatricula.equals(other.idMatricula))
			return false;
		return true;
	}
	
	
	
}
