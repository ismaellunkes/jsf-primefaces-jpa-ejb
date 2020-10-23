package br.senai.sc.services.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import br.senai.sc.daos.CursoDao;
import br.senai.sc.models.Curso;
import br.senai.sc.services.CursoService;

@Stateless
public class CursoServiceImpl implements CursoService {

	@Inject
	CursoDao dao;

	public CursoServiceImpl() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public Curso findById(Long id) {
		return dao.findById(id);
	}

	@Override
	public List<Curso> findAll() {
		return dao.findAll();
	}

	@Override
	public Curso findByNome(String nome) {
		return dao.findByNome(nome);
	}
	
	@Override
	public void save(Curso curso) {
		dao.save(curso);
	}

	@Override
	public void update(Curso curso) {
		dao.update(curso);
	}

	@Override
	public void delete(Curso curso) {
		dao.delete(curso);
	}

	@Override
	public Integer vagasDisponiveis(Curso curso, Integer matriculados) {		
		return dao.findById(curso.getIdCurso()).getNroVagas() - matriculados;
	}

}
