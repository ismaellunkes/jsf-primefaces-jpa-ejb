package br.senai.sc.daos.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import br.senai.sc.daos.PessoaDao;
import br.senai.sc.models.Pessoa;

public class PessoaDaoImpl implements PessoaDao {

	@PersistenceContext(unitName = "prova-java")
	EntityManager em;

	public PessoaDaoImpl() {
	}
	
	@Override
	public Pessoa findById(Long id) {
		return em.find(Pessoa.class, id);
	}

	@Override
	public List<Pessoa> findAll() {
		Query query = em.createQuery(" SELECT p FROM Pessoa p ORDER BY p.nome ASC ");
		return query.getResultList();
	}

	@Override
	public void save(Pessoa pessoa) {
		em.persist(pessoa);

	}

	@Override
	public void update(Pessoa pessoa) {
		em.merge(pessoa);
	}

	@Override
	public void delete(Pessoa pessoa) {
		em.remove(em.merge(pessoa));
	}

	@Override
	public Pessoa findByNome(String nome) {
		Query query = em.createQuery("FROM Pessoa p WHERE p.nome like :nome");
		query.setParameter("nome", "%" + nome + "%");
		return (Pessoa) query.getSingleResult();
	}

}
