package br.senai.sc.services.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import br.senai.sc.daos.PessoaDao;
import br.senai.sc.models.Pessoa;
import br.senai.sc.services.PessoaService;

@Stateless
public class PessoaServiceImpl implements PessoaService {

	@Inject
	PessoaDao dao;

	public PessoaServiceImpl() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public Pessoa findById(Long id) {
		return dao.findById(id);
	}

	@Override
	public List<Pessoa> findAll() {
		return dao.findAll();
	}

	@Override
	public Pessoa findByNome(String nome) {
		return dao.findByNome(nome);
	}
	
	@Override
	public void save(Pessoa pessoa) {
		dao.save(pessoa);
	}

	@Override
	public void update(Pessoa pessoa) {
		dao.update(pessoa);
	}

	@Override
	public void delete(Pessoa pessoa) {
		dao.delete(pessoa);
	}

}
