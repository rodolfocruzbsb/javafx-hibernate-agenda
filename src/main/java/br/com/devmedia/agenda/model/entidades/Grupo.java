package br.com.devmedia.agenda.model.entidades;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 * <p>
 * <b>Title:</b> Grupo.java
 * </p>
 * 
 * <p>
 * <b>Description:</b>Entidade para mapeamento da tabela grupos
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
@Table(name = "grupos")
public class Grupo extends Entidade {

	/** Atributo serialVersionUID. */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_grupo", updatable = false)
	private Long id;

	@Column(name = "ds_nome", nullable = false)
	private String nome;

	@Column(name = "ds_descricao", nullable = false)
	private String descricao;

	@ManyToMany(mappedBy = "grupos")
	private Collection<Contato> contatos;

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
	 * Retorna o valor do atributo <code>descricao</code>
	 *
	 * @return <code>String</code>
	 */
	public String getDescricao() {

		return descricao;
	}

	/**
	 * Define o valor do atributo <code>descricao</code>.
	 *
	 * @param descricao
	 */
	public void setDescricao(String descricao) {

		this.descricao = descricao;
	}

	/**
	 * Retorna o valor do atributo <code>contatos</code>
	 *
	 * @return <code>Collection<Contato></code>
	 */
	public Collection<Contato> getContatos() {

		return contatos;
	}

	/**
	 * Define o valor do atributo <code>contatos</code>.
	 *
	 * @param contatos
	 */
	public void setContatos(Collection<Contato> contatos) {

		this.contatos = contatos;
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

		return "Grupo [id=" + id + ", nome=" + nome + ", descricao=" + descricao + "]";
	}

}
