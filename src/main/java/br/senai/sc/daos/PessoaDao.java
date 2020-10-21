package br.senai.sc.daos;

import java.util.List;

import br.senai.sc.models.Pessoa;

public interface PessoaDao {

	public List<Pessoa> findAll();
	
	public Pessoa findById(Long id);
	
	public void save(Pessoa pessoa);
	
	public void update(Pessoa pessoa);
	
	public void delete(Pessoa pessoa);
	
	public Pessoa findByNome(String nome);
	
}
