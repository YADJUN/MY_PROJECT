package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Utils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    Connection connection = Utils.getConnection();

    public UserDaoJDBCImpl() {
    }

    public void createUsersTable() {
        PreparedStatement preparedStatement = null;
        String sql = "CREATE TABLE  myFirstTable (id INT PRIMARY KEY AUTO_INCREMENT,name VARCHAR(20),lastname VARCHAR(20),age INT)";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.execute(sql);
            System.out.println("Таблица успешно создана");
        } catch (SQLException e) {
            System.out.println("Неудачная попытка создать таблицу: Такая таблица уже существует");
        }
    }

    public void dropUsersTable() {
        PreparedStatement preparedStatement = null;
        String sql = "DROP TABLE IF EXISTS myFirstTable;";

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.execute(sql);
            System.out.println("Таблица успешно удалена");
        } catch (SQLException e) {
            System.out.println("Не удалось удалить таблицу");
            ;
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        PreparedStatement preparedStatement = null;
        String sql = "INSERT INTO myFirstTable (name,lastname,age) VALUES (" + "'"
                + name + "'" + "," + "'" + lastName + "'" + "," + age + ")";

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.execute(sql);
            System.out.println("User " + name + " успешно добавлен");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void removeUserById(long id) {
        PreparedStatement preparedStatement = null;
        String sql = "DELETE FROM myFirstTable WHERE id LIKE(" + id + ")";

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.execute(sql);
            System.out.println("User с id " + id + " успешно удален");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        String sql = "SELECT name,lastname,age FROM myFirstTable";
        Statement statement = null;
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {

                String name = resultSet.getString("name");
                String lastname = resultSet.getString("lastname");
                byte age = resultSet.getByte("age");
                User user = new User(name, lastname, age);
                users.add(user);

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return users;
    }

    public void cleanUsersTable() {
        PreparedStatement preparedStatement = null;
        String sql = "DELETE FROM myFirstTable";

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.execute(sql);
            System.out.println("Таблица успешно очищена");
        } catch (SQLException e) {

        }
    }
}