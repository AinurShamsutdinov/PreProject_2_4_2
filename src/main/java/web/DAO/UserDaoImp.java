package web.DAO;

import org.hibernate.HibernateException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import web.model.User;


import javax.persistence.*;
import java.util.List;

@Repository
@Transactional
public class UserDaoImp implements UserDao {

    private EntityManagerFactory entityManagerFactory;

    @PersistenceUnit
    public void setEntityManager(EntityManagerFactory entityManagerFactory){
        this.entityManagerFactory = entityManagerFactory;
    }

    @Transactional(readOnly = false)
    public void flush(){
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.flush();
    }

    @Override
    public void addUser(User user) {
        try {
            EntityManager entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();
            entityManager.persist(user);
            entityManager.getTransaction().commit();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void updateUserById(long id, User user) {
        try{
            EntityManager entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();
            entityManager.merge(user);
            entityManager.getTransaction().commit();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void deleteUserById(long id) {
        try{
            EntityManager entityManager = entityManagerFactory.createEntityManager();
            User user = entityManager.find(User.class, id);
            if (user != null) {
                entityManager.getTransaction().begin();
                entityManager.remove(user);
                entityManager.getTransaction().commit();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public User getUserByName(String name) {
        try{
            EntityManager entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();
            TypedQuery<User> query = entityManager.createQuery("select user from User user where user.name=:name", User.class);
            query.setParameter("name", name);
            User user = (User) query.getSingleResult();
            entityManager.getTransaction().commit();
            return user;
        }catch (HibernateException e){
            e.printStackTrace();
            return null;
        }
    }

    public boolean isUserExist(String name){
        try{
            EntityManager entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();
            TypedQuery<User> query = entityManager.createQuery("select user from User user where user.name=:name", User.class);
            query.setParameter("name", name);
            User user = query.getSingleResult();
            entityManager.getTransaction().commit();
            return  user != null;
        }catch (NoResultException e){
            return false;
        }
    }

    @Override
    public User getUserById(long id) {
        try{
            EntityManager entityManager = entityManagerFactory.createEntityManager();
            return entityManager.find(User.class, id);
        }catch (HibernateException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<User> getUsersList() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        TypedQuery<User> query = entityManager.createQuery("select user from User user", User.class);
        List<User> userList = query.getResultList();
        return userList;
    }




}
