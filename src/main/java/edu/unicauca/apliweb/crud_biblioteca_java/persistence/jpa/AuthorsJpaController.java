/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.unicauca.apliweb.crud_biblioteca_java.persistence.jpa;

import edu.unicauca.apliweb.crud_biblioteca_java.persistence.Authors;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import edu.unicauca.apliweb.crud_biblioteca_java.persistence.Books;
import edu.unicauca.apliweb.crud_biblioteca_java.persistence.Users;
import edu.unicauca.apliweb.crud_biblioteca_java.persistence.jpa.exceptions.NonexistentEntityException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author User
 */
public class AuthorsJpaController implements Serializable {

    public AuthorsJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Authors authors) {
        if (authors.getBooksList() == null) {
            authors.setBooksList(new ArrayList<Books>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Books> attachedBooksList = new ArrayList<Books>();
            for (Books booksListBooksToAttach : authors.getBooksList()) {
                booksListBooksToAttach = em.getReference(booksListBooksToAttach.getClass(), booksListBooksToAttach.getIdB());
                attachedBooksList.add(booksListBooksToAttach);
            }
            authors.setBooksList(attachedBooksList);
            em.persist(authors);
            for (Books booksListBooks : authors.getBooksList()) {
                booksListBooks.getAuthorsList().add(authors);
                booksListBooks = em.merge(booksListBooks);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Authors authors) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Authors persistentAuthors = em.find(Authors.class, authors.getIdA());
            List<Books> booksListOld = persistentAuthors.getBooksList();            
            authors.setBooksList(booksListOld);
            authors = em.merge(authors);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = authors.getIdA();
                if (findAuthors(id) == null) {
                    throw new NonexistentEntityException("The authors with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Authors authors;
            try {
                authors = em.getReference(Authors.class, id);
                authors.getIdA();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The authors with id " + id + " no longer exists.", enfe);
            }
            List<Books> booksList = authors.getBooksList();
            for (Books booksListBooks : booksList) {
                booksListBooks.getAuthorsList().remove(authors);
                booksListBooks = em.merge(booksListBooks);
            }
            em.remove(authors);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Authors> findAuthorsEntities() {
        return findAuthorsEntities(true, -1, -1);
    }

    public List<Authors> findAuthorsEntities(int maxResults, int firstResult) {
        return findAuthorsEntities(false, maxResults, firstResult);
    }

    private List<Authors> findAuthorsEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Authors.class));
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

    public Authors findAuthors(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Authors.class, id);
        } finally {
            em.close();
        }
    }

    public int getAuthorsCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Authors> rt = cq.from(Authors.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public void create(Users us) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
