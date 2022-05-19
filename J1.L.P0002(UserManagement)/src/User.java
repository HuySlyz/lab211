
public class User implements Comparable<User> {

    private String userName;
    private String firstName;
    private String lastName;
    private String password;
    private String phone;
    private String email;

    //constructor
    public User() {
    }

    public User(String userName, String firstName, String lastName, String password, String phone, String email) {
        this.userName = userName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.phone = phone;
        this.email = email;
    }
    
    //getter/setter
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    //print out with format
    @Override
    public String toString() {
        String result = String.format("%-15s%-15s%-15s%-15s%-20s", userName, firstName, lastName, phone, email);
        return result;
    }

    @Override
    public int compareTo(User t) {
        //merge name to compare full name
        String thisName = this.getFirstName()+ this.getLastName();
        String tName = t.getFirstName() + t.getLastName();
        return thisName.toLowerCase().compareTo(tName.toLowerCase());
    }

}
