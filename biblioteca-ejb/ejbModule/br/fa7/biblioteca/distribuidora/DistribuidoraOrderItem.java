package br.fa7.biblioteca.distribuidora;

import br.fa7.biblioteca.model.PedidoLivro;

public class DistribuidoraOrderItem {

	private Integer id;
	
	private Long isbn;
	
	private Integer amount;
	
	private Integer amountRequest;
	
	private Integer amountResponse;
	
	@Override
	public String toString() {
		return "DistribuidoraOrderItem [isbn=" + isbn + ", amount=" + amount + "]";
	}
	
	
	public DistribuidoraOrderItem() {
		super();
		// TODO Auto-generated constructor stub
	}


	public DistribuidoraOrderItem(Long isbn, Integer amount) {
		super();
		this.isbn = isbn;
		this.amount = amount;
	}


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


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public Integer getAmountRequest() {
		return amountRequest;
	}


	public void setAmountRequest(Integer amountRequest) {
		this.amountRequest = amountRequest;
	}


	public Integer getAmountResponse() {
		return amountResponse;
	}


	public void setAmountResponse(Integer amountResponse) {
		this.amountResponse = amountResponse;
	}		
	
}
