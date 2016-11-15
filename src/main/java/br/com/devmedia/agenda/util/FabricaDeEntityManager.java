package br.com.devmedia.agenda.util;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public class FabricaDeEntityManager {

	public static final String PERSISTENCE_UNIT = "agendaHibernatePU";

	protected static EntityManager entityManager;

	public static EntityManager getEntityManager() {

		if (entityManager == null) {

			javax.persistence.EntityManagerFactory factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT);

			entityManager = factory.createEntityManager();
		}
		return entityManager;
	}

	public static void setEntityManager(EntityManager entityManager) {

		FabricaDeEntityManager.entityManager = entityManager;
	}
}
