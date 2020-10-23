package br.senai.sc.services;

import java.util.List;

import javax.ejb.Local;

import br.senai.sc.models.Curso;
import br.senai.sc.models.Matricula;
import br.senai.sc.models.Pessoa;

@Local
public interface MatriculaService {

	public List<Matricula> findAll();
	
	public Matricula findById(Long id);

	public void save(Matricula matricula);

	public void update(Matricula matricula);

	public void delete(Matricula matricula);

	public Matricula findByNome(String nome);	
	
	public List<Matricula> findByCurso(Matricula matricula);

	public boolean possuiMatriculaCurso(Matricula matricula);
	
	public boolean possuiIdadeMinima(Matricula matricula);
	
	public Integer vagasDisponiveis(Matricula matricula);

	public boolean possuiVagas(Matricula matricula);
	
	public boolean dataMatriculaAntesInicioCurso(Matricula matricula);

}
