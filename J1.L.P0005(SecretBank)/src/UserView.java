
import java.util.*;

public class UserView {

    User currentUser = new User();
    UserController controller = new UserController();
    public static Scanner sc = new Scanner(System.in);

    public void mainMenu() {
        boolean run = true;
        controller.init();
        while (run) {
            System.out.println("1. Create new account");
            System.out.println("2. Login");
            System.out.println("3. Exit");
            sc = new Scanner(System.in);
            int options = sc.nextInt();
            switch (options) {
                case 1:
                    createAccount();
                    break;
                case 2:
                    //if logged in exit main menu
                    boolean a = login();
                    if (a) {
                        run = false;
                    }
                    break;
                case 3:
                    run = false;
                    controller.saveData();
                    System.exit(0);
                    break;
                default:
                    System.out.println("wrong options, try again");
            }
        }
    }

    public void loggedMenu() {
        boolean run = true;
        while (run) {
            System.out.println("1. Withdraw");
            System.out.println("2. Deposit");
            System.out.println("3. Transfer");
            System.out.println("4. Remove Account");
            System.out.println("5. Get balance");
            System.out.println("6. Create new bank account");
            System.out.println("7. Log out");
            sc = new Scanner(System.in);
            int options = sc.nextInt();
            switch (options) {
                case 1:
                    //withdraw
                    withdraw();
                    break;
                case 2:
                    //deposit
                    deposit();
                    break;
                case 3:
                    //transfer
                    transfer();
                    break;
                case 4:
                    //remove
                    removeAccount();
                    run = false;
                    break;
                case 5:
                    getBalance();
                    break;
                case 6:
                    createNewBankAccount();
                case 7:
                    controller.saveData();
//                    System.exit(0);
                    run = false;
                    mainMenu();
                    controller.currentID = "";
                    break;
                default:
                    System.out.println("wrong options, try again");
            }

        }
    }

    public void createAccount() {
        sc = new Scanner(System.in);
        System.out.println("Type id: ");
        String id = sc.nextLine();
        while (controller.checkId(id)) {
            System.out.println("id duplicated or invalid, please try again");
            System.out.println("Type id: ");
            id = sc.nextLine();
        }

        //username
        System.out.println("Type user name: ");
        String userName = sc.nextLine();
        while (controller.checkUserName(userName)) {
            System.out.println("User name duplicate or wrong format");
            System.out.println("Type user name: ");
            userName = sc.nextLine();
        }

        //password
        System.out.println("Type password: ");
        String password = sc.nextLine();
        while (controller.checkPassword(password)) {
            System.out.println("password must has at least 6 chacter, contain at least 1 character, 1 Upper case character, 1 special character");
            System.out.println("Type password: ");
            password = sc.nextLine();
        }

        //confirm
        System.out.println("Type confirm password: ");
        String confirmPassword = sc.nextLine();
        while (controller.checkConfirmPass(password, confirmPassword)) {
            System.out.println("confirm password not match, please try again");
            System.out.println("Type confirm password: ");
            confirmPassword = sc.nextLine();
        }

        controller.addUser(id, userName, password);
        mainMenu();
    }

    //login
    //authentication by id and password, 
    //if logged in, can widthraw and deposit    
    boolean login() {
        sc = new Scanner(System.in);
        System.out.println("UserName: ");
        String userName = sc.nextLine();
        System.out.println("Password: ");
        String password = sc.nextLine();
        currentUser = controller.login(userName, password);
        if (currentUser != null) {
            loggedMenu();
            return true;
        } else {
            System.out.println("User name or password maybe wrong");
            return false;
        }
    }

    //deposit
    //amount money to deposit
    //confirm message, update balance -> go back to menu
    void deposit() {
        long curBalance;
        System.out.println("select your account: ");
        int count = 1;
        for (String a : controller.takeAccount()) {
            System.out.print(count + ". ");
            System.out.println(a);
            count++;
        }
        sc = new Scanner(System.in);
        int selectedAccount = sc.nextInt();
        while (selectedAccount - 1 > controller.takeAccount().size() || selectedAccount < 1) {
            System.out.println("wrong options, please try again");
            selectedAccount = sc.nextInt();
        }
        while (true) {
            System.out.println("Amount money need to deposit");
            long amount = sc.nextLong();
            System.out.println("Are u sure deposit " + amount + " ? (y/n)");
            sc = new Scanner(System.in);
            String confirm = sc.nextLine();
            if (confirm.equalsIgnoreCase("y")) {
                curBalance = controller.deposit(selectedAccount, amount);
                //check amount > 0
                if (curBalance < 0) {
                    System.out.println("please type amount > 0");
                } else {
                    System.out.println("current balance: " + curBalance);
                    break;
                }
            } else if (confirm.equalsIgnoreCase("n")) {
                System.out.println("canceled");
                break;
            } else {
                System.out.println("wrong option, try again");
                System.out.println("Are u sure deposit " + amount + " ? (y/n)");
                sc = new Scanner(System.in);
                confirm = sc.nextLine();
            }
        }
        loggedMenu();
    }

