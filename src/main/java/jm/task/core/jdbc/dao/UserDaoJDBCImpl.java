package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {

    }
    private static final String INSERTION= "INSERT INTO Users VALUES(?,?,?,?)";
    private static final String ID_REMOVAL = "DELETE FROM Users WHERE id=?";

    public void createUsersTable() {
        Util util = new Util();
        try {
            Statement statement = util.getConnection().createStatement();
            statement.execute("CREATE TABLE Users (id BIGINT , name varchar(45), lastName varchar(45), age tinyint );");
        } catch (SQLException e) {
               System.out.println("Скорее всего, такая таблица уже создана");
        }
        util.close();

    }

    public void dropUsersTable() {
        Util util = new Util();
        try {
            Statement statement = util.getConnection().createStatement();
            statement.execute("DROP TABLE Users;");
        } catch (SQLException e) {
               System.out.println("Вероянтно, такой таблицы не существует");
        }
        util.close();
    }

    public void saveUser(String name, String lastName, byte age) {
        Util util = new Util();
        User user = new User(name, lastName, age);
        System.out.println("User с именем – " + name + " добавлен в базу данных");
        try{
            Statement statement = util.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery("select count(*) from Users");
            resultSet.next();
            long count = resultSet.getInt("count(*)");
            user.setId(count + 1);
            util.close();
        } catch (SQLException e) {
             System.out.println("Something went wrong while counting rows");
        }

        try {
            PreparedStatement preparedStatement = util.getConnection().prepareStatement(INSERTION);
            preparedStatement.setLong(1, user.getId());
            preparedStatement.setString(2, name);
            preparedStatement.setString(3, lastName);
            preparedStatement.setByte(4, age);
            preparedStatement.execute();
            util.close();
        } catch (SQLException e) {
            System.out.println("Something went wrong with saveUser");
        }

    }

    public void removeUserById(long id) {
        Util util = new Util();
        try {
            PreparedStatement statement = util.getConnection().prepareStatement(ID_REMOVAL);
            statement.setLong(1, id);

            statement.execute();
        } catch (SQLException e) {
            System.out.println("Probably this user does not exist");
        }
        util.close();
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        Util util = new Util();
        try {
            Statement statement = util.getConnection().createStatement();
           ResultSet resultSet = statement.executeQuery("SELECT * FROM Users");
           while(resultSet.next()){
               User user = new User();
               user.setId(resultSet.getLong(1));
               user.setName(resultSet.getString(2));
               user.setLastName(resultSet.getString(3));
               user.setAge(resultSet.getByte(4));
               users.add(user);

           }
           util.close();
        } catch (SQLException e) {
            System.out.println("Something went wrong");
        }
       return users;
    }

    public void cleanUsersTable() {
        Util util = new Util();
        try {
            Statement statement = util.getConnection().createStatement();
            statement.execute("DELETE FROM Users");
        } catch (SQLException e) {

        }
        util.close();
    }
}
