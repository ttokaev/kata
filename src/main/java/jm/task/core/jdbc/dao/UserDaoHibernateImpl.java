package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.Constants;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {

    }

    @Override
    public void createUsersTable() {
        Transaction transaction = null;
        SessionFactory factory = Util.getSessionFactory();
        try (Session session = factory.openSession()) {
            transaction = session.beginTransaction();
            session.createSQLQuery(Constants.CREATE_TABLE_USER).executeUpdate();
            transaction.commit();
        }
        catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException(e);
        }
    }

    @Override
    public void dropUsersTable() {
        Transaction transaction = null;
        SessionFactory factory = Util.getSessionFactory();
        try (Session session = factory.openSession()) {
            transaction = session.beginTransaction();
            session.createSQLQuery(Constants.DROP_TABLE_USER).executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException(e);
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Transaction transaction = null;
        SessionFactory factory = Util.getSessionFactory();
        try (Session session = factory.openSession()) {
            transaction = session.beginTransaction();
            session.save(new User(name, lastName, age));
            transaction.commit();
        }
        catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException(e);
        }
    }

    @Override
    public void removeUserById(long id) {
        Transaction transaction = null;
        SessionFactory factory = Util.getSessionFactory();
        try (Session session = factory.openSession()) {
            transaction = session.beginTransaction();
            session.remove(session.get(User.class, id));
            transaction.commit();
        }
        catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<User> getAllUsers() {
        Transaction transaction = null;
        SessionFactory factory = Util.getSessionFactory();
        try (Session session = factory.openSession()) {
            transaction = session.beginTransaction();
            List<User> res = session.createQuery("from User", User.class).getResultList();
            transaction.commit();
            return res;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException(e);
        }
    }

    @Override
    public void cleanUsersTable() {
        Transaction transaction = null;
        SessionFactory factory = Util.getSessionFactory();
        try (Session session = factory.openSession()) {
            transaction = session.beginTransaction();
            session.createQuery("DELETE from User").executeUpdate();
            transaction.commit();
        }
        catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException(e);
        }
    }
}
