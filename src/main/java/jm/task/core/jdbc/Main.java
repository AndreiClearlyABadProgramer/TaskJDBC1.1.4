package jm.task.core.jdbc;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

public class Main {
    public static void main(String[] args) {
        Util util = new Util();
        UserService service = new UserServiceImpl();
        service.createUsersTable();
        service.saveUser("Andrei", "Dik", (byte)18);
        service.saveUser("Ivan", "Kirk", (byte)23);
        service.saveUser("Alex", "Fork", (byte)53);
        service.saveUser("Dimitri", "Kraig", (byte)60);
        System.out.println(service.getAllUsers());
        service.cleanUsersTable();
        service.dropUsersTable();
        util.close();
    }
}
