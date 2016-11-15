package br.com.devmedia.agenda.controller.dto;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class TelefoneDTO {

	private final IntegerProperty ddd = new SimpleIntegerProperty();

	private final IntegerProperty numero = new SimpleIntegerProperty();

	public TelefoneDTO() {

	}

	public TelefoneDTO( Integer ddd, Integer numero ) {

		this.ddd.set(ddd);

		this.numero.set(numero);

	}

	public Integer getDdd() {

		return ddd.get();
	}

	public void setDdd(Integer ddd) {

		this.ddd.set(ddd);
	}

	public IntegerProperty getDddProperty() {

		return ddd;
	}

	public SimpleStringProperty getDddStringProperty() {

		return new SimpleStringProperty(String.valueOf(this.ddd.get()));
	}

	public Integer getNumero() {

		return numero.get();
	}

	public void setNumero(Integer numero) {

		this.numero.set(numero);
	}

	public IntegerProperty getNumeroProperty() {

		return numero;
	}

	public SimpleStringProperty getNumeroStringProperty() {

		return new SimpleStringProperty(String.valueOf(this.numero.get()));
	}

	@Override
	public String toString() {

		return "TelefoneDTO [ddd=" + ddd.get() + ", numero=" + numero.get() + "]";
	}

	
	
}
