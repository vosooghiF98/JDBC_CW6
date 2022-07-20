package cw6_1;

import java.sql.*;
import java.util.Scanner;

public class Sql {
    private final Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/maktab","postgres","a*1294278F");
    private final Statement statement = connection.createStatement();
    private String query;
    public Sql() throws SQLException {}
    Scanner input = new Scanner(System.in);
    public void creatUsersTable() throws SQLException {
        query = "create table if not exists users (" +
                "id serial primary key," +
                "first_name varchar(255) not null," +
                "last_name varchar(255) not null," +
                "email varchar(255)," +
                "password varchar(255) not null);";
        statement.execute(query);
    }

    public void insertIntoUsersTable(User user) throws SQLException {
        query = "insert into maktab.public.users (first_name, last_name, email, password) " +
                "values (?,?,?,?);";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1,user.getFirstName());
        preparedStatement.setString(2,user.getLastName());
        preparedStatement.setString(3,user.getEmail());
        preparedStatement.setString(4, user.getPassword());
        preparedStatement.executeUpdate();
    }

    public void deleteData() throws SQLException {
        query = "delete from maktab.public.users where id = ?;";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        System.out.print("Enter ID : ");
        preparedStatement.setInt(1,input.nextInt());
        preparedStatement.executeUpdate();
    }

    public void updateData() throws SQLException {
        query = "update maktab.public.users set password = ? where id = ?;";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        System.out.print("Enter New Password : ");
        preparedStatement.setString(1,input.next());
        System.out.print("Enter ID : ");
        preparedStatement.setInt(2,input.nextInt());
        preparedStatement.executeUpdate();
    }

    public void showData() throws SQLException {
        query = "select * from maktab.public.users where id = ?;";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        System.out.print("Enter ID : ");
        preparedStatement.setInt(1,input.nextInt());
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()){
            System.out.println(resultSet.getString("first_name"));
            System.out.println(resultSet.getString("last_name"));
            System.out.println(resultSet.getString("email"));
            System.out.println(resultSet.getString("password"));
        }

    }

}
