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
import edu.unicauca.apliweb.crud_biblioteca_java.persistence.Authors;
import edu.unicauca.apliweb.crud_biblioteca_java.persistence.Books;
import java.util.ArrayList;
import java.util.List;
import edu.unicauca.apliweb.crud_biblioteca_java.persistence.Userbooks;
import edu.unicauca.apliweb.crud_biblioteca_java.persistence.jpa.exceptions.IllegalOrphanException;
import edu.unicauca.apliweb.crud_biblioteca_java.persistence.jpa.exceptions.NonexistentEntityException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author User
 */
public class BooksJpaController implements Serializable {

    public BooksJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Books books) {
        if (books.getAuthorsList() == null) {
            books.setAuthorsList(new ArrayList<Authors>());
        }
        if (books.getUserbooksList() == null) {
            books.setUserbooksList(new ArrayList<Userbooks>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Authors> attachedAuthorsList = new ArrayList<Authors>();
            for (Authors authorsListAuthorsToAttach : books.getAuthorsList()) {
                authorsListAuthorsToAttach = em.getReference(authorsListAuthorsToAttach.getClass(), authorsListAuthorsToAttach.getIdA());
                attachedAuthorsList.add(authorsListAuthorsToAttach);
            }
            books.setAuthorsList(attachedAuthorsList);
            List<Userbooks> attachedUserbooksList = new ArrayList<Userbooks>();
            for (Userbooks userbooksListUserbooksToAttach : books.getUserbooksList()) {
                userbooksListUserbooksToAttach = em.getReference(userbooksListUserbooksToAttach.getClass(), userbooksListUserbooksToAttach.getUserbooksPK());
                attachedUserbooksList.add(userbooksListUserbooksToAttach);
            }
            books.setUserbooksList(attachedUserbooksList);
            em.persist(books);
            for (Authors authorsListAuthors : books.getAuthorsList()) {
                authorsListAuthors.getBooksList().add(books);
                authorsListAuthors = em.merge(authorsListAuthors);
            }
            for (Userbooks userbooksListUserbooks : books.getUserbooksList()) {
                Books oldBooksOfUserbooksListUserbooks = userbooksListUserbooks.getBooks();
                userbooksListUserbooks.setBooks(books);
                userbooksListUserbooks = em.merge(userbooksListUserbooks);
                if (oldBooksOfUserbooksListUserbooks != null) {
                    oldBooksOfUserbooksListUserbooks.getUserbooksList().remove(userbooksListUserbooks);
                    oldBooksOfUserbooksListUserbooks = em.merge(oldBooksOfUserbooksListUserbooks);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Books books) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Books persistentBooks = em.find(Books.class, books.getIdB());
            List<Authors> authorsListOld = persistentBooks.getAuthorsList();
            List<Authors> authorsListNew = books.getAuthorsList();
            List<Userbooks> userbooksListOld = persistentBooks.getUserbooksList();
            List<Userbooks> userbooksListNew = books.getUserbooksList();
            List<String> illegalOrphanMessages = null;
            for (Userbooks userbooksListOldUserbooks : userbooksListOld) {
                if (!userbooksListNew.contains(userbooksListOldUserbooks)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Userbooks " + userbooksListOldUserbooks + " since its books field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Authors> attachedAuthorsListNew = new ArrayList<Authors>();
            for (Authors authorsListNewAuthorsToAttach : authorsListNew) {
                authorsListNewAuthorsToAttach = em.getReference(authorsListNewAuthorsToAttach.getClass(), authorsListNewAuthorsToAttach.getIdA());
                attachedAuthorsListNew.add(authorsListNewAuthorsToAttach);
            }
            authorsListNew = attachedAuthorsListNew;
            books.setAuthorsList(authorsListNew);
            List<Userbooks> attachedUserbooksListNew = new ArrayList<Userbooks>();
            for (Userbooks userbooksListNewUserbooksToAttach : userbooksListNew) {
                userbooksListNewUserbooksToAttach = em.getReference(userbooksListNewUserbooksToAttach.getClass(), userbooksListNewUserbooksToAttach.getUserbooksPK());
                attachedUserbooksListNew.add(userbooksListNewUserbooksToAttach);
            }
            userbooksListNew = attachedUserbooksListNew;
            books.setUserbooksList(userbooksListNew);
            books = em.merge(books);
            for (Authors authorsListOldAuthors : authorsListOld) {
                if (!authorsListNew.contains(authorsListOldAuthors)) {
                    authorsListOldAuthors.getBooksList().remove(books);
                    authorsListOldAuthors = em.merge(authorsListOldAuthors);
                }
            }
            for (Authors authorsListNewAuthors : authorsListNew) {
                if (!authorsListOld.contains(authorsListNewAuthors)) {
                    authorsListNewAuthors.getBooksList().add(books);
                    authorsListNewAuthors = em.merge(authorsListNewAuthors);
                }
            }
            for (Userbooks userbooksListNewUserbooks : userbooksListNew) {
                if (!userbooksListOld.contains(userbooksListNewUserbooks)) {
                    Books oldBooksOfUserbooksListNewUserbooks = userbooksListNewUserbooks.getBooks();
                    userbooksListNewUserbooks.setBooks(books);
                    userbooksListNewUserbooks = em.merge(userbooksListNewUserbooks);
                    if (oldBooksOfUserbooksListNewUserbooks != null && !oldBooksOfUserbooksListNewUserbooks.equals(books)) {
                        oldBooksOfUserbooksListNewUserbooks.getUserbooksList().remove(userbooksListNewUserbooks);
                        oldBooksOfUserbooksListNewUserbooks = em.merge(oldBooksOfUserbooksListNewUserbooks);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = books.getIdB();
                if (findBooks(id) == null) {
                    throw new NonexistentEntityException("The books with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Books books;
            try {
                books = em.getReference(Books.class, id);
                books.getIdB();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The books with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Userbooks> userbooksListOrphanCheck = books.getUserbooksList();
            for (Userbooks userbooksListOrphanCheckUserbooks : userbooksListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Books (" + books + ") cannot be destroyed since the Userbooks " + userbooksListOrphanCheckUserbooks + " in its userbooksList field has a non-nullable books field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Authors> authorsList = books.getAuthorsList();
            for (Authors authorsListAuthors : authorsList) {
                authorsListAuthors.getBooksList().remove(books);
                authorsListAuthors = em.merge(authorsListAuthors);
            }
            em.remove(books);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Books> findBooksEntities() {
        return findBooksEntities(true, -1, -1);
    }

    public List<Books> findBooksEntities(int maxResults, int firstResult) {
        return findBooksEntities(false, maxResults, firstResult);
    }

    private List<Books> findBooksEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Books.class));
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

    public Books findBooks(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Books.class, id);
        } finally {
            em.close();
        }
    }

    public int getBooksCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Books> rt = cq.from(Books.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
