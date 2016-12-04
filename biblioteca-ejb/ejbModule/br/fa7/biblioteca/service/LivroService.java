package br.fa7.biblioteca.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import br.fa7.biblioteca.model.Livro;

@Stateless
public class LivroService {

	@PersistenceContext(unitName="biblioteca")
	private EntityManager em;

	@SuppressWarnings("unchecked")
	public List<Livro> listar(){
		//Query query = em.createQuery("SELECT l from Livro l LEFT JOIN FETCH l.autores");
		Query query = em.createQuery("SELECT l from Livro l");
		return query.getResultList();
	}
}
