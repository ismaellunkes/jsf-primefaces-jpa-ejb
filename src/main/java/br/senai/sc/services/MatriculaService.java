package br.senai.sc.services;

import java.util.List;

import javax.ejb.Local;
import br.senai.sc.models.Matricula;

@Local
public interface MatriculaService {

	public List<Matricula> findAll();
	
	public Matricula findById(Long id);

	public void save(Matricula matricula);

	public void update(Matricula matricula);

	public void delete(Matricula matricula);

	public Matricula findByNome(String nome);	
	
	public List<Matricula> findByAlunoCurso(Matricula matricula);
	
	public List<Matricula> findByCurso(Matricula matricula);
	
	public boolean dataMatValida(Matricula matricula);
	
	public boolean alunoMatAtiva(Matricula matricula);

}
