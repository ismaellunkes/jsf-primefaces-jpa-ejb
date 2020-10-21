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
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
public class Curso {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idCurso;
	private String nome;
	private Integer nroVagas;
	private Date dataInicio;
	private Date dataFim;
	private Integer idadeMinima;
	
	@OneToMany(mappedBy = "curso", cascade = CascadeType.ALL  )
	private List<Matricula> matriculas = new ArrayList<>();
		
	public Curso() {
	}
	
	public Curso(String nome, Integer nroVagas, Date dataInicio, Date dataFim, Integer idadeMinima) {
		super();
		this.nome = nome;
		this.nroVagas = nroVagas;
		this.dataInicio = dataInicio;
		this.dataFim = dataFim;
		this.idadeMinima = idadeMinima;
	}

	public Curso(Long id) {
		super();
		this.idCurso = id;		
	}
	
	/**
	 * @return the idCurso
	 */
	public Long getIdCurso() {
		return idCurso;
	}

	/**
	 * @param idCurso the idCurso to set
	 */
	public void setIdCurso(Long idCurso) {
		this.idCurso = idCurso;
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
	 * @return the nroVagas
	 */
	public Integer getNroVagas() {
		return nroVagas;
	}

	/**
	 * @param nroVagas the nroVagas to set
	 */
	public void setNroVagas(Integer nroVagas) {
		this.nroVagas = nroVagas;
	}

	/**
	 * @return the dataInicio
	 */
	public Date getDataInicio() {
		return dataInicio;
	}

	/**
	 * @param dataInicio the dataInicio to set
	 */
	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}

	/**
	 * @return the dataFim
	 */
	public Date getDataFim() {
		return dataFim;
	}

	/**
	 * @param dataFim the dataFim to set
	 */
	public void setDataFim(Date dataFim) {
		this.dataFim = dataFim;
	}

	/**
	 * @return the idadeMinima
	 */
	public Integer getIdadeMinima() {
		return idadeMinima;
	}

	/**
	 * @param idadeMinima the idadeMinima to set
	 */
	public void setIdadeMinima(Integer idadeMinima) {
		this.idadeMinima = idadeMinima;
	}

	@Override
	public String toString() {
		return nome + " | Nro. Vagas: "+nroVagas;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idCurso == null) ? 0 : idCurso.hashCode());
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
		Curso other = (Curso) obj;
		if (idCurso == null) {
			if (other.idCurso != null)
				return false;
		} else if (!idCurso.equals(other.idCurso))
			return false;
		return true;
	}
	
}
