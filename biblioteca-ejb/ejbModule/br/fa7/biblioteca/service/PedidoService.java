package br.fa7.biblioteca.service;

import javax.ejb.Stateless;

import br.fa7.biblioteca.model.Pedido;

@Stateless
public class PedidoService extends BaseService<Pedido>{

	public PedidoService(){
		super(Pedido.class);
	}
}
