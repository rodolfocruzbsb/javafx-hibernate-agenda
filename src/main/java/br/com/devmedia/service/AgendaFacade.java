package br.com.devmedia.service;

import java.util.List;

import br.com.devmedia.agenda.model.entidades.Contato;
import br.com.devmedia.agenda.model.entidades.Grupo;
import br.com.devmedia.agenda.model.entidades.Telefone;

public interface AgendaFacade {

	List<Grupo> buscarTodosGrupos();

	List<Contato> buscarTodosContatos();

	void salvarContato(Contato entidade);

	void excluirTelefone(Telefone telefone);

	void excluirContato(Contato contato);

	void flush();

	void clear();

}
