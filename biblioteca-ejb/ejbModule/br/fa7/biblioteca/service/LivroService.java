package br.fa7.biblioteca.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import br.fa7.biblioteca.model.Livro;

@Stateless
public class LivroService extends BaseService<Livro>{

	public LivroService(){
		super(Livro.class);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Livro> list() {		
		Query qry = em.createQuery("SELECT l FROM Livro l LEFT JOIN FETCH l.editora"); 
		return qry.getResultList();
	}		
}
