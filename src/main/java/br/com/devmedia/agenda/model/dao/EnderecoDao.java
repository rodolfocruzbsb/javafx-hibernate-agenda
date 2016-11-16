package br.com.devmedia.agenda.model.dao;

import javax.persistence.EntityManager;

import br.com.devmedia.agenda.model.entidades.Endereco;

/**
 * <p>
 * <b>Title:</b> EnderecoDao.java
 * </p>
 * 
 * <p>
 * <b>Description:</b> DAO para trabalhar com a entidade Endereco
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
public class EnderecoDao extends GenericDaoImpl<Endereco> {

	public EnderecoDao( EntityManager entityManager ) {

		super(entityManager);
	}
}
