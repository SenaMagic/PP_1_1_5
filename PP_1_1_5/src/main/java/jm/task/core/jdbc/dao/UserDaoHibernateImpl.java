package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {


    @Override
    public void createUsersTable() {
        try (Session session = Util.getSessionFactory().getCurrentSession()) {
            Transaction transaction = session.beginTransaction();
            session.createSQLQuery("CREATE TABLE IF NOT EXISTS users " +
                    "(id INT PRIMARY KEY AUTO_INCREMENT, " +
                    "name VARCHAR(45), " +
                    "last_name VARCHAR(45), " +
                    "age INT)").executeUpdate();
            transaction.commit();
            System.out.println("Таблица создана");
        } catch (HibernateException e) {
            System.err.println("Не удалось создать таблицу");
            e.printStackTrace();
        }
    }

    @Override
    public void dropUsersTable() {
        try (Session session = Util.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.createSQLQuery("DROP TABLE IF EXISTS users").executeUpdate();
            transaction.commit();
            System.out.println("Таблица удалена");
        } catch (HibernateException e) {
            System.err.println("Не удалось удалить таблицу");
            e.printStackTrace();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try (Session session = Util.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(new User(name, lastName, age));
            transaction.commit();
            System.out.println("Пользователь " + name + " " + lastName + " добавлен");
        } catch (HibernateException e) {
            System.err.println("При добавлении пользователя возникло исключение");
            e.printStackTrace();
        }

    }

    @Override
    public void removeUserById(long id) {
        try (Session session = Util.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.createSQLQuery("DELETE FROM users " +
                    "WHERE id = " + id).executeUpdate();
            transaction.commit();
            System.out.println("Пользователь id " + id + " удален");
        } catch (HibernateException e) {
            System.err.println("При удалении пользователя возникло исключение");
            e.printStackTrace();
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> userList = null;
        try (Session session = Util.getSessionFactory().openSession()) {
            userList = session.createQuery("FROM User").list();
        } catch (HibernateException e) {
            System.err.println("Не удалось получить список пользователей");
            e.printStackTrace();
        }
        return userList;
    }

    @Override
    public void cleanUsersTable() {
        try (Session session = Util.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.createSQLQuery("DELETE FROM users").executeUpdate();
            transaction.commit();
            System.out.println("Таблица очищена");
        } catch (HibernateException e) {
            System.err.println("Не удалось очистить таблицу");
            e.printStackTrace();
        }
    }
}
