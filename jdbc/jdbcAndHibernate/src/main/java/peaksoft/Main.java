package peaksoft;

import peaksoft.service.UserService;
import peaksoft.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        // реализуйте алгоритм здесь
        UserService userService = new UserServiceImpl();
        userService.createUsersTable();
        userService.saveUser("Baiel", "Saparaliev", (byte) 23);
        userService.saveUser("Isabek", "Davletov", (byte) 12);
        userService.saveUser("Ali", "Kurmanaliev", (byte) 24);
        userService.saveUser("Nuriza", "Erriad kyzy", (byte) 32);
        System.out.println(userService.getAllUsers());
        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}
