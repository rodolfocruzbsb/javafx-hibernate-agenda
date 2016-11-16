package br.com.devmedia.agenda.model.entidades;

import java.util.Collection;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

/**
 * <p>
 * <b>Title:</b> Contato.java
 * </p>
 * 
 * <p>
 * <b>Description:</b> Entidade para mapeamento da tabela contatos
 * </p>
 * 
 * <p>
 * <b>Company: </b> DEVMEDIA
 * </p>
 * 
 * @author DevMedia - http://www.devmedia.com.br/
 * 
 * @version 1.0.0
 */
@Entity
@Table(name = "contatos")
public class Contato extends Entidade {

	/** Atributo serialVersionUID. */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_contato", updatable = false)
	private Long id;

	@Column(name = "ds_nome", nullable = false)
	private String nome;

	@Temporal(TemporalType.DATE)
	@Column(name = "dt_nascimento", nullable = false)
	private Date dataNascimento;

	@OneToOne(optional = true, cascade = CascadeType.ALL)
	@JoinColumn(name = "fk_endereco", unique = true)
	private Endereco endereco;

	@OneToMany(mappedBy = "contato", cascade = CascadeType.ALL)
	private Collection<Telefone> telefones;

	@ManyToMany
	@JoinTable(name = "ass_grupo_contato",

			uniqueConstraints = @UniqueConstraint(columnNames = { "id_contato", "id_grupo" }),

			joinColumns = @JoinColumn(name = "id_contato", nullable = false),

			inverseJoinColumns = @JoinColumn(name = "id_grupo", nullable = false)

	)
	private Collection<Grupo> grupos;

	/**
	 * Retorna o valor do atributo <code>id</code>
	 *
	 * @return <code>Long</code>
	 */
	public Long getId() {

		return id;
	}

	/**
	 * Define o valor do atributo <code>id</code>.
	 *
	 * @param id
	 */
	public void setId(Long id) {

		this.id = id;
	}

	/**
	 * Retorna o valor do atributo <code>nome</code>
	 *
	 * @return <code>String</code>
	 */
	public String getNome() {

		return nome;
	}

	/**
	 * Define o valor do atributo <code>nome</code>.
	 *
	 * @param nome
	 */
	public void setNome(String nome) {

		this.nome = nome;
	}

	/**
	 * Retorna o valor do atributo <code>dataNascimento</code>
	 *
	 * @return <code>Date</code>
	 */
	public Date getDataNascimento() {

		return dataNascimento;
	}

	/**
	 * Define o valor do atributo <code>dataNascimento</code>.
	 *
	 * @param dataNascimento
	 */
	public void setDataNascimento(Date dataNascimento) {

		this.dataNascimento = dataNascimento;
	}

	/**
	 * Retorna o valor do atributo <code>endereco</code>
	 *
	 * @return <code>Endereco</code>
	 */
	public Endereco getEndereco() {

		return endereco;
	}

	/**
	 * Define o valor do atributo <code>endereco</code>.
	 *
	 * @param endereco
	 */
	public void setEndereco(Endereco endereco) {

		this.endereco = endereco;
	}

	/**
	 * Retorna o valor do atributo <code>telefones</code>
	 *
	 * @return <code>Collection<Telefone></code>
	 */
	public Collection<Telefone> getTelefones() {

		return telefones;
	}

	/**
	 * Define o valor do atributo <code>telefones</code>.
	 *
	 * @param telefones
	 */
	public void setTelefones(Collection<Telefone> telefones) {

		this.telefones = telefones;
	}

	/**
	 * Retorna o valor do atributo <code>grupos</code>
	 *
	 * @return <code>Collection<Grupo></code>
	 */
	public Collection<Grupo> getGrupos() {

		return grupos;
	}

	/**
	 * Define o valor do atributo <code>grupos</code>.
	 *
	 * @param grupos
	 */
	public void setGrupos(Collection<Grupo> grupos) {

		this.grupos = grupos;
	}

	/**
	 * Descrição Padrão: <br>
	 * <br>
	 *
	 * {@inheritDoc}
	 *
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {

		return "Contato [id=" + id + ", nome=" + nome + ", dataNascimento=" + dataNascimento + ", endereco=" + endereco + "]";
	}

}
