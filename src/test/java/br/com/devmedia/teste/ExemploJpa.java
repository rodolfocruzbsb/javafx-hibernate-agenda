package br.com.devmedia.teste;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.devmedia.agenda.model.entidades.Contato;
import br.com.devmedia.agenda.util.FabricaDeEntityManager;

public class ExemploJpa {

	public static void main(String[] args) {

		EntityManager entityManager = null;
		try {
			entityManager = FabricaDeEntityManager.getEntityManager();
			entityManager.getTransaction().begin();
			listarProdutos(entityManager);
			entityManager.getTransaction().commit();
		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			if (entityManager != null && entityManager.isOpen())
				entityManager.close();

		}

		System.out.println("FIM..........");
	}

	private static void listarProdutos(EntityManager entityManager) {

		@SuppressWarnings("unchecked")

		final List<Contato> contatos = entityManager.createQuery("from Contato").getResultList();

		contatos.stream().forEach(System.out::println);

	}
}
