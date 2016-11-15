package br.com.devmedia.agenda.controller.dto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import br.com.devmedia.agenda.util.DateUtil;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ContatoDTO {

	private final StringProperty nome = new SimpleStringProperty();

	private final ObjectProperty<LocalDate> dataNascimento = new SimpleObjectProperty<LocalDate>();

	private EnderecoDTO endereco;

	private List<TelefoneDTO> telefones;

	public ContatoDTO() {

	}

	public ContatoDTO( String nome, LocalDate dataNascimento ) {

		this.nome.set(nome);

		this.dataNascimento.set(dataNascimento);
	}

	public LocalDate getDataNascimento() {

		return dataNascimento.get();
	}

	public void setDataNascimento(LocalDate dataNascimento) {

		this.dataNascimento.set(dataNascimento);
	}

	public StringProperty getDataNascimentoStringProperty() {

		return new SimpleStringProperty(DateUtil.format(dataNascimento.get()));
	}

	public String getDataNascimentoString() {

		return DateUtil.format(dataNascimento.get());
	}

	public ObjectProperty<LocalDate> getDataNascimentoProperty() {

		return dataNascimento;
	}

	public String getNome() {

		return nome.get();
	}

	public StringProperty getNomeProperty() {

		return nome;
	}

	public void setNome(String nome) {

		this.nome.set(nome);
	}

	public EnderecoDTO getEndereco() {

		return endereco;
	}

	public void setEndereco(EnderecoDTO endereco) {

		this.endereco = endereco;
	}

	public List<TelefoneDTO> getTelefones() {

		if (this.telefones == null) {
			
			this.telefones = new ArrayList<>();
		}
		
		return telefones;
	}

	public void setTelefones(List<TelefoneDTO> telefones) {

		this.telefones = telefones;
	}
}
