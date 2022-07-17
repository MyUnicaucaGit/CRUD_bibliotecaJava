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
import edu.unicauca.apliweb.crud_biblioteca_java.persistence.Userbooks;
import edu.unicauca.apliweb.crud_biblioteca_java.persistence.Users;
import edu.unicauca.apliweb.crud_biblioteca_java.persistence.jpa.exceptions.IllegalOrphanException;
import edu.unicauca.apliweb.crud_biblioteca_java.persistence.jpa.exceptions.NonexistentEntityException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author User
 */
public class UsersJpaController implements Serializable {

    public UsersJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Users users) {
        if (users.getUserbooksList() == null) {
            users.setUserbooksList(new ArrayList<Userbooks>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Userbooks> attachedUserbooksList = new ArrayList<Userbooks>();
            for (Userbooks userbooksListUserbooksToAttach : users.getUserbooksList()) {
                userbooksListUserbooksToAttach = em.getReference(userbooksListUserbooksToAttach.getClass(), userbooksListUserbooksToAttach.getUserbooksPK());
                attachedUserbooksList.add(userbooksListUserbooksToAttach);
            }
            users.setUserbooksList(attachedUserbooksList);
            em.persist(users);
            for (Userbooks userbooksListUserbooks : users.getUserbooksList()) {
                Users oldUsersOfUserbooksListUserbooks = userbooksListUserbooks.getUsers();
                userbooksListUserbooks.setUsers(users);
                userbooksListUserbooks = em.merge(userbooksListUserbooks);
                if (oldUsersOfUserbooksListUserbooks != null) {
                    oldUsersOfUserbooksListUserbooks.getUserbooksList().remove(userbooksListUserbooks);
                    oldUsersOfUserbooksListUserbooks = em.merge(oldUsersOfUserbooksListUserbooks);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Users users) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Users persistentUsers = em.find(Users.class, users.getIdU());
            List<Userbooks> userbooksListOld = persistentUsers.getUserbooksList();
            List<Userbooks> userbooksListNew = users.getUserbooksList();
            List<String> illegalOrphanMessages = null;
            for (Userbooks userbooksListOldUserbooks : userbooksListOld) {
                if (!userbooksListNew.contains(userbooksListOldUserbooks)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Userbooks " + userbooksListOldUserbooks + " since its users field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Userbooks> attachedUserbooksListNew = new ArrayList<Userbooks>();
            for (Userbooks userbooksListNewUserbooksToAttach : userbooksListNew) {
                userbooksListNewUserbooksToAttach = em.getReference(userbooksListNewUserbooksToAttach.getClass(), userbooksListNewUserbooksToAttach.getUserbooksPK());
                attachedUserbooksListNew.add(userbooksListNewUserbooksToAttach);
            }
            userbooksListNew = attachedUserbooksListNew;
            users.setUserbooksList(userbooksListNew);
            users = em.merge(users);
            for (Userbooks userbooksListNewUserbooks : userbooksListNew) {
                if (!userbooksListOld.contains(userbooksListNewUserbooks)) {
                    Users oldUsersOfUserbooksListNewUserbooks = userbooksListNewUserbooks.getUsers();
                    userbooksListNewUserbooks.setUsers(users);
                    userbooksListNewUserbooks = em.merge(userbooksListNewUserbooks);
                    if (oldUsersOfUserbooksListNewUserbooks != null && !oldUsersOfUserbooksListNewUserbooks.equals(users)) {
                        oldUsersOfUserbooksListNewUserbooks.getUserbooksList().remove(userbooksListNewUserbooks);
                        oldUsersOfUserbooksListNewUserbooks = em.merge(oldUsersOfUserbooksListNewUserbooks);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = users.getIdU();
                if (findUsers(id) == null) {
                    throw new NonexistentEntityException("The users with id " + id + " no longer exists.");
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
            Users users;
            try {
                users = em.getReference(Users.class, id);
                users.getIdU();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The users with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Userbooks> userbooksListOrphanCheck = users.getUserbooksList();
            for (Userbooks userbooksListOrphanCheckUserbooks : userbooksListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Users (" + users + ") cannot be destroyed since the Userbooks " + userbooksListOrphanCheckUserbooks + " in its userbooksList field has a non-nullable users field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(users);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Users> findUsersEntities() {
        return findUsersEntities(true, -1, -1);
    }

    public List<Users> findUsersEntities(int maxResults, int firstResult) {
        return findUsersEntities(false, maxResults, firstResult);
    }

    private List<Users> findUsersEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Users.class));
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

    public Users findUsers(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Users.class, id);
        } finally {
            em.close();
        }
    }

    public int getUsersCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Users> rt = cq.from(Users.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
