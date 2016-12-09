package br.fa7.biblioteca.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Pedido implements Serializable{

	private static final long serialVersionUID = -3032963513110126852L;

	@Id
	@SequenceGenerator(name="pedido_id_seq", sequenceName="pedido_id_seq", allocationSize=1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="pedido_id_seq")
	private Integer id;
	
	private String descricao;
	
	@ManyToOne
	@JoinColumn(name = "distribuidoraid")
	private Distribuidora distribuidora;

	@ManyToOne
	@JoinColumn(name = "situacaopedidoid")
	private SituacaoPedido situacaoPedido;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataRealizacao;

	@OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE},
			mappedBy="pedido",fetch=FetchType.LAZY)
	private List<PedidoLivro> pedidoLivros;
	
	public Pedido() {
		super();
	}
	
	@Override
	public String toString() {
		return "Pedido [id=" + id + ", distribuidora=" + distribuidora + "]";
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
		Pedido other = (Pedido) obj;
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

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Distribuidora getDistribuidora() {
		return distribuidora;
	}

	public void setDistribuidora(Distribuidora distribuidora) {
		this.distribuidora = distribuidora;
	}

	public Date getDataRealizacao() {
		return dataRealizacao;
	}

	public void setDataRealizacao(Date dataRealizacao) {
		this.dataRealizacao = dataRealizacao;
	}

	public SituacaoPedido getSituacaoPedido() {
		return situacaoPedido;
	}

	public void setSituacaoPedido(SituacaoPedido situacaoPedido) {
		this.situacaoPedido = situacaoPedido;
	}

	public List<PedidoLivro> getPedidoLivros() {
		return pedidoLivros;
	}

	public void setPedidoLivros(List<PedidoLivro> pedidoLivros) {
		this.pedidoLivros = pedidoLivros;
	}		
}
