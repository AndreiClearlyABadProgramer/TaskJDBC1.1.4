package jm.task.core.jdbc.service;


import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.model.User;
import java.util.List;

public class UserServiceImpl implements UserService {
    public void createUsersTable() {
        UserDao dao = new UserDaoHibernateImpl();
        dao.createUsersTable();
    }

    public void dropUsersTable() {
        UserDao dao = new UserDaoHibernateImpl();
        dao.dropUsersTable();
    }

    public void saveUser(String name, String lastName, byte age) {
        UserDao dao = new UserDaoHibernateImpl();
        dao.saveUser(name, lastName, age);
    }

    public void removeUserById(long id) {
        UserDao dao = new UserDaoHibernateImpl();
        dao.removeUserById(id);
    }

    public List<User> getAllUsers() {
        UserDao dao = new UserDaoHibernateImpl();
        return dao.getAllUsers();
    }

    public void cleanUsersTable() {
        UserDao dao = new UserDaoHibernateImpl();
        dao.cleanUsersTable();
    }
}
