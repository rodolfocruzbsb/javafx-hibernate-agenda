package br.com.devmedia.agenda.controller.dto;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class EnderecoDTO {

	private final IntegerProperty cep = new SimpleIntegerProperty();

	private final StringProperty complemento = new SimpleStringProperty();

	private final StringProperty numero = new SimpleStringProperty();

	private final StringProperty descricao = new SimpleStringProperty();

	public EnderecoDTO() {

	}

	public EnderecoDTO( Integer cep, String complemento, String numero, String descricao ) {

		this.cep.set(cep);

		this.complemento.set(complemento);

		this.numero.set(numero);

		this.descricao.set(descricao);

	}

	public Integer getCep() {

		return cep.get();
	}

	public void setCep(Integer cep) {

		this.cep.set(cep);
	}

	public IntegerProperty getCepProperty() {

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

}
