package br.senai.sc.services.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import br.senai.sc.daos.MatriculaDao;
import br.senai.sc.models.Curso;
import br.senai.sc.models.Matricula;
import br.senai.sc.models.Pessoa;
import br.senai.sc.services.CursoService;
import br.senai.sc.services.MatriculaService;
import br.senai.sc.services.PessoaService;

@Stateless
public class MatriculaServiceImpl implements MatriculaService {

	@Inject
	MatriculaDao dao;
	
	@Inject
	CursoService cursoService;
	
	@Inject
	PessoaService pessoaService;
	
	
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
	public boolean possuiMatriculaCurso(Matricula matricula) {
		return dao.possuiMatriculaCurso(matricula.getPessoa(), matricula.getCurso());
	}

	@Override
	public boolean possuiIdadeMinima(Matricula matricula) {
		return cursoService.findById(matricula.getCurso().getIdCurso()).getIdadeMinima() <= pessoaService.idadePessoa(matricula.getPessoa());
	}

	@Override
	public Integer vagasDisponiveis(Matricula matricula) {		
		return cursoService.vagasDisponiveis(matricula.getCurso(),  dao.findByCurso(matricula).size());
	}
	
	@Override
	public boolean possuiVagas(Matricula matricula) {		
		return vagasDisponiveis(matricula)>0?true:false;
	}

	@Override
	public boolean dataMatriculaAntesInicioCurso(Matricula matricula) {		
		return  matricula.getDataMatricula().before(cursoService.findById(matricula.getCurso().getIdCurso()).getDataInicio())?true:false;		
	}
	
}
