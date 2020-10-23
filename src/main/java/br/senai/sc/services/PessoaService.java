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

	public Pessoa findByNome(String nome);
	
	public Integer idadePessoa(Pessoa pessoa);

}
