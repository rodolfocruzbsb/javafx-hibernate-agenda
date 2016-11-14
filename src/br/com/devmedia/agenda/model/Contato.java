package br.com.devmedia.agenda.model;

import java.time.LocalDate;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Contato {

	private final StringProperty fabricante;

	private final StringProperty modelo;

	private final StringProperty placa;

	private final ObjectProperty<LocalDate> birthday;

	/**
	 * Default constructor.
	 */
	public Contato() {
		this(null, null);
	}

	/**
	 * Constructor with some initial data.
	 * 
	 * @param fabricante
	 * @param modelo
	 */
	public Contato( String fabricante, String modelo ) {
		this.fabricante = new SimpleStringProperty(fabricante);
		this.modelo = new SimpleStringProperty(modelo);

		// Some initial dummy data, just for convenient testing.
		this.placa = new SimpleStringProperty();
		this.birthday = new SimpleObjectProperty<LocalDate>(LocalDate.of(1999, 2, 21));
	}

	public String getFabricante() {

		return fabricante.get();
	}

	public void setFabricante(String fabricante) {

		this.fabricante.set(fabricante);
	}

	public StringProperty fabricanteProperty() {

		return fabricante;
	}

	public String getModelo() {

		return modelo.get();
	}

	public void setModelo(String modelo) {

		this.modelo.set(modelo);
	}

	public StringProperty modeloProperty() {

		return modelo;
	}

	public String getPlaca() {

		return placa.get();
	}

	public void setPlaca(String placa) {

		this.placa.set(placa);
	}

	public StringProperty placaProperty() {

		return placa;
	}

	public LocalDate getBirthday() {

		return birthday.get();
	}

	public void setBirthday(LocalDate birthday) {

		this.birthday.set(birthday);
	}

	public ObjectProperty<LocalDate> birthdayProperty() {

		return birthday;
	}
}
