package br.senai.sc.services.impl;

import java.time.Instant;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.print.attribute.standard.DateTimeAtCompleted;

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


	@Override
	public Integer idadePessoa(Pessoa pessoa) {
		int dayInstant = LocalDate.now().getDayOfMonth();
		int monthInstant = LocalDate.now().getMonthValue();
		int yearInstant = LocalDate.now().getYear();
								
		Date birthPessoa = dao.findById(pessoa.getIdPessoa()).getDataNasc();
		
		if ((birthPessoa.getMonth()<monthInstant) ||
				(birthPessoa.getMonth()==monthInstant && birthPessoa.getDay() <= dayInstant)){
			return yearInstant-(birthPessoa.getYear()+1900);
		}else {
			return yearInstant-(birthPessoa.getYear()+1900)-1;
		}			
	}

}
