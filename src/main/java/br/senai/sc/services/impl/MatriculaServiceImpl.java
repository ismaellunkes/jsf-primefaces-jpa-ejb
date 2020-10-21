package br.senai.sc.services.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import br.senai.sc.daos.MatriculaDao;
import br.senai.sc.models.Matricula;
import br.senai.sc.models.Pessoa;
import br.senai.sc.services.MatriculaService;

@Stateless
public class MatriculaServiceImpl implements MatriculaService {

	@Inject
	MatriculaDao dao;

	public MatriculaServiceImpl() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public Matricula findById(Long id) {
		return dao.findById(id);
	}

	@Override
	public List<Matricula> findAll() {
		return dao.findAll();
	}

	@Override
	public Matricula findByNome(String nome) {
		return dao.findByNome(nome);
	}
	
	@Override
	public void save(Matricula matricula) {
		dao.save(matricula);
	}

	@Override
	public void update(Matricula matricula) {
		dao.update(matricula);
	}

	@Override
	public void delete(Matricula matricula) {
		dao.delete(matricula);
	}

	@Override
	public List<Matricula> findByCurso(Matricula matricula) {
		return dao.findByCurso(matricula);
	}

	@Override
	public List<Matricula> findByAluno(Pessoa pessoa) {
		return dao.findByAluno(pessoa);
	}

}
