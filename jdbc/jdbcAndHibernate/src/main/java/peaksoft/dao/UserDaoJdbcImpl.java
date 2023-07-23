package peaksoft.dao;

import peaksoft.model.User;
import peaksoft.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJdbcImpl implements UserDao {

    public UserDaoJdbcImpl() {

    }

    public void createUsersTable() {
        String sql = "CREATE TABLE IF NOT EXISTS users (" +
                "id SERIAL PRiMARY KEY," +
                "name VARCHAR," +
                "last_name VARCHAR," +
                "age SMALLINT)";
        try (
                Connection connection = Util.getConnection();
                Statement statement = connection.createStatement()
        ) {
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.getMessage();
        }
        System.out.println("Table created");

    }

    public void dropUsersTable() {
        String sql = "DROP TABLE IF EXISTS users";
        try (
                Connection connection = Util.getConnection();
                Statement statement = connection.createStatement()
        ) {
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    public void saveUser(String name, String lastName, byte age) {
        String sql = "INSERT  INTO users (name, last_name, age)" +
                "VALUES (?, ?, ?)";
        try (
                Connection connection = Util.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql)
        ) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("User " + name + " was added");
    }

    public void removeUserById(long id) {
        String sql = "DELETE FROM users WHERE id = ?";
        try (
                Connection connection = Util.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql)
        ) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();
        String sql = "SELECT * FROM users";
        try (
                Connection connection = Util.getConnection();
                Statement statement = connection.createStatement();
        ) {
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                userList.add(new User(
                                resultSet.getLong("id"),
                                resultSet.getString("name"),
                                resultSet.getString("last_name"),
                                resultSet.getByte("age")
                        )
                );
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return userList;
    }

    public void cleanUsersTable() {
        String sql = "DELETE FROM users";
        try(
                Connection connection = Util.getConnection();
                Statement statement = connection.createStatement();
                ){
            statement.executeUpdate(sql);
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
}