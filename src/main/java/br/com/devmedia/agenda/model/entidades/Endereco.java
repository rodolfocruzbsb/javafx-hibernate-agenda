package br.com.devmedia.agenda.model.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * <p>
 * <b>Title:</b> Endereco.java
 * </p>
 * 
 * <p>
 * <b>Description:</b> Entidade para mapeamento da tabela enderecos
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
@Table(name = "enderecos")
public class Endereco extends Entidade {

	/** Atributo serialVersionUID. */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_endereco", updatable = false)
	private Long id;

	@Column(name = "ds_cep", nullable = false)
	private String cep;

	@Column(name = "ds_complemento")
	private String complemento;

	@Column(name = "ds_numero", nullable = false)
	private String numero;

	@Column(name = "ds_descricao", nullable = false)
	private String descricao;

	@OneToOne(mappedBy = "endereco")
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
	 * Retorna o valor do atributo <code>cep</code>
	 *
	 * @return <code>String</code>
	 */
	public String getCep() {

		return cep;
	}

	/**
	 * Define o valor do atributo <code>cep</code>.
	 *
	 * @param cep
	 */
	public void setCep(String cep) {

		this.cep = cep;
	}

	/**
	 * Retorna o valor do atributo <code>complemento</code>
	 *
	 * @return <code>String</code>
	 */
	public String getComplemento() {

		return complemento;
	}

	/**
	 * Define o valor do atributo <code>complemento</code>.
	 *
	 * @param complemento
	 */
	public void setComplemento(String complemento) {

		this.complemento = complemento;
	}

	/**
	 * Retorna o valor do atributo <code>numero</code>
	 *
	 * @return <code>String</code>
	 */
	public String getNumero() {

		return numero;
	}

	/**
	 * Define o valor do atributo <code>numero</code>.
	 *
	 * @param numero
	 */
	public void setNumero(String numero) {

		this.numero = numero;
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

	public Contato getContato() {

		return contato;
	}

	public void setContato(Contato contato) {

		this.contato = contato;
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

		return "Endereco [id=" + id + ", cep=" + cep + ", complemento=" + complemento + ", numero=" + numero + ", descricao=" + descricao + "]";
	}

}
