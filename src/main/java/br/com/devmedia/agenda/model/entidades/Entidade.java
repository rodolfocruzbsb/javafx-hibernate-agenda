package br.com.devmedia.agenda.model.entidades;

import java.io.Serializable;

public abstract class Entidade implements Serializable {

	private static final long serialVersionUID = 1l;

	public abstract Long getId();

	public abstract void setId(Long id);

}
