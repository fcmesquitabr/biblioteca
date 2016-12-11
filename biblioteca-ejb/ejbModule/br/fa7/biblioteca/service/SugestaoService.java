package br.fa7.biblioteca.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import br.fa7.biblioteca.model.SugestaoLivro;

@Stateless
public class SugestaoService extends BaseService<SugestaoLivro>{

	public SugestaoService(){
		super(SugestaoLivro.class);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<SugestaoLivro> list() {
		Query qry = em.createQuery(
				"SELECT s "
				+ "FROM SugestaoLivro s "
				+ "	LEFT JOIN FETCH s.livro l"
				+ "	LEFT JOIN FETCH s.aluno "); 
		return qry.getResultList();
	}

	@Override
	public SugestaoLivro remove(SugestaoLivro entity) {
		entity = find(entity.getId());
		return super.remove(entity);
	}		
}
