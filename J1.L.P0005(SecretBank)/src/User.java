
import java.io.Serializable;
import java.util.ArrayList;
public class User implements Serializable {
    private ArrayList<String> id = new ArrayList<>();
    private String userName;
    private String password;

    public User(String id, String userName, String password) {
        this.id.add(id);
        this.userName = userName;
        this.password = password;
    }
    
    public User() {
    }



    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ArrayList<String> getId() {
        return id;
    }

    public void addId(String id) {
        this.id.add(id);
    }
    
    public void deleteId(int id){
        this.id.remove(id);
    }
    
    
}
