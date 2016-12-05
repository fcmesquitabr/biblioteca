package br.fa7.biblioteca.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class SugestaoLivro implements Serializable{

	private static final long serialVersionUID = 5215480763340090080L;

	@Id
	@SequenceGenerator(name="sugestaolivro_id_seq", sequenceName="sugestaolivro_id_seq", allocationSize=1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="sugestaolivro_id_seq")
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name = "alunoid")
	private Aluno aluno;
	
	@ManyToOne
	@JoinColumn(name = "livroid")
	private Livro livro;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataRealizacao;

	
	public SugestaoLivro() {
		super();
	}
	
	@Override
	public String toString() {
		return "SugestaoLivro [aluno=" + aluno + ", livro=" + livro + "]";
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
		SugestaoLivro other = (SugestaoLivro) obj;
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

	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}

	public Livro getLivro() {
		return livro;
	}

	public void setLivro(Livro livro) {
		this.livro = livro;
	}

	public Date getDataRealizacao() {
		return dataRealizacao;
	}

	public void setDataRealizacao(Date dataRealizacao) {
		this.dataRealizacao = dataRealizacao;
	}		
}
