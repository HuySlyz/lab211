
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Scanner;

public class EBank {

    //current locate of bank
    private Locale curLocate;

    //function 1 change language
    public void setLocale(Locale locate) {
        curLocate = locate;
    }

    //function 2 check account and return valid account
    public String checkAccount(String input) {
        //\\d{10} check for number of digit
        String regEx = "^\\d{10}$";
        //check account number, if wrong return error message
        if (input.matches(regEx)) {
            return input;
        } else {
            return getMessage("InvalidAccount");
        }
    }

    //function 3 check password and return valid password
    public String checkPassword(String password) {
        // (?=.*[a-zA-Z]) search for alphabet
        //(?=.*\\d) search for digit
        //[A-Za-z\\d]{8,31}$: search for number of character
        String regEx = "^(?=.*[a-zA-Z])(?=.*\\d)[A-Za-z\\d]{8,31}$";
        //check password, if wrong return error message.
        if (password.matches(regEx)) {
            return password;
        } else {
            return getMessage("InvalidPassword");
        }
    }

    //function 4 generate captcha
    public String generateCaptcha() {
        //create random captcha
        //option 1: use ascii code 48-57 /65-90/97-122
        StringBuilder sb = new StringBuilder();
        //length of captcha
        int length = 5;
        for (int i = 0; i < length; i++) {
            //random ascii of Upper case
            int AZ = (int) (Math.random() * 26) + 65;
            //random ascii of lower case
            int az = (int) (Math.random() * 26) + 97;
            //random digit
            int digits = (int) (Math.random() * 10) + 48;
            //random choice to choice uppercase, lowercase or digits
            int ran = (int) (Math.random() * 3) + 1;
            char a;
            //char is uppercase
            if (ran == 1) {
                a = (char) AZ;
                //char is lowercase
            } else if (ran == 2) {
                a = (char) az;
                //char is digits
            } else {
                a = (char) digits;
            }
            sb.append(a);
        }
        return sb.toString();
        //option 2: create a array contain all character need to create captcha
    }

    //function 5
    public String checkCaptcha(String captchaInput, String captchaGenerated) {
        //check captcha, if wrong return error message
        if (captchaInput.equals(captchaGenerated)) {
            return captchaGenerated;
        } else {
            return getMessage("InvalidCaptcha");
        }
    }

    public String getMessage(String key) {
        // get string in current language with parameter is key
        ResourceBundle words = ResourceBundle.getBundle("Language/" + curLocate, curLocate);
        return words.getString(key);
    }

    //function 6
    void login(Locale locale) {
        setLocale(locale);
        Scanner sc = new Scanner(System.in);
        //type account number
        System.out.println(getMessage("enterAccountNumber"));
        String accountNumber = sc.nextLine();
        String checkAcc = checkAccount(accountNumber);
        //check if error, re-type account number
        while (true) {
            if (!accountNumber.equals(checkAcc)) {
                System.out.println(getMessage("InvalidAccount"));
                System.out.println(getMessage("enterAccountNumber"));
                accountNumber = sc.nextLine();
                checkAcc = checkAccount(accountNumber);
            } else {
                break;
            }
        }
        //type password
        System.out.println(getMessage("enterPassword"));
        String password = sc.nextLine();
        String checkPass = checkPassword(password);
        //check if error, re-type password
        while (true) {
            if (!password.equals(checkPass)) {
                System.out.println(getMessage("InvalidPassword"));
                System.out.println(getMessage("enterPassword"));
                password = checkPassword(sc.nextLine());
                checkPass = checkPassword(password);
            } else {
                break;
            }
        }
        //create and display captcha
        String captchaGenerated = generateCaptcha();
        System.out.print("Captcha: ");
        System.out.println(captchaGenerated);
        //check captcha
        System.out.println(getMessage("enterCaptcha"));
        String captchaInput = sc.nextLine();
        String checkCaptcha = checkCaptcha(captchaInput, captchaGenerated);
        //check if error, retype captcha
        while (!captchaInput.equals(checkCaptcha)) {
            System.out.println(getMessage("InvalidCaptcha"));
            System.out.println(getMessage("enterCaptcha"));
            captchaInput = sc.nextLine();
            checkCaptcha = checkCaptcha(captchaInput, captchaGenerated);
        }
        System.out.println(getMessage("loginSuccess"));
    }
}
