package br.com.devmedia.teste;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import br.com.devmedia.agenda.model.entidades.Contato;

public class ExemploHibernate {

	public static void main(String[] args) {

		Configuration config = new Configuration();
		config.configure("hibernate.cfg.xml");
		config.addAnnotatedClass(Contato.class);

		ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(config.getProperties()).build();
		SessionFactory sessionFactory = config.buildSessionFactory(serviceRegistry);

		Session session = sessionFactory.openSession();
		listarProdutos(session);
		session.close();
		sessionFactory.close();
	}

	private static void listarProdutos(Session session) {

		@SuppressWarnings("unchecked")

		final List<Contato> contatos = session.createQuery("from Contato").getResultList();

		contatos.stream().forEach(System.out::println);

	}
}
