package br.fa7.biblioteca.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OrderBy;
import javax.persistence.SequenceGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Livro implements Serializable{

	private static final long serialVersionUID = 5828721390913180338L;

	@Id
	@SequenceGenerator(name="livro_id_seq", sequenceName="livro_id_seq", allocationSize=1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="livro_id_seq")
	private Integer id;
	
	private String titulo;
	
	private String isbn;
	
	private String descricao;

	@ManyToOne
	@JoinColumn(name = "editoraid")
	private Editora editora;

	@ManyToMany(
			fetch = FetchType.LAZY,	
			targetEntity=br.fa7.biblioteca.model.Autor.class,
			cascade={CascadeType.PERSIST, CascadeType.MERGE}
			)
	@JoinTable(
			name="livroautor",
			joinColumns=@JoinColumn(name="livroid"),
			inverseJoinColumns=@JoinColumn(name="autorid")	   
			)
	@OrderBy("nome")
	@JsonIgnore
	private List<Autor> autores;
	
	
	public Livro() {
		super();
	}
	
	@Override
	public String toString() {
		return "Livro [titulo=" + titulo + "]";
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
		Livro other = (Livro) obj;
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

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Editora getEditora() {
		return editora;
	}

	public void setEditora(Editora editora) {
		this.editora = editora;
	}

	public List<Autor> getAutores() {
		return autores;
	}

	public void setAutores(List<Autor> autores) {
		this.autores = autores;
	}			
}
