package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.Constants;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.ArrayList;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
        SessionFactory factory = Util.getSessionFactory();
        try (Session session = factory.openSession()) {
            session.beginTransaction();
            session.createSQLQuery(Constants.CREATE_TABLE_USER).executeUpdate();
            session.getTransaction().commit();
        }
    }

    @Override
    public void dropUsersTable() {
        SessionFactory factory = Util.getSessionFactory();
        try (Session session = factory.openSession()) {
            session.beginTransaction();
            session.createSQLQuery(Constants.DROP_TABLE_USER).executeUpdate();
            session.getTransaction().commit();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        SessionFactory factory = Util.getSessionFactory();
        try (Session session = factory.openSession()) {
            session.beginTransaction();
            session.save(new User(name, lastName, age));
            session.getTransaction().commit();
        }
    }

    @Override
    public void removeUserById(long id) {
        SessionFactory factory = Util.getSessionFactory();
        try (Session session = factory.openSession()) {
            session.beginTransaction();
            session.remove(session.get(User.class, id));
            session.getTransaction().commit();
        }
    }

    @Override
    public List<User> getAllUsers() {
        SessionFactory factory = Util.getSessionFactory();
        try (Session session = factory.openSession()) {
            session.beginTransaction();
            List<User> res = session.createQuery("from User", User.class).getResultList();
            session.getTransaction().commit();
            return res;
        }
    }

    @Override
    public void cleanUsersTable() {
        SessionFactory factory = Util.getSessionFactory();
        try (Session session = factory.openSession()) {
            session.beginTransaction();
            session.createQuery("DELETE from User").executeUpdate();
            session.getTransaction().commit();
        }
    }
}
