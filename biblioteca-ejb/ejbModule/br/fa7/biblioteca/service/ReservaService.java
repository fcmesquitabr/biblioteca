package br.fa7.biblioteca.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import br.fa7.biblioteca.model.ReservaLivro;

@Stateless
public class ReservaService extends BaseService<ReservaLivro>{

	public ReservaService(){
		super(ReservaLivro.class);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<ReservaLivro> list() {
		Query qry = em.createQuery(
				"SELECT r "
				+ "FROM ReservaLivro r "
				+ "	LEFT JOIN FETCH r.livro l"
				+ "	LEFT JOIN FETCH r.aluno "); 
		return qry.getResultList();
	}


	@Override
	public ReservaLivro remove(ReservaLivro entity) {
		entity = find(entity.getId());
		return super.remove(entity);
	}
		
}
