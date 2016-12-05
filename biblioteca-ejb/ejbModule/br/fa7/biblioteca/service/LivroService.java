package br.fa7.biblioteca.service;

import javax.ejb.Stateless;

import br.fa7.biblioteca.model.Livro;

@Stateless
public class LivroService extends BaseService<Livro>{

	public LivroService(){
		super(Livro.class);
	}
	
}
