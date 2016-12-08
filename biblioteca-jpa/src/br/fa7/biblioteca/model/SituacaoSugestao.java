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
public class SituacaoSugestao implements Serializable{

	private static final long serialVersionUID = 2232790861133640183L;

	public static final int EM_ANALISE = 1;
	
	public static final int ADQUIRIDA = 2;
	
	public static final int CANCELADA = 3;
	
	public static final int NAO_DISPONIVEL = 4;
	
	@Id
	@SequenceGenerator(name="situacaosugestao_id_seq", sequenceName="situacaosugestao_id_seq", allocationSize=1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="situacaosugestao_id_seq")
	private Integer id;
	
	private String descricao;
	
	public SituacaoSugestao() {
		super();
	}

	@Override
	public String toString() {
		return "SituacaoSugestao [id=" + id + ", descricao=" + descricao + "]";
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
		SituacaoSugestao other = (SituacaoSugestao) obj;
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
