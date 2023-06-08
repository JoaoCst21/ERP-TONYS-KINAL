package org.joaocastillo.com.bean;

public class Login {
    String masterUser;
    String masterPassword;

    public Login(String masterUser, String masterPassword) {
        this.masterUser = masterUser;
        this.masterPassword = masterPassword;
    }

    public String getMasterUser() {
        return masterUser;
    }

    public void setMasterUser(String masterUser) {
        this.masterUser = masterUser;
    }

    public String getMasterPassword() {
        return masterPassword;
    }

    public void setMasterPassword(String masterPassword) {
        this.masterPassword = masterPassword;
    }
}
