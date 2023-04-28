package org.joaocastillo.com.bean;

public class EmployeeType {
    // Attributes
    private int idEmployeeType;
    private String descriptionEmployeeType;

    // Constructors

    public EmployeeType(int idEmployeeType, String descriptionEmployeeType) {
        this.idEmployeeType = idEmployeeType;
        this.descriptionEmployeeType = descriptionEmployeeType;
    }

    // Getters
    public int getIdEmployeeType() {
        return idEmployeeType;
    }

    public String getDescriptionEmployeeType() {
        return descriptionEmployeeType;
    }

    // Setters
    public void setIdEmployeeType(int idEmployeeType) {
        this.idEmployeeType = idEmployeeType;
    }

    public void setDescriptionEmployeeType(String descriptionEmployeeType) {
        this.descriptionEmployeeType = descriptionEmployeeType;
    }
}
