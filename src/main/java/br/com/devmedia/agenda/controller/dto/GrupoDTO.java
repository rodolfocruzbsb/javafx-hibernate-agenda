package br.com.devmedia.agenda.controller.dto;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import br.com.devmedia.agenda.model.entidades.Grupo;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class GrupoDTO {

	private Grupo entidade;

	private final StringProperty nome = new SimpleStringProperty();

	private final StringProperty descricao = new SimpleStringProperty();

	public GrupoDTO() {

	}

	public GrupoDTO( final Grupo entidade, final String nome, final String descricao ) {

		this.entidade = entidade;

		this.nome.set(nome);

		this.descricao.set(descricao);

	}

	public String getNome() {

		return this.nome.get();
	}

	public void setNome(final String nome) {

		this.nome.set(nome);
	}

	public StringProperty getNomeProperty() {

		return this.nome;
	}

	public String getDescricao() {

		return this.descricao.get();
	}

	public void setDescricao(final String descricao) {

		this.descricao.set(descricao);
	}

	public StringProperty getDescricaoProperty() {

		return this.descricao;
	}

	public static GrupoDTO from(final Grupo grupo) {

		GrupoDTO result = null;

		if (grupo != null) {

			result = new GrupoDTO(grupo, grupo.getNome(), grupo.getDescricao());
		}

		return result;
	}

	public static List<GrupoDTO> from(final Collection<Grupo> grupos) {

		List<GrupoDTO> result = new ArrayList<>();

		if (grupos != null) {

			result = grupos.stream().filter(t -> t != null).map(t -> GrupoDTO.from(t)).collect(Collectors.toList());

		}

		return result;
	}

	public boolean estaPreenchido() {

		return ( ( this.getDescricao() != null && this.getDescricao().trim().length() > 0 )

				|| ( this.getNome() != null && this.getNome().trim().length() > 0 ) );
	}

	public Grupo getEntidadeSincronizada() {

		if (this.estaPreenchido()) {

			this.entidade = this.entidade != null ? this.entidade : new Grupo();

			this.entidade.setDescricao(this.getDescricao());

			this.entidade.setNome(this.getNome());

		}

		return this.entidade;
	}

	/**
	 * Retorna o valor do atributo <code>entidade</code>
	 *
	 * @return <code>Grupo</code>
	 */
	public Grupo getEntidade() {

		return this.entidade;
	}

	/**
	 * Define o valor do atributo <code>entidade</code>.
	 *
	 * @param entidade
	 */
	public void setEntidade(final Grupo entidade) {

		this.entidade = entidade;
	}

}
