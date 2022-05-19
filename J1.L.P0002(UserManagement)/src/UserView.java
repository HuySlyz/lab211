
import java.util.Scanner;
import Utils.Validation;
import java.util.ArrayList;
import java.util.Collections;

public class UserView {

    //init data
    UserController controller = new UserController();
    Scanner sc = new Scanner(System.in);

    //main menu of program
    void menu() {
        controller.init();
        while (true) {
            System.out.println(
                    "=============User Management============\n"
                    + "1. Create user account\n"
                    + "2. Check exists user\n"
                    + "3. Search user information by name\n"
                    + "4. Update user\n"
                    + "5. Save account to file\n"
                    + "6. Print list user from file");
            System.out.print("Choice: ");
            String choices = sc.nextLine();
            choices = choices.trim();
            switch (choices) {
                case "1":
                    createUserAccount();
                    break;
                case "2":
                    checkExistUser();
                    break;
                case "3":
                    searchByName();
                    break;
                case "4":
                    updateMenu();
                    break;
                case "5":
                    saveToFile();
                    break;
                case "6":
                    printFromFile();
                    break;
                default:
                    System.exit(0);
            }
        }

    }

    void createUserAccount() {
        boolean repeat = true;
        while (repeat) {
            //input username
            System.out.print("username: ");
            String username = sc.nextLine();
            //check if username duplicate or not
            boolean duplicatedUsername = controller.checkExistUser(username);
            while (Validation.checkUserName(username) || duplicatedUsername) {
                //if username duplicate, print out message and
                if (duplicatedUsername) {
                    System.out.println("user name duplicated");
                }
                System.out.print("username: ");
                username = sc.nextLine();
                duplicatedUsername = controller.checkExistUser(username);
            }

            //input password
            System.out.print("password: ");
            String password = sc.nextLine();
            while (Validation.checkPassword(password)) {
                System.out.print("password: ");
                password = sc.nextLine();
            }

            //confirm password
            System.out.print("confirm password: ");
            String confirm = sc.nextLine();
            while (Validation.confirmPassword(password, confirm)) {
                System.out.print("confirm password: ");
                confirm = sc.nextLine();
            }

            //input first name
            System.out.print("firstname: ");
            String firstName = sc.nextLine();
            while (Validation.checkBlank(firstName)) {
                System.out.print("first name: ");
                firstName = sc.nextLine();
            }

            //input last name
            System.out.print("last name: ");
            String lastName = sc.nextLine();
            while (Validation.checkBlank(lastName)) {
                System.out.print("last name: ");
                lastName = sc.nextLine();
            }

            //input phone number
            System.out.print("phone number: ");
            String phone = sc.nextLine();
            while (Validation.checkPhoneNumber(phone)) {
                System.out.print("phone number: ");
                phone = sc.nextLine();
            }

            //input email
            System.out.print("email: ");
            String email = sc.nextLine();
            while (Validation.checkEmail(email)) {
                System.out.print("email: ");
                email = sc.nextLine();
            }
            //add to list
            controller.addUser(username, firstName, lastName, password, phone, email);
            //ask if user want to continue or not ?
            repeat = askToRepeat();
        }
    }

    void checkExistUser() {
        //ask to reapeat
        while (true) {
            //input user name
            System.out.print("User name: ");
            String userName = sc.nextLine();
            //check user exist in list ?
            boolean exist = controller.checkExistUser(userName);
            //message about existance
            if (exist) {
                System.out.println("Exist User");
            } else {
                System.out.println("No User Found!");
            }
            //ask user want to repeat
            if (!askToRepeat()) {
                break;
            }
        }
    }

    void searchByName() {
        //check exist
        while (true) {
            //input apart of user name (firstname or last name)
            System.out.print("Type a part name of user: ");
            String name = sc.nextLine();
            //search in list this name
            ArrayList<User> result = controller.searchByName(name);
            //user not found
            if (result.size() == 0) {
                System.out.println("Have no any user");
            } else {
                //sort result use comparable in class user according alphabet
                Collections.sort(result);
                //print out user
                printUser(result);
            }

            if (!askToRepeat()) {
                break;
            }
        }
    }

    //update menu
    void updateMenu() {
        System.out.println("1. Update user\n"
                + "2. Delete User");
        String choice = sc.nextLine();
        switch (choice) {
            case "1":
                updateUser();
                break;
            case "2":
                deleteUser();
                break;
            default:
                System.out.println("wrong option, please try again");
        }
    }

