package cw6_1;

import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    static Scanner input = new Scanner(System.in);

    public static void main(String[] args) throws SQLException {
        User user = new User();
        Sql sql = new Sql();
        sql.creatUsersTable();
        while (true) {
            System.out.print("Enter Your Function : ");
            String button = input.next();
            if (button.equals("add")) {
                System.out.print("Enter Number of Insert row : ");
                int rows = input.nextInt();
                while (rows > 0) {
                    System.out.print("Enter First Name : ");
                    user.setFirstName(input.next());
                    System.out.print("Enter last Name : ");
                    user.setLastName(input.next());
                    System.out.print("Enter Email (not necessary!) : ");
                    user.setEmail(input.next());
                    System.out.print("Enter Password : ");
                    user.setPassword(input.next());
                    sql.insertIntoUsersTable(user);
                    rows--;
                }
            }
            if (button.equals("delete")){
                System.out.print("Enter ID : ");
                sql.deleteData(input.nextInt());
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
