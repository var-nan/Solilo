package main.solilo.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;

import main.solilo.bean.Quicky;
import main.solilo.entity.QuickyEntity;
import main.solilo.exceptions.InvalidKeyException;
import main.solilo.utilities.JPAUtility;
import net.bytebuddy.implementation.bytecode.Throw;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class QuickyDAOImpl {

	//@Override
	public static boolean addQuicky(Quicky quicky) throws Exception {
		// TODO Auto-generated method stub
		EntityManagerFactory entityManagerFactory;
		EntityManager entityManager = null;
		boolean isAdded;
		
		try {
			entityManagerFactory = JPAUtility.getEntityManagerFactory();
			entityManager = entityManagerFactory.createEntityManager();
			// create transaction
			entityManager.getTransaction().begin();
			// TODO: check if it works?
			//Quicky temp_quicky = new Quicky();
			QuickyEntity quickyEntity = new QuickyEntity(quicky);
			entityManager.persist(quickyEntity);
			entityManager.getTransaction().commit();
			isAdded = true;
			
		} catch (Exception e) {
			// TODO: handle exception
			isAdded = false;
			throw new Exception();

		} finally {
			if (entityManager != null && entityManager.isOpen())
				entityManager.close();
			//return isAdded;
		}
		return isAdded;
	}

	//@Override
	public static boolean deleteQuicky(String time)
			throws InvalidKeyException, RuntimeException {
		// TODO Auto-generated method stub
		// first find object, then delete it
		EntityManagerFactory entityManagerFactory;
		EntityManager entityManager = null;
		QuickyEntity quickyEntity;
		boolean isDeleted;
		
		try {
			entityManagerFactory = JPAUtility.getEntityManagerFactory();
			entityManager = entityManagerFactory.createEntityManager();
			
			quickyEntity = entityManager.find(QuickyEntity.class, time);
			
			if (quickyEntity == null) {
				// just stop the program? or return false
				throw new InvalidKeyException(time);
			}
			
			// proceed to delete
			entityManager.getTransaction().begin();
			entityManager.remove(quickyEntity);
			entityManager.getTransaction().commit();
			isDeleted = true;
			
		}catch (InvalidKeyException ike) {
			throw new InvalidKeyException("hello");

		} catch (Exception e) {
			// TODO: handle exception
			isDeleted = false;
			throw new RuntimeException();

		} finally {
			if (entityManager != null && entityManager.isOpen())
				entityManager.close();
		}
		return isDeleted;
	}

	//@Override
	public static boolean updateQuicky(Quicky quicky)
			throws InvalidKeyException, RuntimeException {
		// TODO Auto-generated method stub
		// get quicky with time
		EntityManagerFactory entityManagerFactory = null;
		EntityManager entityManager = null;
		QuickyEntity quickyEntity; 
		boolean isUpdated;
		
		try {
			entityManagerFactory = JPAUtility.getEntityManagerFactory();
			entityManager = entityManagerFactory.createEntityManager();
			String time = quicky.getCreated();
			quickyEntity = entityManager.find(QuickyEntity.class,time);
			
			if(quickyEntity == null) {
				throw new InvalidKeyException(quicky.getCreated());
			}
			
			entityManager.getTransaction().begin();
			// edit only the message and visibility
			quickyEntity.setMessage(quicky.getMessage());
			quickyEntity.setVisibile(quicky.isVisible());
			quickyEntity.setModified(true);
			
			//entityManager.persist(quickyEntity);
			entityManager.getTransaction().commit();
			
			isUpdated = true; 
			
		} catch (InvalidKeyException ike) {
			throw new InvalidKeyException(quicky.getCreated());

		} catch (Throwable exp) {
			isUpdated = false;
			throw new RuntimeException(exp);
			
		} finally {
			if (entityManager != null && entityManager.isOpen()) 
				entityManager.close();
		}
		
		return isUpdated;
	}

	//@Override
	public static Quicky getQuicky(String time) throws InvalidKeyException, RuntimeException {
		// TODO Auto-generated method stub
		EntityManagerFactory entityManagerFactory;
		EntityManager entityManager = null;
		QuickyEntity quickyEntity;
		Quicky quicky;
		
		try {
			entityManagerFactory = JPAUtility.getEntityManagerFactory();
			entityManager = entityManagerFactory.createEntityManager();
			
			quickyEntity = entityManager.find(QuickyEntity.class, time);
			
			if (quickyEntity == null) {
				throw new InvalidKeyException(time);
			}
			
			quicky = new Quicky(quickyEntity.getCreated(), 
								quickyEntity.getMessage(), 
								quickyEntity.isVisible(), 
								quickyEntity.isModified());
			
		}catch (InvalidKeyException ike) {
			throw new InvalidKeyException("hello");

		} catch (Throwable e) {
			// TODO: handle exception
			throw new RuntimeException(e);
			
		} finally {

			if (entityManager != null && entityManager.isOpen())
				entityManager.close();
			
		}
		return quicky;
		
	}

	public static List<QuickyEntity> getQuickyEntities(int n) throws RuntimeException{
		List<QuickyEntity> quickies;
		EntityManagerFactory entityManagerFactory = null;
		EntityManager entityManager = null;

		try {
			String subQuery = "SELECT k FROM QuickyEntity k ORDER BY k.created DESC";
			//String mainQuery = "SELECT * FROM ("+ subQuery +") ORDER BY created";
			//String tempQuery = "SELECT k FROM quicky k";

			entityManagerFactory = JPAUtility.getEntityManagerFactory();
			entityManager = entityManagerFactory.createEntityManager();

			//entityManager.getTransaction().begin();
			Query query = entityManager.createQuery(subQuery);
			query.setMaxResults(n);
			quickies = query.getResultList();
			// convert to quickies in service layer?
			//entityManager.getTransaction().commit();

		} catch (Throwable ex) {
			throw new RuntimeException(ex);

		} finally {
			// close entitymanagerfactory
			if (entityManager != null && entityManager.isOpen())
				entityManager.close();
			/*
			if (entityManagerFactory != null && entityManagerFactory.isOpen())
				entityManagerFactory.close();
			*/
		}
		return quickies;

	}

}
