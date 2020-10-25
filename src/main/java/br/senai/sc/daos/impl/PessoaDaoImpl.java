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
		if (pessoa.getIdPessoa()== null) {
			em.persist(pessoa);
		}else {
			update(pessoa);
		}
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
	public List<Pessoa> findByNome(Pessoa pessoa) {
		Query query = em.createQuery("FROM Pessoa p WHERE UPPER(p.nome) like UPPER(:nome)");
		query.setParameter("nome", "%" + pessoa.getNome() + "%");
		return query.getResultList();
	}

}
