package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;

import java.util.List;

public class UserServiceImpl implements UserService {
    UserDao userDaoJDBC = new UserDaoJDBCImpl();
    UserDao userhibernate = new UserDaoHibernateImpl();


    public void createUsersTable() {
        userhibernate.createUsersTable();
    }

    public void dropUsersTable() {
        userhibernate.dropUsersTable();
    }

    public void saveUser(String name, String lastName, byte age) {
        userhibernate.saveUser(name, lastName, age);
    }

    public void removeUserById(long id) {
        userhibernate.removeUserById(id);
    }

    public List<User> getAllUsers() {
        return userhibernate.getAllUsers();
    }

    public void cleanUsersTable() {
        userhibernate.cleanUsersTable();
    }
}
