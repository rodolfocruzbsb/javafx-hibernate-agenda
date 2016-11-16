package br.com.devmedia.agenda.model.dao;

import java.util.Collection;
import java.util.List;

import br.com.devmedia.agenda.model.entidades.Entidade;

public interface GenericDao<T extends Entidade> {

	T salvar(T entity);

	T selecionarPorId(Long id);

	void excluir(Long id);

	List<T> buscarTodos();

	void salvar(Collection<T> itens);
	
}
