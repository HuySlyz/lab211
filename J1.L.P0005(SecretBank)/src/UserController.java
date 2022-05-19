
import java.util.*;
import Cryption.Crypt;
import static Cryption.Crypt.*;
import java.io.*;

public class UserController {

    ArrayList<User> list = new ArrayList<>();
    ArrayList<UserBank> listBalance = new ArrayList<>();
    User currentUser = new User();
    String currentID;
    String currentUserName;

    //take data from database;
    void init() {
        try {
            //fileinputstream use to read byte data from file .dat
            FileInputStream file = new FileInputStream("user.dat");
            FileInputStream bankFile = new FileInputStream("bank.dat");
            //read object from fileinputstreamx
            ObjectInputStream ois = new ObjectInputStream(file);
            ObjectInputStream oisBank = new ObjectInputStream(bankFile);
            //list of user
            list = (ArrayList<User>) ois.readObject();
            //list of bank
            listBalance = (ArrayList<UserBank>) oisBank.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    boolean checkId(String id) {
        id = id.trim();
        //not blank
        if (id.length() <= 0) {
            return false;
        }
        for (UserBank u : listBalance) {
            //dubplilcate return true;
            if (id.equals(u.getId())) {
                return true;
            }
        }
        return false;
    }

    boolean checkPassword(String password) {
        //^ access position start of line
        //(?=.*[a-z]) look for normal chracter
        //(?=.*[A-Z]) look for Upper case characeter
        //(?=.*[`~!@#$%^&*()-_=+':;?/><,.]) look for special character
        //[@$!%*?&A-Za-z\\d`~!@#$%^&*()-_=+':;?/><,.]{6,} match character in [] more than or equals 6 time 
        String regEx = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[.,:';`#^*+=-@$!%*?&])[A-Za-z\\d.,:';`#^*+=-@$!%*?&]{6,}$";
        if (password.matches(regEx)) {
            return false;
        }
        return true;
    }

    boolean checkConfirmPass(String password, String confirm) {
        password = password.trim();
        confirm = confirm.trim();
        if (password.equals(confirm)) {
            return false;
        }
        return true;
    }

    boolean checkUserName(String userName) {
        String regEx = "^([a-zA-Z]){2,}$";
        if (!userName.matches(regEx)) {
            return true;
        }
        for (User a : list) {
            if (a.getUserName().equals(userName)) {
                return true;
            }
        }
        return false;
    }

    //check exist
    String search(String id) {
        //take id
        for (UserBank ub : listBalance) {
            if (ub.getId().equals(id)) {
                return ub.getUserName();
            }
        }
        return "";
    }

    //save to database
    void saveData() {
        try {
            FileOutputStream userFile = new FileOutputStream("user.dat");
            ObjectOutputStream oosUser = new ObjectOutputStream(userFile);
            FileOutputStream bankFile = new FileOutputStream("bank.dat");
            ObjectOutputStream oosBank = new ObjectOutputStream(bankFile);
            oosUser.writeObject(list);
            oosBank.writeObject(listBalance);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void addUser(String id, String name, String password) {
        password = encrypt(password);
        try {
            //add to arraylist
            list.add(new User(id, name, password));
            listBalance.add(new UserBank(id, name));
            //file output stream use for writing of raw bytes to file 
            //or storing data to file
            FileOutputStream userFile = new FileOutputStream("user.dat");
            ObjectOutputStream oosUser = new ObjectOutputStream(userFile);
            FileOutputStream bankFile = new FileOutputStream("bank.dat");
            ObjectOutputStream oosBank = new ObjectOutputStream(bankFile);
            //save to database
            oosUser.writeObject(list);
            oosBank.writeObject(listBalance);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //login and return user, if not exist return null
    User login(String userName, String password) {
        password = encrypt(password);
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getUserName().equals(userName)) {
                if (list.get(i).getPassword().equals(password)) {
                    //currentID = id;
                    currentUserName = userName;
                    currentUser = list.get(i);
                    return list.get(i);
                }
            }
        }
        return null;
    }

    //search id in bank account list and set balance for account
    long deposit(int selectedAccount, long amount) {
        //selected account is index of arraylist in currentuser id
        selectedAccount -= 1;
        if (amount <= 0) {
            return -1;
        }
        String currentAccount = currentUser.getId().get(selectedAccount);
        //search for id in userBank.
        for (UserBank a : listBalance) {
            if (a.getId().equals(currentAccount)) {
                long currentBalance = a.getBalance();
                long newBalance = currentBalance + amount;
                a.setBalance(newBalance);
                return a.getBalance();
            }
        }
        return -1;
    }

    //check balance and withdraw
    long withdraw(int selectedAccount, long amount) {
        selectedAccount -= 1;
        if (amount <= 0) {
            return -1;
        }
        String currentAccount = currentUser.getId().get(selectedAccount);
        for (UserBank a : listBalance) {
            if (a.getId().equals(currentAccount)) {
                long currentBalance = a.getBalance();
                if (currentBalance < amount) {
                    return -1;
                } else {
                    a.setBalance(currentBalance - amount);
                    return a.getBalance();
                }
            }
        }
        return -1;
    }
      
    long transfer(int selectedAccount, String id, long amount) {
        selectedAccount -= 1;
        if (amount <= 0) {
            return -1;
        }
        String currentAccount = currentUser.getId().get(selectedAccount);
        //not enough money or transfer to same account
        if (amount <= 0 || currentAccount.equals(id)) {
            return -1;
        }
        //check balance of current user, if not enough of money, return -1, else transfer and return current balance
        selectedAccount += 1; //because in withraw function will - 1;
        long a = withdraw(selectedAccount, amount);
        if (a < 0) {
            return a;
        }
        for (UserBank ub : listBalance) {
            if (ub.getId().equals(id)) {
                long newBalance = ub.getBalance() + amount;
                ub.setBalance(newBalance);
            }
        }
        return a;
    }

    void removeAcc(int selectedAccount) {
        selectedAccount -= 1;
        int index;
        //reset index
        index = -1;
        String currentAccount = currentUser.getId().get(selectedAccount);
        //search in arraylist of UserBank
        for (int i = 0; i < listBalance.size(); i++) {
            if (listBalance.get(i).getId().equals(currentAccount)) {
                index = i;
                break;
            }
        }
        if (index >= 0) {
            listBalance.remove(index);
            currentUser.deleteId(selectedAccount);
        } else {
            return;
        }
        saveData();
    }

    void removeUser() {
        int index = -1;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getUserName().equals(currentUser.getUserName())) {
                index = i;
                break;
            }
        }
        if (index >= 0) {
            list.remove(index);
        } else {
            return;
        }
        index = -1;
        for (int i = 0; i < listBalance.size(); i++) {
            if (listBalance.get(i).getUserName().equals(currentUser.getUserName())) {
                index = i;
                break;
            }
        }
        if (index >= 0) {
            listBalance.remove(index);
        } else {
            return;
        }
        saveData();
    }

    //use for test
    String getBalance() {
        StringBuffer result = new StringBuffer();
        result.append("Balance: \n");
        for (String a : currentUser.getId()) {
            result.append(a + ": ");
            for(UserBank ub: listBalance){
                if(a.equals(ub.getId())){
                    result.append(ub.getBalance() + "\n");
                }
            }
        }
        return result.toString();
    }

    //ceasar crypt 33-126
    void createNewBankAccount(String newId, String userName) {
        listBalance.add(new UserBank(newId, userName, 0));
        for (User a : list) {
            if (a.getUserName().equals(currentUser.getUserName())) {
                a.addId(newId);
            }
        }
    }
    
    ArrayList<String> takeAccount() {
        return currentUser.getId();
    }

}
