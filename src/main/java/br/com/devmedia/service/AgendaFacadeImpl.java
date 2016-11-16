package br.com.devmedia.service;

import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;

import br.com.devmedia.agenda.model.dao.ContatoDao;
import br.com.devmedia.agenda.model.dao.GrupoDao;
import br.com.devmedia.agenda.model.dao.TelefoneDao;
import br.com.devmedia.agenda.model.entidades.Contato;
import br.com.devmedia.agenda.model.entidades.Grupo;
import br.com.devmedia.agenda.model.entidades.Telefone;
import br.com.devmedia.agenda.util.FabricaDeEntityManager;

public class AgendaFacadeImpl implements AgendaFacade {

	private EntityManager entityManager;

	private final ContatoDao contatoDao;

	private final GrupoDao grupoDao;

	private final TelefoneDao telefoneDao;

	public AgendaFacadeImpl() {

		this.setEntityManager(FabricaDeEntityManager.getEntityManager());

		this.contatoDao = new ContatoDao(this.getEntityManager());

		this.telefoneDao = new TelefoneDao(this.getEntityManager());

		this.grupoDao = new GrupoDao(this.getEntityManager());

	}

	@Override
	public List<Grupo> buscarTodosGrupos() {

		return this.grupoDao.buscarTodos();

	}

	@Override
	public List<Contato> buscarTodosContatos() {

		this.clear();

		return this.contatoDao.buscarTodos();
	}

	@Override
	public void salvarContato(final Contato contato) {

		try {

			this.beginTransaction();

			// Vinculando os contatos
			if (contato.getTelefones() != null) {

				contato.getTelefones().stream().forEach(t -> t.setContato(contato));

			}

			this.contatoDao.salvar(contato);

			this.commitTransaction();

		} catch (final Exception e) {

			this.rollbackTransaction();

			throw e;
		}

	}

	@Override
	public void excluirTelefone(final Telefone telefone) {

		try {
			if (telefone != null && telefone.getId() != null) {

				this.beginTransaction();

				final Telefone telefoneParaExclusao = this.telefoneDao.selecionarPorId(telefone.getId());

				this.telefoneDao.excluir(telefoneParaExclusao.getId());

				this.commitTransaction();

			}
		} catch (final Exception e) {

			this.rollbackTransaction();

			throw e;
		}

	}

	public void excluirContato(final Contato contato) {

		if (contato != null && contato.getId() != null) {
			try {

				this.beginTransaction();

				// Paso 1: Excluindo os telefones, poderia ser feito via cascade, porém desta forma ficou mais didático
				final Collection<Telefone> telefoneParaExclusao = this.telefoneDao.buscarPorContato(contato);

				telefoneParaExclusao.stream().forEach(t -> this.telefoneDao.excluir(t.getId()));

				// Passo Final: Excluindo Contato, o Endereço será excluído em cascada juntamente
				this.contatoDao.excluir(contato.getId());

				this.commitTransaction();
			} catch (Exception e) {

				this.rollbackTransaction();

				throw e;
			}
		}

	}

	@Override
	public void flush() {

		this.getEntityManager().flush();
	}

	@Override
	public void clear() {

		this.getEntityManager().clear();
	}
	
	/**
	 * Retorna o valor do atributo <code>entityManager</code>
	 *
	 * @return <code>EntityManager</code>
	 */
	private EntityManager getEntityManager() {

		return this.entityManager;
	}

	/**
	 * Define o valor do atributo <code>entityManager</code>.
	 *
	 * @param entityManager
	 */
	private void setEntityManager(final EntityManager entityManager) {

		this.entityManager = entityManager;
	}

	private void beginTransaction() {

		this.getEntityManager().getTransaction().begin();
	}

	private void commitTransaction() {

		this.getEntityManager().getTransaction().commit();
	}

	private void rollbackTransaction() {

		this.getEntityManager().getTransaction().rollback();
	}

}
