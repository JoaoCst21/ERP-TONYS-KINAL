package org.joaocastillo.com.bean;

public class User {
    // Attributes
    private int idUser;
    private String userEmail;
    private String userPassword;
    private String userName;
    private String userLastName;

    // Constructor
    public User(int idUser, String userEmail, String userPassword, String userName, String userLastName) {
        this.idUser = idUser;
        this.userEmail = userEmail;
        this.userPassword = userPassword;
        this.userName = userName;
        this.userLastName = userLastName;
    }

    // Getters
    public int getIdUser() {
        return idUser;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserLastName() {
        return userLastName;
    }

    // Setters
    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setUserLastName(String userLastName) {
        this.userLastName = userLastName;
    }
}
