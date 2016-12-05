package br.fa7.biblioteca.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

@Entity
public class PedidoLivro implements Serializable{

	private static final long serialVersionUID = -8473199004487196930L;

	@Id
	@SequenceGenerator(name="pedidolivro_id_seq", sequenceName="pedidolivro_id_seq", allocationSize=1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="pedidolivro_id_seq")
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name = "livroid")
	private Livro livro;
	
	@ManyToOne
	@JoinColumn(name = "pedidoid")
	private Pedido pedido;
	
	private Integer quantidade;

	
	public PedidoLivro() {
		super();
	}
	
	@Override
	public String toString() {
		return "PedidoLivro [livro=" + livro + ", pedido=" + pedido + ", quantidade=" + quantidade + "]";
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PedidoLivro other = (PedidoLivro) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Livro getLivro() {
		return livro;
	}

	public void setLivro(Livro livro) {
		this.livro = livro;
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}		
}
