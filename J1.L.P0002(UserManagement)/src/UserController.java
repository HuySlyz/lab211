
import java.io.*;
import java.util.*;
import Utils.Hashing;

public class UserController {

    ArrayList<User> list = new ArrayList<>();
    //use for update user
    User curUser = null;
    //use for delete user
    int curIndex;

    //init data
    void init() {
        try {
            //read data from file user.txt add to arraylist
            File file = new File("src\\User.txt");
            Scanner reader = new Scanner(file);
            while (reader.hasNextLine()) {
                String data = reader.nextLine();
                //split data by ","
                String[] word = data.split(",");
                //delete reducdant space
                for (int i = 0; i < word.length; i++) {
                    word[i] = word[i].trim();
                }
                //create user and add to list
                list.add(new User(word[0], word[1], word[2], word[3], word[4], word[5]));
            }
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //check user exist or not
    boolean checkExistUser(String userName) {
        //loop over and find by username
        for (User user : list) {
            if (user.getUserName().trim().equals(userName)) {
                return true;
            }
        }
        return false;
    }

    //add user to collection
    void addUser(String userName, String firstName, String lastName, String password, String phone, String email) {
        //hash password by sha-256 method
        password = Hashing.getHash(password);
        list.add(new User(userName, firstName, lastName, password, phone, email));
    }

    ArrayList<User> searchByName(String name) {
        // arraylist result of searching
        ArrayList<User> rs = new ArrayList<>();
        //change both search name and name of user to lower case for searching
        name = name.toLowerCase().trim();
        for (User user : list) {
            //co the nhap full name
            String fullName = user.getFirstName().toLowerCase() +" " + user.getLastName().toLowerCase();
            if (fullName.contains(name)) {
                rs.add(user);
            }
        }
        return rs;
    }

    User login(String username, String password) {
        //hash password to compare
        String hashedPassword = Hashing.getHash(password);
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getUserName().equals(username)) {
                String curPass = list.get(i).getPassword();
                if (curPass.equals(hashedPassword)) {
                    //current user
                    curUser = list.get(i);
                    //current index of current user  in array list
                    curIndex = i;
                    return curUser;
                }
            }
        }
        //reset
        curUser = null;
        return null;
    }

//    void updateUserName(String username) {
//        curUser.setUserName(username);
//    }

    void updatePassword(String password) {
        curUser.setPassword(Hashing.getHash(password));
    }

    void updateFirstName(String firstName) {
        curUser.setFirstName(firstName);
    }

    void updateLastName(String lastName) {
        curUser.setLastName(lastName);
    }

    void updatePhone(String phone) {
        curUser.setPhone(phone);
    }

    void updateEmail(String email) {
        curUser.setEmail(email);
    }

    void deleteUser() {
        list.remove(list.get(curIndex));
    }

    ArrayList<User> getAllUser() {
        return list;
    }

    ArrayList<User> printFromFile() {
        ArrayList<User> result = new ArrayList<>();
        try {
            //read data from file user.txt add to arraylist
            File file = new File("src\\User.txt");
            Scanner reader = new Scanner(file);
            while (reader.hasNextLine()) {
                String data = reader.nextLine();
                //split data by ","
                String[] word = data.split(",");
                //delete reducdant space
                for (int i = 0; i < word.length; i++) {
                    word[i] = word[i].trim();
                }
                //create user and add to list
                result.add(new User(word[0], word[1], word[2], word[3], word[4], word[5]));
            }
            reader.close();
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<User>();
        }
    }

    void saveToFile() {
        try {
            File file = new File("src\\User.txt");
            Writer wr = new FileWriter(file);
            StringBuffer sb = new StringBuffer();
            //add info to string buffer according to format
            for (User user : list) {
                sb.append(user.getUserName() + "," + user.getFirstName() + "," + user.getLastName() + "," + user.getPassword() + "," + user.getPhone() + ", " + user.getEmail() + "\n");
            }
            //write to file
            wr.write(sb.toString());
            wr.flush();
            wr.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
