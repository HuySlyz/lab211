
import java.util.Locale;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        EBank bank = new EBank();
        Scanner sc = new Scanner(System.in);
        //create locale
        Locale vietnamese = new Locale("Vi");
        Locale english = new Locale("En");
        //print out option
        System.out.println("1. Vietnamese");
        System.out.println("2. English");
        System.out.println("3. Exit");
        System.out.print("Please choice one option: ");
        int choice = Integer.parseInt(sc.nextLine());
        boolean toggle = true;
        //loop util take valid option.
        while (toggle) {
            switch (choice) {
                case 1:
                    bank.login(vietnamese);
                    toggle = false;
                    break;
                case 2:
                    bank.login(english);
                    toggle = false;
                    break;
                case 3:
                    toggle = false;
                    break;
                default:
                    System.out.println("Invalid Options, please try again");
                    System.out.print("Please choice one option: ");
                    choice = Integer.parseInt(sc.nextLine());
            }
        }
    }
}
