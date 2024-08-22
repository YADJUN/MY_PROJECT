package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();

//        userService.createUsersTable();
//        userService.saveUser("Malik","aef",(byte)123);
        userService.cleanUsersTable();
    }
}
