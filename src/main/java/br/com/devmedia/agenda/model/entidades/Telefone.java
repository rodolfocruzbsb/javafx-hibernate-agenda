package br.com.devmedia.agenda.model.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * <p>
 * <b>Title:</b> Telefone.java
 * </p>
 * 
 * <p>
 * <b>Description:</b> Entidade para mapeamento da tabela telefones
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
@Table(name = "telefones")
public class Telefone extends Entidade {

	/** Atributo serialVersionUID. */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_telefone", updatable = false)
	private Long id;

	@Column(name = "nu_ddd", nullable = false)
	private Long ddd;

	@Column(name = "nu_numero", nullable = false)
	private Long numero;

	@ManyToOne(optional = false)
	@JoinColumn(name = "fk_contato", nullable = false)
	private Contato contato;

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
	 * Retorna o valor do atributo <code>ddd</code>
	 *
	 * @return <code>Long</code>
	 */
	public Long getDdd() {

		return ddd;
	}

	/**
	 * Define o valor do atributo <code>ddd</code>.
	 *
	 * @param ddd
	 */
	public void setDdd(Long ddd) {

		this.ddd = ddd;
	}

	/**
	 * Retorna o valor do atributo <code>numero</code>
	 *
	 * @return <code>Long</code>
	 */
	public Long getNumero() {

		return numero;
	}

	/**
	 * Define o valor do atributo <code>numero</code>.
	 *
	 * @param numero
	 */
	public void setNumero(Long numero) {

		this.numero = numero;
	}

	/**
	 * Retorna o valor do atributo <code>contato</code>
	 *
	 * @return <code>Contato</code>
	 */
	public Contato getContato() {

		return contato;
	}

	/**
	 * Define o valor do atributo <code>contato</code>.
	 *
	 * @param contato
	 */
	public void setContato(Contato contato) {

		this.contato = contato;
	}

}
