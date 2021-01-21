package co.edu.unipiloto.edu.mystarbuzzapp;

public class User {

    private String fullName;
    private String userName;
    private String email;
    private String password;
    private String gender;

    public User() {

    }

    public User(String fullName, String userName, String email, String password, String gender) {
        this.fullName = fullName;
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "User{" +
                "fullName='" + fullName + '\'' +
                ", userName='" + userName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", gender='" + gender + '\'' +
                '}';
    }

    public String getFullName() {return fullName;}
    public String getUserName() {return userName;}
    public String getEmail() {return email;}
    public String getPassword() {return password;}
    public String getGender() {return gender;}

    public void setFullName(String fullName) {this.fullName = fullName;}
    public void setUserName(String userName) {this.userName = userName;}
    public void setEmail(String email) {this.email = email;}
    public void setPassword(String password) {this.password = password;}
    public void setGender(String gender) {this.gender = gender;}
}
