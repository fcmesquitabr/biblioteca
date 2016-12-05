package br.fa7.biblioteca.service;

import javax.ejb.Stateless;

import br.fa7.biblioteca.model.SugestaoLivro;

@Stateless
public class SugestaoService extends BaseService<SugestaoLivro>{

	public SugestaoService(){
		super(SugestaoLivro.class);
	}
}
