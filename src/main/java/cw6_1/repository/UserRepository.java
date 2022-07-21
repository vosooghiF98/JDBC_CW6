package cw6_1.repository;

import cw6_1.entity.User;

import java.sql.*;
import java.util.Scanner;

public class UserRepository {
    private final Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/maktab", "postgres", "a*1294278F");
    public UserRepository() throws SQLException {
    }

    Scanner input = new Scanner(System.in);


    public void insertIntoUsersTable(User user) throws SQLException {
        String query = "insert into maktab.public.users (first_name, last_name, email, password) " +
                "values (?,?,?,?);";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, user.getFirstName());
        preparedStatement.setString(2, user.getLastName());
        preparedStatement.setString(3, user.getEmail());
        preparedStatement.setString(4, user.getPassword());
        preparedStatement.executeUpdate();
        preparedStatement.close();
    }

    public void deleteData(int id) throws SQLException {
        String query = "delete from maktab.public.users where id = ?;";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, id);
        preparedStatement.executeUpdate();
        preparedStatement.close();
    }

    public void updateData(String firstName,String lastName,String email,String password,int id) throws SQLException {
        String query = "update maktab.public.users set " +
                "first_name = ?," +
                "last_name = ?," +
                "email = ?," +
                "password = ?" +
                " where id = ?;";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1,firstName);
        preparedStatement.setString(2, lastName);
        preparedStatement.setString(3, email);
        preparedStatement.setString(4, password);
        preparedStatement.setInt(5, id);
        preparedStatement.executeUpdate();
        preparedStatement.close();
    }

    public User loadById(int id) throws SQLException {
        User user = new User();
        String query = "select * from maktab.public.users where id = ?;";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        user.setId(resultSet.getInt("id"));
        user.setFirstName(resultSet.getString("first_name"));
        user.setLastName(resultSet.getString("last_name"));
        user.setEmail(resultSet.getString("email"));
        user.setPassword(resultSet.getString("password"));
        resultSet.close();
        return user;
    }

    public User[] loadByName(String firstName,String lastName) throws SQLException {
        User user[] = new User[500];
        String query = "select * from maktab.public.users where first_name = ? and last_name = ?;";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, firstName);
        preparedStatement.setString(2, lastName);
        ResultSet resultSet = preparedStatement.executeQuery();
        User user1 = new User();
        int i = 0;
        while (resultSet.next()) {
            user1.setId(resultSet.getInt("id"));
            user1.setFirstName(resultSet.getString("first_name"));
            user1.setLastName(resultSet.getString("last_name"));
            user1.setEmail(resultSet.getString("email"));
            user1.setPassword(resultSet.getString("password"));
            user[i] = user1;
            i++;
        }
        resultSet.close();
        return user;
    }

    public boolean isContains(int id,String firstName,String lastName,String email,String password) throws SQLException {
        User user = new User();
        String query = """
                select * from users where id = ? and first_name = ? and last_name = ? and email = ? and password = ? ;""";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, id);
        preparedStatement.setString(2, firstName);
        preparedStatement.setString(3, lastName);
        preparedStatement.setString(4, email);
        preparedStatement.setString(5, password);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            resultSet.close();
            return true;
        } else {
            return false;
        }
    }
    public boolean isContains(int id) throws SQLException{
        return loadById(id) != null;
    }

}