    //login function, return username if logged in
    boolean login() {
        while (true) {
            //input username
            System.out.print("username: ");
            String username = sc.nextLine();
            while (Validation.checkUserName(username)) {
                System.out.print("username: ");
                username = sc.nextLine();
            }

            //input password
            System.out.print("password: ");
            String password = sc.nextLine();
            while (Validation.checkPassword(password)) {
                System.out.print("password: ");
                password = sc.nextLine();
            }
            //validate username and password
            User curUser = controller.login(username, password);
            //if have username but wrong password 
            if (curUser == null && controller.checkExistUser(username)) {
                System.out.println("wrong password");
                return false;
                //if not found user name 
            } else if (curUser == null && !controller.checkExistUser(username)) {
                return false;
            } else {
                System.out.println("Login successfully");
                return true;
            }

        }

    }

    //update function
    void updateUser() {
        sc = new Scanner(System.in);
        //required login
        while (true) {
            System.out.println("login is required");
            //logged in
            if (login()) {
                //input password
                System.out.print("password: ");
                String password = sc.nextLine();
                //if type password, check and confirm -> update, else, not update
                if (password.trim().length() != 0) {
                    while (Validation.checkPassword(password)) {
                        System.out.print("password: ");
                        password = sc.nextLine();
                    }
                    //confirm password
                    System.out.print("confirm password: ");
                    String confirm = sc.nextLine();
                    while (Validation.confirmPassword(password, confirm)) {
                        System.out.print("confirm password: ");
                        confirm = sc.nextLine();
                    }
                    controller.updatePassword(password);
                }

                //input first name
                System.out.print("firstname: ");
                String firstName = sc.nextLine();
                //if type first name, check and update, else, not update
                if (firstName.trim().length() > 0) {
                    while (Validation.checkBlank(firstName)) {
                        System.out.print("first name: ");
                        firstName = sc.nextLine();
                    }
                    controller.updateFirstName(firstName);
                }

                //input last name
                System.out.print("last name: ");
                String lastName = sc.nextLine();
                //if type first name, check and update, else, not update
                if (lastName.trim().length() > 0) {
                    while (Validation.checkBlank(lastName)) {
                        System.out.print("last name: ");
                        lastName = sc.nextLine();
                    }
                    controller.updateLastName(lastName);
                }

                //input phone number
                System.out.print("phone number: ");
                String phone = sc.nextLine();
                if (phone.trim().length() > 0) {

                    while (Validation.checkPhoneNumber(phone)) {
                        System.out.print("phone number: ");
                        phone = sc.nextLine();
                    }
                    controller.updatePhone(phone);
                }

                //input email
                System.out.print("email: ");
                String email = sc.nextLine();
                if (email.trim().length() > 0) {
                    while (Validation.checkEmail(email)) {
                        System.out.print("email: ");
                        email = sc.nextLine();
                    }
                    controller.updateEmail(email);
                }
                //username not found
            } else {
                System.out.println("User dose not exist");
            }
            if (!askToRepeat()) {
                break;
            }
        }
    }

    void deleteUser() {
        //logged in is required
        while (true) {
            System.out.println("login is required");
            if (login()) {
                controller.deleteUser();
                System.out.println("User Deleted");
            } else {
                System.out.println("User does not exist");
            }
            if (!askToRepeat()) {
                break;
            }
        }
    }

    void saveToFile() {
        controller.saveToFile();
        System.out.println("saved");
    }

    //use for search function and print out function
    void printUser(ArrayList<User> a) {
        System.out.format("%-15s%-15s%-15s%-15s%-20s", "userName", "firstName", "lastName", "phone", "email");
        System.out.println("");
        for (User user : a) {
            System.out.println(user);
        }
    }
    //print all user from file
    void printFromFile(){
        ArrayList<User> list = controller.printFromFile();
        Collections.sort(list);
        printUser(list);
    }
    //print all user
    void printAllUser() {
        ArrayList<User> list = controller.getAllUser();
        Collections.sort(list);
        printUser(list);
    }

    boolean askToRepeat() {
        while (true) {
            System.out.print("Do you want to continue (y/n) ?: ");
            String choice = sc.nextLine();
            if (choice.trim().equalsIgnoreCase("y") || choice.trim().equalsIgnoreCase("yes")) {
                return true;
            } else if (choice.trim().equalsIgnoreCase("n") || choice.trim().equalsIgnoreCase("no")) {
                return false;
            } else {
                System.out.println("wrong option, please try again");
            }
        }
    }

}
