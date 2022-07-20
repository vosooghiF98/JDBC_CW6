package cw6_1;

import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    static Scanner input = new Scanner(System.in);

    public static void main(String[] args) throws SQLException {
        Sql sql = new Sql();
        sql.creatUsersTable();
        while (true) {
            System.out.print("Enter Your Function : ");
            String button = input.next();
            if (button.equals("add")) {
                System.out.print("Enter Number of Insert row : ");
                int rows = input.nextInt();
                while (rows > 0) {
                    sql.insertIntoUsersTable();
                    rows--;
                }
            }
            if (button.equals("delete")){
                sql.deleteData();
            }
            if (button.equals("update")) {
                sql.updateData();
            }
            if (button.equals("show")){
                sql.showData();
            }
            if (button.equals("end")){
                break;
            }
        }
    }
}
