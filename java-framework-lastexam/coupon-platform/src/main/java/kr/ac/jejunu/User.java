package kr.ac.jejunu;

public class User {
    Integer code;
    String id;
    String password;
    String name;
    boolean ownerFlag = false;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isOwnerFlag() {
        return ownerFlag;
    }

    public void setOwnerFlag(boolean ownerFlag) {
        this.ownerFlag = ownerFlag;
    }
}
