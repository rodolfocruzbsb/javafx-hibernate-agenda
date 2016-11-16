package br.com.devmedia.agenda.controller.dto;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import br.com.devmedia.agenda.model.entidades.Contato;
import br.com.devmedia.agenda.model.entidades.Telefone;
import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;

public class TelefoneDTO {

	private Telefone entidade;

	private final LongProperty ddd = new SimpleLongProperty();

	private final LongProperty numero = new SimpleLongProperty();

	public TelefoneDTO() {

	}

	public TelefoneDTO( final Telefone entidade, final Long ddd, final Long numero ) {

		this.entidade = entidade;

		this.ddd.set(ddd);

		this.numero.set(numero);

	}

	public Long getDdd() {

		return ddd.get();
	}

	public void setDdd(Long ddd) {

		this.ddd.set(ddd);
	}

	public LongProperty getDddProperty() {

		return ddd;
	}

	public SimpleStringProperty getDddStringProperty() {

		return new SimpleStringProperty(String.valueOf(this.ddd.get()));
	}

	public Long getNumero() {

		return numero.get();
	}

	public void setNumero(Long numero) {

		this.numero.set(numero);
	}

	public LongProperty getNumeroProperty() {

		return numero;
	}

	public SimpleStringProperty getNumeroStringProperty() {

		return new SimpleStringProperty(String.valueOf(this.numero.get()));
	}

	@Override
	public String toString() {

		return "TelefoneDTO [ddd=" + ddd.get() + ", numero=" + numero.get() + "]";
	}

	public static List<TelefoneDTO> from(Collection<Telefone> telefones) {

		List<TelefoneDTO> result = new ArrayList<>();

		if (telefones != null) {

			result = telefones.stream().filter(t -> t != null).map(t -> TelefoneDTO.from(t)).collect(Collectors.toList());

		}

		return result;
	}

	public static TelefoneDTO from(Telefone telefone) {

		TelefoneDTO result = null;

		if (telefone != null) {

			result = new TelefoneDTO(telefone, telefone.getDdd(), telefone.getNumero());
		}

		return result;
	}

	public boolean estaPreenchido() {

		return this.ddd != null || this.numero != null;
	}

	public Telefone getEntidadeSincronizada(Contato contato) {

		if (estaPreenchido()) {

			entidade = entidade != null ? entidade : new Telefone();

			entidade.setContato(contato);

			entidade.setDdd(getDdd());

			entidade.setNumero(getNumero());
		}
		return entidade;
	}

	/**
	 * Retorna o valor do atributo <code>entidade</code>
	 *
	 * @return <code>Telefone</code>
	 */
	public Telefone getEntidade() {

		return entidade;
	}

	/**
	 * Define o valor do atributo <code>entidade</code>.
	 *
	 * @param entidade
	 */
	public void setEntidade(Telefone entidade) {

		this.entidade = entidade;
	}

}
