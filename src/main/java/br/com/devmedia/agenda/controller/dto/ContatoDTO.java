package br.com.devmedia.agenda.controller.dto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import br.com.devmedia.agenda.model.entidades.Contato;
import br.com.devmedia.agenda.util.DateUtil;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ContatoDTO {

	private Long id;

	private final StringProperty nome = new SimpleStringProperty();

	private final ObjectProperty<LocalDate> dataNascimento = new SimpleObjectProperty<LocalDate>();

	private EnderecoDTO endereco;

	private List<TelefoneDTO> telefones;

	private Contato contato;

	public ContatoDTO() {

	}

	public ContatoDTO( final Long id, final String nome, final LocalDate dataNascimento ) {

		this.id = id;

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

		final String result = DateUtil.format(dataNascimento.get());

		return result != null ? result : "";
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
		
		if(endereco == null){
			
			endereco = new EnderecoDTO();
		}

		return endereco;
	}

	public boolean temEndereco() {

		return this.endereco != null && this.endereco.estaPreenchido();

	}

	public boolean temTelefone() {

		return this.telefones != null && !this.telefones.isEmpty();
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

	public static ContatoDTO from(Contato contato) {

		ContatoDTO result = null;

		if (contato != null) {

			result = new ContatoDTO(contato.getId(), contato.getNome(), DateUtil.from(contato.getDataNascimento()));

			result.setContato(contato);

			if (contato.getEndereco() != null) {

				result.setEndereco(EnderecoDTO.from(contato.getEndereco()));
			}

			if (contato.getTelefones() != null && !contato.getTelefones().isEmpty()) {

				result.setTelefones(TelefoneDTO.from(contato.getTelefones()));
			}
		}

		return result;
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

	public Contato getEntidadeSincronizada() {

		this.contato = this.contato != null ? this.contato : new Contato();
		
		this.contato.setNome(getNome());

		this.contato.setDataNascimento(DateUtil.from(getDataNascimento()));

		this.contato.setEndereco(this.temEndereco() ? this.endereco.getEntidadeSincronizada(this.contato) : null);

		this.contato.setTelefones(this.temTelefone() ? this.telefones.stream().filter(t -> t.estaPreenchido()).map(t -> t.getEntidadeSincronizada(this.contato)).collect(Collectors.toList()) : null);
		return this.contato;
	}
}
