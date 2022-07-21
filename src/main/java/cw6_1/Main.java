package cw6_1;

import cw6_1.entity.User;
import cw6_1.repository.UserRepository;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    static Scanner input = new Scanner(System.in);

    public static void main(String[] args) throws SQLException {
        User user = new User();
        UserRepository userRepository = new UserRepository();
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
                    userRepository.insertIntoUsersTable(user);
                    rows--;
                }
            }
            if (button.equals("delete")){
                System.out.print("Enter ID : ");
                userRepository.deleteData(input.nextInt());
            }
            if (button.equals("update")) {
                System.out.print("Enter New First Name : ");
                String firstName = input.next();
                System.out.print("Enter New Last Name : ");
                String lastName = input.next();
                System.out.print("Enter New Email : ");
                String email = input.next();
                System.out.print("Enter New Password : ");
                String password = input.next();
                System.out.print("Enter ID : ");
                int id = input.nextInt();
                userRepository.updateData(firstName,lastName,email,password,id);
            }
            if (button.equals("showById")){
                System.out.print("Enter ID : ");
                int id = input.nextInt();
                System.out.println(userRepository.loadById(id));
            }
            if (button.equals("showByName")){
                System.out.print("Enter First Name : ");
                String firstName = input.next();
                System.out.print("Enter Last Name : ");
                String lastName = input.next();
                System.out.println(Arrays.toString(userRepository.loadByName(firstName, lastName)));
            }
            if (button.equals("containsById")){
                System.out.print("Enter ID : ");
                int id = input.nextInt();
                System.out.println(userRepository.isContains(id));
            }
            if (button.equals("contains")){
                System.out.print("Enter ID : ");
                int id = input.nextInt();
                System.out.print("Enter First Name : ");
                String firstName = input.next();
                System.out.print("Enter Last Name : ");
                String lastName = input.next();
                System.out.print("Enter Email : ");
                String email = input.next();
                System.out.print("Enter Password : ");
                String password = input.next();
                System.out.println(userRepository.isContains(id,firstName,lastName,email,password));
            }
            if (button.equals("end")){
                break;
            }
        }
    }
}
