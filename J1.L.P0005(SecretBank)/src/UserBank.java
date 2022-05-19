
import java.io.Serializable;


public class UserBank implements Serializable{
    private String id;
    private String userName;
    private long balance;

    public UserBank() {
    }

    public UserBank(String id) {
        this.id = id;
    }
    
    public UserBank(String id, String userName){
        this.id = id;
        this.userName = userName;
    }
    public UserBank(String id, String userName, long balance) {
        this.id = id;
        this.userName = userName;
        this.balance = balance;
    }
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public long getBalance() {
        return balance;
    }

    public void setBalance(long balance) {
        this.balance = balance;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
    
    
    
    
}
