package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        UserService service = new UserServiceImpl();

        service.createUsersTable();

        service.saveUser("Name1", "LastName1", (byte) 20);
        System.out.println("User с именем Ц Name1 добавлен в базу данных");
        service.saveUser("Name2", "LastName2", (byte) 25);
        System.out.println("User с именем Ц Name2 добавлен в базу данных");
        service.saveUser("Name3", "LastName3", (byte) 31);
        System.out.println("User с именем Ц Name3 добавлен в базу данных");
        service.saveUser("Name4", "LastName4", (byte) 38);
        System.out.println("User с именем Ц Name4 добавлен в базу данных");

        service.removeUserById(1);
        List<User> users = service.getAllUsers();
        for (User user : users) {
            System.out.println(user);
        }
        service.cleanUsersTable();
        service.dropUsersTable();
    }
}