    //withdraw
    //amount monry to widthdraw
    //check balance, update balance
    //return to main screen
    void withdraw() {
        System.out.println("select your account: ");
        int count = 1;
        for (String a : controller.takeAccount()) {
            System.out.print(count + ". ");
            System.out.println(a);
            count++;
        }
        sc = new Scanner(System.in);
        int selectedAccount = sc.nextInt();
        System.out.println("Amount money need to withdraw");
        long amount = sc.nextLong();
        System.out.println("Are u sure withdraw " + amount + " ? (y/n)");
        sc = new Scanner(System.in);
        String confirm = sc.nextLine();
        while (true) {
            if (confirm.equalsIgnoreCase("y")) {
                long curBalance = controller.withdraw(selectedAccount, amount);
                if (curBalance >= 0) {
                    System.out.println("current balance: " + curBalance);
                } else {
                    System.out.println("your balance is not enough money or invalid value!");
                }
                break;
            } else if (confirm.equalsIgnoreCase("n")) {
                System.out.println("canceled");
                break;
            } else {
                System.out.println("wrong option, try again");
                System.out.println("Are u sure deposit " + amount + " ? (y/n)");
                sc = new Scanner(System.in);
                confirm = sc.nextLine();
            }
        }
        loggedMenu();
    }

    //transfer
    //recepit account and amount money need to transfer
    //check if receiver is existed or not, if exist, display receiver user name, else err message
    void transfer() {
        System.out.println("select your account: ");
        int count = 1;
        for (String a : controller.takeAccount()) {
            System.out.print(count + ". ");
            System.out.println(a);
            count++;
        }
        sc = new Scanner(System.in);
        int selectedAccount = sc.nextInt();
        System.out.println("id of receiver: ");
        sc = new Scanner(System.in);
        String id = sc.nextLine();
        String name = controller.search(id);
        long amount;
        //check receiver exist or not
        while (true) {
            if (name.length() > 0) {
                System.out.println("name of receiver is: " + name);
                System.out.println("type amount of money need to transfer: ");
                sc = new Scanner(System.in);
                amount = sc.nextLong();
                // transfer, return current balance, if not, return -1
                while (true) {
                    System.out.println("do you wan to transfer to " + name + " with amount: " + amount + " ? (y/n)");
                    sc = new Scanner(System.in);
                    String confirm = sc.nextLine();
                    if (confirm.equalsIgnoreCase("y")) {
                        long balance = controller.transfer(selectedAccount, id, amount);
                        if (balance >= 0) {
                            System.out.println("transaction successfull");
                            System.out.println("current balance: " + balance);
                        } else {
                            System.out.println("Not enough money to transfer or invalid value");
                        }
                        break;
                    } else if (confirm.equalsIgnoreCase("n")) {
                        System.out.println("canceled");
                        break;
                    } else {
                        System.out.println("Wrong option, try again");
                    }
                }
                break;
            } else {
                System.out.println("receiver not exist, please try again !");
                sc = new Scanner(System.in);
                System.out.println("id of receiver: ");
                id = sc.nextLine();
                name = controller.search(id);
            }
        }
        loggedMenu();
    }

    //remove account
    //confirm message to remove or not
    void removeAccount() {
        ArrayList<String> listAcc = controller.takeAccount();
        int selectedAccount = 0;
        if (listAcc.size() > 1) {
            System.out.println("select your account: ");
            int count = 1;
            for (String a : listAcc) {
                System.out.print(count + ". ");
                System.out.println(a);
                count++;
            }
            sc = new Scanner(System.in);
            selectedAccount = sc.nextInt();
        }
        System.out.println("Do you really want to remove account ? (y/n)");
        while (true) {
            sc = new Scanner(System.in);
            String confirm = sc.nextLine();
            if (confirm.equalsIgnoreCase("y")) {
                //if list account = 1 remove user
                if (listAcc.size() > 1) {
                    controller.removeAcc(selectedAccount);
                   loggedMenu();
                } else {
                    controller.removeUser();
                    mainMenu();
                }
                break;
            } else if (confirm.equalsIgnoreCase("n")) {
                System.out.println("canceled");
                break;
            } else {
                System.out.println("wrong option, try again");
            }
        }
    }

    void getBalance() {
        String rs = controller.getBalance();
        if (rs.length() < 0) {
            return;
        } else {
            System.out.println(rs);
        }
    }
    //user.dat store all account and password

    private void createNewBankAccount() {
        System.out.print("type id of new account: ");
        sc = new Scanner(System.in);
        String newId = sc.nextLine();
        while (controller.checkId(newId)) {
            System.out.println("id dublicated or invalid, please try again");
            newId = sc.nextLine();
        }
        controller.createNewBankAccount(newId, currentUser.getUserName());
        loggedMenu();
    }

}
