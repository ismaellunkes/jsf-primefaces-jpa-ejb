package br.senai.sc.daos;

import java.util.List;

import br.senai.sc.models.Curso;
import br.senai.sc.models.Matricula;
import br.senai.sc.models.Pessoa;

public interface MatriculaDao {

	public List<Matricula> findAll();
	
	public Matricula findById(Long id);
	
	public void save(Matricula matricula);
	
	public void update(Matricula matricula);
	
	public void delete(Matricula matricula);
	
	public Matricula findByNome(String nome);
	
	public List<Matricula> findByCurso(Matricula matricula);

	public List<Matricula> findByAlunoCurso(Pessoa pessoa, Curso curso);	
	
	public boolean possuiMatriculaCurso(Pessoa pessoa, Curso curso);	
	
	
	
}
