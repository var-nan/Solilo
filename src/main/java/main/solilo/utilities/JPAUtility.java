package main.solilo.utilities;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAUtility {
	private static EntityManagerFactory emf;
	
	public static EntityManagerFactory getEntityManagerFactory() {
		if (emf == null)
			emf = buildEntityManagerFactory();
		return emf;
	}
	
	private static EntityManagerFactory buildEntityManagerFactory() {
		try {
			emf = Persistence.createEntityManagerFactory("solilo");
		}
		catch (Throwable exp) {
			// TODO: add to log
			System.err.println("Unable to create entity manager Factory");
			throw new RuntimeException(exp);
		}
		return emf;
	}
	
	public static void closeEntityManagerFactory() {
		if (emf != null && emf.isOpen())
			emf.close();
	}
}
