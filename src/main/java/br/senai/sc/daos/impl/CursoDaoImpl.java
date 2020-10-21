package br.senai.sc.daos.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import br.senai.sc.daos.CursoDao;
import br.senai.sc.models.Curso;

public class CursoDaoImpl implements CursoDao {

	@PersistenceContext(unitName = "prova-java")
	EntityManager em;

	public CursoDaoImpl() {
	}
	
	@Override
	public Curso findById(Long id) {
		return em.find(Curso.class, id);
	}

	@Override
	public List<Curso> findAll() {
		Query query = em.createQuery(" SELECT p FROM Curso p ORDER BY p.nome ASC ");
		return query.getResultList();
	}

	@Override
	public void save(Curso curso) {
		em.persist(curso);

	}

	@Override
	public void update(Curso curso) {
		em.merge(curso);
	}

	@Override
	public void delete(Curso curso) {
		em.remove(em.merge(curso));
	}

	@Override
	public Curso findByNome(String nome) {
		Query query = em.createQuery("FROM Curso p WHERE p.nome like :nome");
		query.setParameter("nome", "%" + nome + "%");
		return (Curso) query.getSingleResult();
	}

}
