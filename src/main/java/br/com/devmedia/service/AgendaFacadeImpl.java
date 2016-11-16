package br.com.devmedia.service;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.devmedia.agenda.model.dao.ContatoDao;
import br.com.devmedia.agenda.model.dao.GrupoDao;
import br.com.devmedia.agenda.model.dao.TelefoneDao;
import br.com.devmedia.agenda.model.entidades.Contato;
import br.com.devmedia.agenda.util.FabricaDeEntityManager;

public class AgendaFacadeImpl implements AgendaFacade {

	private EntityManager entityManager;

	private ContatoDao contatoDao;

	private GrupoDao grupoDao;

	private TelefoneDao telefoneDao;

	public AgendaFacadeImpl() {

		this.setEntityManager(FabricaDeEntityManager.getEntityManager());

		this.contatoDao = new ContatoDao(getEntityManager());

		this.telefoneDao = new TelefoneDao(getEntityManager());

		this.grupoDao = new GrupoDao(getEntityManager());
	}

	@Override
	public List<Contato> buscarTodosContatos() {

		this.getContatoDao().getEntityManager().clear();

		return this.getContatoDao().buscarTodos();
	}

	@Override
	public void salvarContato(Contato contato) {


		try {
			this.contatoDao.getEntityManager().getTransaction().begin();
			this.contatoDao.salvar(contato);
			this.contatoDao.getEntityManager().getTransaction().commit();

			if (contato != null) {
				System.out.println(contato);
				
				if (contato.getTelefones() != null)
					contato.getTelefones().stream().forEach(System.out::println);
				
				if (contato.getGrupos() != null)
					contato.getGrupos().stream().forEach(System.out::println);
			}
			
		} catch (Exception e) {

			this.contatoDao.getEntityManager().getTransaction().rollback();
			throw e;
		}

	}

	/**
	 * Retorna o valor do atributo <code>contatoDao</code>
	 *
	 * @return <code>ContatoDao</code>
	 */
	public ContatoDao getContatoDao() {

		return contatoDao;
	}

	/**
	 * Define o valor do atributo <code>contatoDao</code>.
	 *
	 * @param contatoDao
	 */
	public void setContatoDao(ContatoDao contatoDao) {

		this.contatoDao = contatoDao;
	}

	/**
	 * Retorna o valor do atributo <code>entityManager</code>
	 *
	 * @return <code>EntityManager</code>
	 */
	public EntityManager getEntityManager() {

		return entityManager;
	}

	/**
	 * Define o valor do atributo <code>entityManager</code>.
	 *
	 * @param entityManager
	 */
	public void setEntityManager(EntityManager entityManager) {

		this.entityManager = entityManager;
	}

	public void beginTransaction() {

		this.getEntityManager().getTransaction().begin();
	}

	public void commitTransaction() {

		this.getEntityManager().getTransaction().commit();
	}

	public void rollbackTransaction() {

		this.getEntityManager().getTransaction().rollback();
	}

	public void flush() {

		this.getEntityManager().flush();
	}

	public void clear() {

		this.getEntityManager().clear();
	}

}
