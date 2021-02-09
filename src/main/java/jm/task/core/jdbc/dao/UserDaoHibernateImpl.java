package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import java.util.ArrayList;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
        Util util = new Util();
        Session session = null;
        try {
            session = util.getSessionFactory().openSession();
            session.beginTransaction();
            session.createSQLQuery("create table Users").executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e){
            if(session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
        } finally {
            session.close();
        }
    }

    @Override
    public void dropUsersTable() {
        Util util = new Util();
        Session session = null;
        try {
            session = util.getSessionFactory().openSession();
            session.beginTransaction();
            session.createSQLQuery("drop table if exists Users").executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e){
            if(session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
        } finally {
            session.close();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Util util = new Util();
        User user = new User(name, lastName, age);
        Long userID;
        Session session = null;
        try {
            session = util.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(user);
            System.out.println("User с именем - " + name + " добавлен в базу данных");
            session.getTransaction().commit();
        } catch (Exception e){
            if(session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
        } finally {
            session.close();
        }


    }

    @Override
    public void removeUserById(long id) {
        Util util = new Util();
        Session session = null;
        try{
            session = util.getSessionFactory().openSession();
            session.beginTransaction();
            User user = (User) session.get(User.class, id);
            session.delete(user);
            session.getTransaction().commit();
        } catch (Exception e) {
            if(session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
        } finally {
            session.close();
        }
    }

    @Override
    public List<User> getAllUsers() {
        Util util = new Util();
        Session session = null;
        List<User> users = new ArrayList<>();
        try {
            session = util.getSessionFactory().openSession();
            session.beginTransaction();
            users = session.createCriteria(User.class).list();
            session.getTransaction().commit();
        } catch (Exception e){
            if(session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
        } finally {
            session.close();
        }
        return users;
    }

    @Override
    public void cleanUsersTable() {
        Util util = new Util();
        Session session = null;
        try {
            session = util.getSessionFactory().openSession();
            session.beginTransaction();
            session.createSQLQuery("delete from Users").executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e){
            if(session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
        } finally {
            session.close();
        }

    }
}
