package kr.ac.jejunu;



@Getter
@Setter
public class User {
    String id;
    String password;
    String name;
    boolean ownerFlag = false;

    public User(){}

    public User(String id, String password, String name, boolean ownerFlag) {
        this.id = id;
        this.password = password;
        this.name = name;
        this.ownerFlag = ownerFlag;
    }





}
