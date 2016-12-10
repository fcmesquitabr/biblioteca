package br.fa7.biblioteca.distribuidora;

import br.fa7.biblioteca.model.PedidoLivro;

public class DistribuidoraOrderItem {

	private Long isbn;
	
	private Integer amount;
	
	public DistribuidoraOrderItem(PedidoLivro pedidoLivro) {
		super();
		this.isbn = Long.parseLong(pedidoLivro.getLivro().getIsbn());
		this.amount = pedidoLivro.getQuantidadeSolicitada();
	}

	public Long getIsbn() {
		return isbn;
	}

	public void setIsbn(Long isbn) {
		this.isbn = isbn;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}
	
	
}
