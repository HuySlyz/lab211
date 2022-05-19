package Utils;

public class Validation {

    public static boolean checkBlank(String str) {
        if (str.length() < 1) {
            System.out.println("this field cannot be blank");
            return true;
        } else {
            return false;
        }
    }

    //validate username, have at least 5 character, no space, if invalid return true
    public static boolean checkUserName(String username) {
        if (username.length() < 5) {
            System.out.println("User name must be at least 5 character");
            return true;
        } else if (username.contains(" ")) {
            System.out.println("User name cannot have space");
            return true;
        }
        return false;
    }

    //validate password, have at lease 6 character. if invalid return true;
    public static boolean checkPassword(String password) {
        if (password.length() < 6) {
            System.out.println("password must be at least 6 character");
            return true;
        } else if (password.contains(" ")) {
            System.out.println("User name cannot have space");
            return true;
        }
        return false;
    }
    
    //check confirm password, if not equal, return true;
    public static boolean confirmPassword(String password, String confirm){
        password = password.trim();
        confirm = confirm.trim();
        if(!password.equals(confirm)){
            System.out.println("confirm password not match");
            return true;
        }
        return false;
    }
    
    //check phone number, contain 10 numbers, if invalid return true;
    public static boolean checkPhoneNumber(String number){
        //check have 10 digits
        String regEx = "^\\d{10}$";
        if(!number.matches(regEx)){
            System.out.println("phone number must contain 10 numbers");
            return true;
        }
        return false;
    }
        //check email, if invalid return true;
    public static boolean checkEmail(String email){
        //valid format is something@name.domain or something@name.type.domain ex: trang@gmail.com or trang@fpt.edu.vn
        //"something" can be character and digit, domain is character from 2-8 character, type also 2- 8 character
        String regEx = "^[a-zA-Z0-9]+@([a-z]{2,8}\\.[a-z]{2,8}(\\.[a-z]{2,8})?)*$";
        if(!email.matches(regEx)){
            System.out.println("Email must be in format something@name.domain or something@name.type.domain");
            return true;
        }
        return false;
    }
}
