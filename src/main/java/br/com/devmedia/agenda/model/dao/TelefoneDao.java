package br.com.devmedia.agenda.model.dao;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.devmedia.agenda.model.entidades.Contato;
import br.com.devmedia.agenda.model.entidades.Telefone;

/**
 * <p>
 * <b>Title:</b> TelefoneDao.java
 * </p>
 * 
 * <p>
 * <b>Description:</b> DAO para trabalhar com a entidade Telefone
 * </p>
 * 
 * <p>
 * <b>Company: </b> DEVMEDIA
 * </p>
 * 
 * @author DevMedia - http://www.devmedia.com.br/
 * 
 * @version 1.0.0
 */
public class TelefoneDao extends GenericDaoImpl<Telefone> {

	public TelefoneDao( final EntityManager entityManager ) {

		super(entityManager);
	}

	@SuppressWarnings("unchecked")
	public Collection<Telefone> buscarPorContato(final Contato contato) {

		Collection<Telefone> result = new ArrayList<>();

		if (contato != null && contato.getId() != null) {

			final Query query = super.getEntityManager().createQuery("select t from Telefone t where t.contato.id =:idContato");

			query.setParameter("idContato", contato.getId());

			result = query.getResultList();
		}

		return result;
	}
}
