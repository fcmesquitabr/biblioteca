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
public class Distribuidora implements Serializable{

	private static final long serialVersionUID = -3457668954169662763L;

	@Id
	@SequenceGenerator(name="distribuidora_id_seq", sequenceName="distribuidora_id_seq", allocationSize=1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="distribuidora_id_seq")
	private Integer id;
	
	private String nome;

	
	public Distribuidora() {
		super();
	}
		

	@Override
	public String toString() {
		return "Distribuidora [nome=" + nome + "]";
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
		Distribuidora other = (Distribuidora) obj;
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

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}	
}
