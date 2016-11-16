package br.com.devmedia.service;

import java.util.List;

import br.com.devmedia.agenda.model.entidades.Contato;

public interface AgendaFacade {

	public List<Contato> buscarTodosContatos();

	void salvarContato(Contato entidade);

}
