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

	private final ObjectProperty<LocalDate> dataNascimento = new SimpleObjectProperty<>();

	private EnderecoDTO endereco;

	private List<TelefoneDTO> telefones;

	private List<GrupoDTO> grupos;

	private Contato contato;

	public ContatoDTO() {

	}

	public ContatoDTO( final Long id, final String nome, final LocalDate dataNascimento ) {

		this.id = id;

		this.nome.set(nome);

		this.dataNascimento.set(dataNascimento);
	}

	public LocalDate getDataNascimento() {

		return this.dataNascimento.get();
	}

	public void setDataNascimento(final LocalDate dataNascimento) {

		this.dataNascimento.set(dataNascimento);
	}

	public StringProperty getDataNascimentoStringProperty() {

		return new SimpleStringProperty(DateUtil.format(this.dataNascimento.get()));
	}

	public String getDataNascimentoString() {

		final String result = DateUtil.format(this.dataNascimento.get());

		return result != null ? result : "";
	}

	public ObjectProperty<LocalDate> getDataNascimentoProperty() {

		return this.dataNascimento;
	}

	public String getNome() {

		return this.nome.get();
	}

	public StringProperty getNomeProperty() {

		return this.nome;
	}

	public void setNome(final String nome) {

		this.nome.set(nome);
	}

	public EnderecoDTO getEndereco() {

		if (this.endereco == null) {

			this.endereco = new EnderecoDTO();
		}

		return this.endereco;
	}

	public boolean temEndereco() {

		return this.endereco != null && this.endereco.estaPreenchido();

	}

	public boolean temTelefone() {

		return this.telefones != null && !this.telefones.isEmpty();
	}

	private boolean temGrupo() {

		return this.telefones != null && !this.telefones.isEmpty();
	}

	public void setEndereco(final EnderecoDTO endereco) {

		this.endereco = endereco;
	}

	public List<TelefoneDTO> getTelefones() {

		if (this.telefones == null) {

			this.telefones = new ArrayList<>();
		}

		return this.telefones;
	}

	public void setTelefones(final List<TelefoneDTO> telefones) {

		this.telefones = telefones;
	}

	public List<GrupoDTO> getGrupos() {

		if (this.grupos == null) {

			this.grupos = new ArrayList<>();
		}

		return this.grupos;
	}

	public void setGrupos(final List<GrupoDTO> grupos) {

		this.grupos = grupos;
	}

	/**
	 * Retorna o valor do atributo <code>id</code>
	 *
	 * @return <code>Long</code>
	 */
	public Long getId() {

		return this.id;
	}

	/**
	 * Define o valor do atributo <code>id</code>.
	 *
	 * @param id
	 */
	public void setId(final Long id) {

		this.id = id;
	}

	public static ContatoDTO from(final Contato contato) {

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

			if (contato.getGrupos() != null && !contato.getGrupos().isEmpty()) {

				result.setGrupos(GrupoDTO.from(contato.getGrupos()));
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

		return this.contato;
	}

	/**
	 * Define o valor do atributo <code>contato</code>.
	 *
	 * @param contato
	 */
	public void setContato(final Contato contato) {

		this.contato = contato;
	}

	public Contato getEntidadeSincronizada() {

		this.contato = this.contato != null ? this.contato : new Contato();

		this.contato.setNome(this.getNome());

		this.contato.setDataNascimento(DateUtil.from(this.getDataNascimento()));

		this.contato.setEndereco(this.temEndereco() ? this.endereco.getEntidadeSincronizada(this.contato) : null);

		this.contato.setTelefones(this.temTelefone() ? this.telefones.stream().filter(t -> t.estaPreenchido()).map(t -> t.getEntidadeSincronizada(this.contato)).collect(Collectors.toList()) : null);

		this.contato.setGrupos(this.temGrupo() ? this.grupos.stream().filter(g -> g.estaPreenchido()).map(g -> g.getEntidadeSincronizada()).collect(Collectors.toList()) : null);

		return this.contato;
	}

}
