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
public class ReservaLivro implements Serializable{

	private static final long serialVersionUID = -6107320835710276399L;

	@Id
	@SequenceGenerator(name="reservalivro_id_seq", sequenceName="reservalivro_id_seq", allocationSize=1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="reservalivro_id_seq")
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "alunoid")
	private Aluno aluno;
	
	@ManyToOne
	@JoinColumn(name = "livroid")
	private Livro livro;

	@ManyToOne
	@JoinColumn(name = "situacaoreservaid")
	private SituacaoReserva situacaoReserva;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataRealizacao;

	@Temporal(TemporalType.TIMESTAMP)
	private Date dataEmprestimo;
	
	public ReservaLivro() {
		super();
	}
	
	@Override
	public String toString() {
		return "ReservaLivro [aluno=" + aluno + ", livro=" + livro + "]";
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
		ReservaLivro other = (ReservaLivro) obj;
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

	public SituacaoReserva getSituacaoReserva() {
		return situacaoReserva;
	}

	public void setSituacaoReserva(SituacaoReserva situacaoReserva) {
		this.situacaoReserva = situacaoReserva;
	}

	public Date getDataEmprestimo() {
		return dataEmprestimo;
	}

	public void setDataEmprestimo(Date dataEmprestimo) {
		this.dataEmprestimo = dataEmprestimo;
	}	
}
