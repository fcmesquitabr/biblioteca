package br.fa7.biblioteca.service;

import javax.ejb.Stateless;

import br.fa7.biblioteca.model.ReservaLivro;

@Stateless
public class ReservaService extends BaseService<ReservaLivro>{

	public ReservaService(){
		super(ReservaLivro.class);
	}

	@Override
	public ReservaLivro remove(ReservaLivro entity) {
		entity = find(entity.getId());
		return super.remove(entity);
	}
	
	
}
