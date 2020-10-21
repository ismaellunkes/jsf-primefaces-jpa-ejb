package br.senai.sc.daos.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import br.senai.sc.daos.MatriculaDao;
import br.senai.sc.models.Matricula;
import br.senai.sc.models.Pessoa;

public class MatriculaDaoImpl implements MatriculaDao {

	@PersistenceContext(unitName = "prova-java")
	EntityManager em;

	public MatriculaDaoImpl() {
	}
	
	@Override
	public Matricula findById(Long id) {
		return em.find(Matricula.class, id);
	}

	@Override
	public List<Matricula> findAll() {
		Query query = em.createQuery("SELECT m FROM Matricula m ORDER BY m.dataMatricula ASC ");									
		return query.getResultList();
	}

	@Override
	public void save(Matricula matricula) {
		em.persist(matricula);

	}

	@Override
	public void update(Matricula matricula) {
		em.merge(matricula);
	}

	@Override
	public void delete(Matricula matricula) {
		em.remove(em.merge(matricula));
	}

	@Override
	public Matricula findByNome(String nome) {
		Query query = em.createQuery("FROM Matricula m WHERE m.nome like :nome");
		query.setParameter("nome", "%" + nome + "%");
		return (Matricula) query.getSingleResult();
	}

	@Override
	public List<Matricula> findByCurso(Matricula matricula) {
		Query query = em.createQuery(" SELECT m FROM Matricula m WHERE m.curso = :curso ");		
		query.setParameter("curso", matricula.getCurso());
		return query.getResultList();
	}

	@Override
	public List<Matricula> findByAluno(Pessoa pessoa) {
		Query query = em.createQuery(" SELECT m FROM Matricula m WHERE m.pessoa = :pessoa ");		
		query.setParameter("pessoa", pessoa);
		return query.getResultList();
	}

}
