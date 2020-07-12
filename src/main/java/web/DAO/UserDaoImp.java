package web.DAO;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import web.model.User;


import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

    private SessionFactory sessionFactory;

    @Autowired
    public UserDaoImp(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public boolean addUser(User user) {
        try(Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.save(user);
            session.getTransaction().commit();
            return true;
        }catch (HibernateException e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean updateUserById(long id, User user) {
        try(Session session = sessionFactory.openSession()){
            session.beginTransaction();
            session.update("id", user);
            session.getTransaction().commit();
            return true;
        }catch (HibernateException e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteUserById(long id) {
        try(Session session = sessionFactory.openSession()){
            session.beginTransaction();
            Query<User> queryUser = session.createQuery("delete User user where user.id =: id");
            queryUser.setParameter("id", id);
            queryUser.executeUpdate();
            session.getTransaction().commit();
            return true;
        }catch (HibernateException e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public User getUserByName(String name) {
        try(Session session = sessionFactory.openSession()){
            session.beginTransaction();
            Query query = session.createQuery("select user from User user where user.name=:name", User.class);
            query.setParameter("name", name);
            User user = (User) query.getSingleResult();
            session.getTransaction().commit();
            return user;
        }catch (HibernateException e){
            e.printStackTrace();
            return null;
        }
    }

    public boolean isUserExist(String name){
        try(Session session = sessionFactory.openSession()){
            Query query = session.createQuery("select 1 from User user where user.name =: name");
            query.setParameter("name", name);
            return  query.uniqueResult() != null;
        }catch (HibernateException e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public User getUserById(long id) {
        try(Session session = sessionFactory.openSession()){
            session.beginTransaction();
            User user = (User) session.get(User.class, id);
            session.getTransaction().commit();
            return user;
        }catch (HibernateException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<User> getUsersList() {
        Session session =sessionFactory.openSession();
        String hql = "FROM User";
        Query query = session.createQuery(hql);
        List<User> userList = query.getResultList();
        return userList;
    }


}
