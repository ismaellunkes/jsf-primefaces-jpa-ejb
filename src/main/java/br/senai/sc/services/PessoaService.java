package br.senai.sc.services;


import java.util.List;

import javax.ejb.Local;

import br.senai.sc.models.Pessoa;

@Local
public interface PessoaService {

	public List<Pessoa> findAll();
	
	public Pessoa findById(Long id);

	public void save(Pessoa pessoa);

	public void update(Pessoa pessoa);

	public void delete(Pessoa pessoa);
	
	public Integer idadePessoa(Pessoa pessoa);
	
	public boolean dataNascValida(Pessoa pessoa);

	public List<Pessoa> findByNome(Pessoa pessoa);

}
