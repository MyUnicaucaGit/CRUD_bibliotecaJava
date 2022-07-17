/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.unicauca.apliweb.crud_biblioteca_java.persistence.jpa;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import edu.unicauca.apliweb.crud_biblioteca_java.persistence.Users;
import edu.unicauca.apliweb.crud_biblioteca_java.persistence.Books;
import edu.unicauca.apliweb.crud_biblioteca_java.persistence.Userbooks;
import edu.unicauca.apliweb.crud_biblioteca_java.persistence.UserbooksPK;
import edu.unicauca.apliweb.crud_biblioteca_java.persistence.jpa.exceptions.NonexistentEntityException;
import edu.unicauca.apliweb.crud_biblioteca_java.persistence.jpa.exceptions.PreexistingEntityException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author User
 */
public class UserbooksJpaController implements Serializable {

    public UserbooksJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Userbooks userbooks) throws PreexistingEntityException, Exception {
        if (userbooks.getUserbooksPK() == null) {
            userbooks.setUserbooksPK(new UserbooksPK());
        }
        userbooks.getUserbooksPK().setUserId(userbooks.getUsers().getIdU());
        userbooks.getUserbooksPK().setBookId(userbooks.getBooks().getIdB());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Users users = userbooks.getUsers();
            if (users != null) {
                users = em.getReference(users.getClass(), users.getIdU());
                userbooks.setUsers(users);
            }
            Books books = userbooks.getBooks();
            if (books != null) {
                books = em.getReference(books.getClass(), books.getIdB());
                userbooks.setBooks(books);
            }
            em.persist(userbooks);
            if (users != null) {
                users.getUserbooksList().add(userbooks);
                users = em.merge(users);
            }
            if (books != null) {
                books.getUserbooksList().add(userbooks);
                books = em.merge(books);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findUserbooks(userbooks.getUserbooksPK()) != null) {
                throw new PreexistingEntityException("Userbooks " + userbooks + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Userbooks userbooks) throws NonexistentEntityException, Exception {
        userbooks.getUserbooksPK().setUserId(userbooks.getUsers().getIdU());
        userbooks.getUserbooksPK().setBookId(userbooks.getBooks().getIdB());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Userbooks persistentUserbooks = em.find(Userbooks.class, userbooks.getUserbooksPK());
            Users usersOld = persistentUserbooks.getUsers();
            Users usersNew = userbooks.getUsers();
            Books booksOld = persistentUserbooks.getBooks();
            Books booksNew = userbooks.getBooks();
            if (usersNew != null) {
                usersNew = em.getReference(usersNew.getClass(), usersNew.getIdU());
                userbooks.setUsers(usersNew);
            }
            if (booksNew != null) {
                booksNew = em.getReference(booksNew.getClass(), booksNew.getIdB());
                userbooks.setBooks(booksNew);
            }
            userbooks = em.merge(userbooks);
            if (usersOld != null && !usersOld.equals(usersNew)) {
                usersOld.getUserbooksList().remove(userbooks);
                usersOld = em.merge(usersOld);
            }
            if (usersNew != null && !usersNew.equals(usersOld)) {
                usersNew.getUserbooksList().add(userbooks);
                usersNew = em.merge(usersNew);
            }
            if (booksOld != null && !booksOld.equals(booksNew)) {
                booksOld.getUserbooksList().remove(userbooks);
                booksOld = em.merge(booksOld);
            }
            if (booksNew != null && !booksNew.equals(booksOld)) {
                booksNew.getUserbooksList().add(userbooks);
                booksNew = em.merge(booksNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                UserbooksPK id = userbooks.getUserbooksPK();
                if (findUserbooks(id) == null) {
                    throw new NonexistentEntityException("The userbooks with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(UserbooksPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Userbooks userbooks;
            try {
                userbooks = em.getReference(Userbooks.class, id);
                userbooks.getUserbooksPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The userbooks with id " + id + " no longer exists.", enfe);
            }
            Users users = userbooks.getUsers();
            if (users != null) {
                users.getUserbooksList().remove(userbooks);
                users = em.merge(users);
            }
            Books books = userbooks.getBooks();
            if (books != null) {
                books.getUserbooksList().remove(userbooks);
                books = em.merge(books);
            }
            em.remove(userbooks);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Userbooks> findUserbooksEntities() {
        return findUserbooksEntities(true, -1, -1);
    }

    public List<Userbooks> findUserbooksEntities(int maxResults, int firstResult) {
        return findUserbooksEntities(false, maxResults, firstResult);
    }

    private List<Userbooks> findUserbooksEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Userbooks.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Userbooks findUserbooks(UserbooksPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Userbooks.class, id);
        } finally {
            em.close();
        }
    }

    public int getUserbooksCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Userbooks> rt = cq.from(Userbooks.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
