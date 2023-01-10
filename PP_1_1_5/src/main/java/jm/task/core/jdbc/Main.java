package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;


public class Main {

    public static void main(String[] args) {
        // реализуйте алгоритм здесь
        UserService userService = new UserServiceImpl();

        userService.createUsersTable();
        userService.saveUser("Ones", "Mans", (byte) 28);
        userService.saveUser("Twos" , "Womans", (byte) 36);
        userService.saveUser("Threes" , "Womans", (byte) 19);
        userService.saveUser("Fours", "Armored Helicopter", (byte) 26);
        System.out.println(userService.getAllUsers());
        userService.removeUserById(2);
        System.out.println(userService.getAllUsers());
        userService.cleanUsersTable();
        System.out.println(userService.getAllUsers());
        userService.dropUsersTable();

//        UserDaoJDBCImpl userDaoJDBC = new UserDaoJDBCImpl();
//        userDaoJDBC.createUsersTable();
//        userDaoJDBC.saveUser("One", "Man", (byte) 18);
//        userDaoJDBC.saveUser("Two", "Woman", (byte) 24);
//        userDaoJDBC.saveUser("Three", "Armored helicopter", (byte) 23);
//        userDaoJDBC.saveUser("Four", "Man", (byte) 47);
//        System.out.println(userDaoJDBC.getAllUsers());
//        userDaoJDBC.cleanUsersTable();
//        userDaoJDBC.dropUsersTable();
    }
}
