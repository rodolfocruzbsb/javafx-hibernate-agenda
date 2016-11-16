package br.com.devmedia.agenda.controller.dto;

import br.com.devmedia.agenda.model.entidades.Contato;
import br.com.devmedia.agenda.model.entidades.Endereco;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class EnderecoDTO {

	private Endereco entidade;

	private final StringProperty cep = new SimpleStringProperty();

	private final StringProperty complemento = new SimpleStringProperty();

	private final StringProperty numero = new SimpleStringProperty();

	private final StringProperty descricao = new SimpleStringProperty();

	public EnderecoDTO() {

	}

	public EnderecoDTO( final Endereco entidade, final String cep, final String complemento, final String numero, final String descricao ) {

		this.entidade = entidade;

		this.cep.set(cep);

		this.complemento.set(complemento);

		this.numero.set(numero);

		this.descricao.set(descricao);

	}

	public String getCep() {

		return cep.get();
	}

	public void setCep(String cep) {

		this.cep.set(cep);
	}

	public StringProperty getCepProperty() {

		return cep;
	}

	public String getComplemento() {

		return complemento.get();
	}

	public void setComplemento(String complemento) {

		this.complemento.set(complemento);
	}

	public StringProperty getComplementoProperty() {

		return complemento;
	}

	public String getNumero() {

		return numero.get();
	}

	public void setNumero(String numero) {

		this.numero.set(numero);
	}

	public StringProperty getNumeroProperty() {

		return numero;
	}

	public String getDescricao() {

		return descricao.get();
	}

	public void setDescricao(String descricao) {

		this.descricao.set(descricao);
	}

	public StringProperty getDescricaoProperty() {

		return descricao;
	}

	public static EnderecoDTO from(Endereco endereco) {

		EnderecoDTO result = null;

		if (endereco != null) {

			result = new EnderecoDTO(endereco, endereco.getCep(), endereco.getComplemento(), endereco.getNumero(), endereco.getDescricao());
		}

		return result;
	}

	public boolean estaPreenchido() {

		return ( (this.getCep() != null && this.getCep().trim().length() > 0)

				|| (this.getComplemento() != null && this.getComplemento().trim().length() > 0)

				|| (this.getDescricao() != null && this.getDescricao().trim().length() > 0)

				|| (this.getNumero() != null && this.getNumero().trim().length() > 0) );
	}

	public Endereco getEntidadeSincronizada(Contato contato) {

		if (this.estaPreenchido()) {

			entidade = entidade != null ? entidade : new Endereco();

			entidade.setCep(getCep());

			entidade.setComplemento(getComplemento());

			entidade.setDescricao(getDescricao());

			entidade.setNumero(getNumero());

			entidade.setContato(contato);
		}

		return entidade;
	}

	/**
	 * Retorna o valor do atributo <code>entidade</code>
	 *
	 * @return <code>Endereco</code>
	 */
	public Endereco getEntidade() {

		return entidade;
	}

	/**
	 * Define o valor do atributo <code>entidade</code>.
	 *
	 * @param entidade
	 */
	public void setEntidade(Endereco entidade) {

		this.entidade = entidade;
	}

}
