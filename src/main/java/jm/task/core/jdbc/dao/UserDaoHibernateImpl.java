package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Utils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.Query;
import java.util.List;


public class UserDaoHibernateImpl implements UserDao {
    private static final SessionFactory sessionFactory = Utils.getSessionFactory();

    public UserDaoHibernateImpl() {
    }


    @Override
    public void createUsersTable() {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        Query query = session.createSQLQuery(
                "CREATE TABLE kata_hibernate (id INT PRIMARY KEY AUTO_INCREMENT,name VARCHAR(20),lastname VARCHAR(20),age INT)");
        query.executeUpdate();
        session.getTransaction().commit();
    }

    @Override
    public void dropUsersTable() {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            Query query = session.createSQLQuery("DROP TABLE IF EXISTS kata_hibernate");
            query.executeUpdate();
            session.getTransaction().commit();
            System.out.println("Таблица удалена");
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try {
            Session session = sessionFactory.getCurrentSession();
            session.beginTransaction();
            User user = new User(name, lastName, age);
            session.save(user);
            session.getTransaction().commit();
        } finally {
            System.out.println("User " + name + " успешно добавлен");
        }
    }

    @Override
    public void removeUserById(long id) {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            Query query = session.createSQLQuery("DELETE FROM kata_hibernate WHERE id LIKE(" + id + ")");
            session.getTransaction().commit();
            System.out.println("User под id - " + id + " успешно удален");
        }
    }

    @Override
    public List<User> getAllUsers() {

        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            List<User> users = session.createQuery("from User").getResultList();
            session.getTransaction().commit();
            return users;
        }

    }

    @Override
    public void cleanUsersTable() {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            Query query = session.createSQLQuery("DELETE FROM kata_hibernate");
            query.executeUpdate();
            session.getTransaction().commit();
        }
    }
}
