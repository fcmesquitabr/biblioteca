package br.fa7.biblioteca.model;

import java.io.Serializable;

import javax.persistence.Cacheable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Cacheable(true)
@Entity
public class SituacaoReserva implements Serializable{

	private static final long serialVersionUID = 2232790861133640183L;

	public static final int AGUARDANDO_EMPRESTIMO = 1;
	
	public static final int EMPRESTADO = 2;
	
	public static final int CANCELADO = 3;
	
	public static final int DEVOLVIDO = 4;
	
	@Id
	@SequenceGenerator(name="situacaoreserva_id_seq", sequenceName="situacaoreserva_id_seq", allocationSize=1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="situacaoreserva_id_seq")
	private Integer id;
	
	private String descricao;
	
	public SituacaoReserva() {
		super();
	}

	@Override
	public String toString() {
		return "SituacaoReserva [id=" + id + ", descricao=" + descricao + "]";
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
		SituacaoReserva other = (SituacaoReserva) obj;
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
}
