package br.senai.sc.services;

import java.util.List;

import javax.ejb.Local;

import br.senai.sc.models.Curso;

@Local
public interface CursoService {

	public List<Curso> findAll();
	
	public Curso findById(Long id);

	public void save(Curso curso);

	public void update(Curso curso);

	public void delete(Curso curso);

	public Curso findByNome(String nome);	

}
