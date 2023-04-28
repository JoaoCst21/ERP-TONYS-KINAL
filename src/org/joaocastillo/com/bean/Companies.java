package org.joaocastillo.com.bean;

public class Companies {
    // Attributes
    private int idCompany;
    private String nameCompany;
    private String addressCompany;
    private String phoneCompany;

    // Constructors

    public Companies(int idCompany, String nameCompany, String addressCompany, String phoneCompany) {
        this.idCompany = idCompany;
        this.nameCompany = nameCompany;
        this.addressCompany = addressCompany;
        this.phoneCompany = phoneCompany;
    }

    // Getters

    public int getIdCompany() {
        return idCompany;
    }

    public String getNameCompany() {
        return nameCompany;
    }

    public String getAddressCompany() {
        return addressCompany;
    }

    public String getPhoneCompany() {
        return phoneCompany;
    }

    // Setters
    public void setIdCompany(int idCompany) {
        this.idCompany = idCompany;
    }

    public void setNameCompany(String nameCompany) {
        this.nameCompany = nameCompany;
    }

    public void setAddressCompany(String addressCompany) {
        this.addressCompany = addressCompany;
    }

    public void setPhoneCompany(String phoneCompany) {
        this.phoneCompany = phoneCompany;
    }
}
