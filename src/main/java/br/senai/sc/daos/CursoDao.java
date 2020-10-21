package br.senai.sc.daos;

import java.util.List;

import br.senai.sc.models.Curso;

public interface CursoDao {

	public List<Curso> findAll();
	
	public Curso findById(Long id);
	
	public void save(Curso curso);
	
	public void update(Curso curso);
	
	public void delete(Curso curso);
	
	public Curso findByNome(String nome);
	
}
