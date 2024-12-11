package gr.perisnik.cj.schoolapp.service.util;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

/**
 * Utility class for managing JPA EntityManager and EntityManagerFactory.
 * Ensures thread-safe usage of EntityManager instances using ThreadLocal.
 *
 * @author Peris Nik
 */
public class JPAHelper {

    private static EntityManagerFactory emf;
    private static final ThreadLocal<EntityManager> threadLocal = new ThreadLocal<>();

    /**
     * Private constructor to prevent instantiation.
     */
    private JPAHelper() {}

    /**
     * Provides a thread-safe instance of EntityManagerFactory.
     * Creates the factory if it doesn't already exist or is closed.
     *
     * @return the EntityManagerFactory instance
     */
    public static synchronized EntityManagerFactory getEntityManagerFactory() {
        if ((emf == null) || (!emf.isOpen())) {
            emf = Persistence.createEntityManagerFactory("schoolAppPU");
        }
        return emf;
    }

    /**
     * Provides a thread-local instance of EntityManager.
     * Creates a new EntityManager if one does not already exist for the thread or if it is closed.
     *
     * @return the EntityManager instance
     */
    public static EntityManager getEntityManager() {
        EntityManager em = threadLocal.get();
        if ((em == null) || (!em.isOpen())) {
            em = getEntityManagerFactory().createEntityManager();
            threadLocal.set(em);
        }
        return em;
    }

    /**
     * Closes the thread-local EntityManager if it exists and is open.
     * Removes the EntityManager from the ThreadLocal.
     */
    public static void closeEntityManager() {
        EntityManager em = threadLocal.get();
        if (em != null) {
            if (em.isOpen()) {
                em.close();
            }
            threadLocal.remove();
        }
    }

    /**
     * Closes the EntityManagerFactory if it exists and is open.
     */
    public static void closeEntityManagerFactory() {
        if (emf != null && emf.isOpen()) {
            emf.close();
        }
    }

    /**
     * Begins a transaction on the current thread-local EntityManager.
     * Ensures that the transaction is active before proceeding.
     */
    public static void beginTransaction() {
        EntityManager em = getEntityManager();
        if (!em.getTransaction().isActive()) {
            em.getTransaction().begin();
        }
    }

    /**
     * Commits the transaction on the current thread-local EntityManager.
     * Ensures that the transaction is active before committing.
     */
    public static void commitTransaction() {
        EntityManager em = getEntityManager();
        if (em.getTransaction().isActive()) {
            em.getTransaction().commit();
        }
    }

    /**
     * Rolls back the transaction on the current thread-local EntityManager.
     * Ensures that the transaction is active before rolling back.
     * Closes the EntityManager after rollback to prevent reuse of a potentially corrupted state.
     */
    public static void rollBackTransaction() {
        EntityManager em = getEntityManager();
        if (em.getTransaction().isActive()) {
            try {
                em.getTransaction().rollback();
            } catch (Exception e) {
                System.err.println("Transaction rollback failed: " + e.getMessage());
                e.printStackTrace();
            } finally {
                closeEntityManager(); // Clean up after rollback
            }
        }
    }
}