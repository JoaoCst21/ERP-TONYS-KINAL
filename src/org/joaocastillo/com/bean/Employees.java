package org.joaocastillo.com.bean;

public class Employees {
    // Attributes
    private int idEmployee;
    private int employeeNumber;
    private String firstNamesEmployee;
    private String lastNamesEmployee;
    private String addressEmployee;
    private String contactPhoneEmployee;
    private String chefDegreeEmployee;
    private int _idEmployeeType;

    // Constructors


    public Employees(int idEmployee, int employeeNumber, String firstNamesEmployee, String lastNamesEmployee,
                     String addressEmployee, String contactPhoneEmployee, String chefDegreeEmployee,
                     int _idEmployeeType) {
        this.idEmployee = idEmployee;
        this.employeeNumber = employeeNumber;
        this.firstNamesEmployee = firstNamesEmployee;
        this.lastNamesEmployee = lastNamesEmployee;
        this.addressEmployee = addressEmployee;
        this.contactPhoneEmployee = contactPhoneEmployee;
        this.chefDegreeEmployee = chefDegreeEmployee;
        this._idEmployeeType = _idEmployeeType;
    }

    // Getters
    public int getIdEmployee() {
        return idEmployee;
    }

    public int getEmployeeNumber() {
        return employeeNumber;
    }

    public String getFirstNamesEmployee() {
        return firstNamesEmployee;
    }

    public String getLastNamesEmployee() {
        return lastNamesEmployee;
    }

    public String getAddressEmployee() {
        return addressEmployee;
    }

    public String getContactPhone() {
        return contactPhoneEmployee;
    }

    public int get_idEmployeeType() {
        return _idEmployeeType;
    }

    public String getChefDegreeEmployee() {
        return chefDegreeEmployee;
    }

    // Setters
    public void setIdEmployee(int idEmployee) {
        this.idEmployee = idEmployee;
    }

    public void setEmployeeNumber(int employeeNumber) {
        this.employeeNumber = employeeNumber;
    }

    public void setFirstNamesEmployee(String firstNamesEmployee) {
        this.firstNamesEmployee = firstNamesEmployee;
    }

    public void setLastNamesEmployee(String lastNamesEmployee) {
        this.lastNamesEmployee = lastNamesEmployee;
    }

    public void setAddressEmployee(String addressEmployee) {
        this.addressEmployee = addressEmployee;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhoneEmployee = contactPhone;
    }

    public void set_idEmployeeType(int _idEmployeeType) {
        this._idEmployeeType = _idEmployeeType;
    }

    public void setChefDegreeEmployee(String chefDegreeEmployee) {
        this.chefDegreeEmployee = chefDegreeEmployee;
    }
}