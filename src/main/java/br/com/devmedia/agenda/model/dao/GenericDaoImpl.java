package br.com.devmedia.agenda.model.dao;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.devmedia.agenda.model.entidades.Entidade;
import br.com.devmedia.agenda.util.FabricaDeEntityManager;

/**
 * <p>
 * <b>Title:</b> GenericDaoImpl.java
 * </p>
 * 
 * <p>
 * <b>Description:</b> Implementação genérica de CRUD com JPA/Hibernate
 * </p>
 * 
 * <p>
 * <b>Company: </b> DEVMEDIA
 * </p>
 * 
 * @author DevMedia - http://www.devmedia.com.br/
 * @param <T>
 * 
 * @version 1.0.0
 */
public abstract class GenericDaoImpl<T extends Entidade> implements GenericDao<T> {

	protected EntityManager entityManager = FabricaDeEntityManager.getEntityManager();

	private final Class<T> clazz;

	@SuppressWarnings("unchecked")
	public GenericDaoImpl() {
		this.clazz = (Class<T>) ( (ParameterizedType) this.getClass().getGenericSuperclass() ).getActualTypeArguments()[0];
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> buscarTodos() {

		final Query query = this.entityManager.createQuery("select x from " + this.clazz.getName() + "order by id");

		return query.getResultList();
	}

	@Override
	public void excluir(final Long id) {

		this.verificaParametroID(id);

		final T entity = this.selecionarPorId(id);

		if (entity != null) {

			this.entityManager.remove(entity);
		}

	}

	@Override
	public T salvar(final T entity) {

		if (entity == null || entity.getId() == null) {

			this.entityManager.persist(entity);
		} else {

			this.entityManager.merge(entity);
		}

		return entity;
	}

	@Override
	public T selecionarPorId(final Long id) {

		this.verificaParametroID(id);

		return this.entityManager.find(this.clazz, id);
	}

	private void verificaParametroID(final Long id) {

		if (id == null) {

			throw new IllegalArgumentException("ID não pode ser nulo!");
		}
	}
}
