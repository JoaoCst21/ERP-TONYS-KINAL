package org.joaocastillo.com.bean;

import java.sql.Date;

public class Budgets {
    // Attributes
    private int idBudget;
    private Date requestDate;
    private double budgetAmount;
    private int _idCompany;

    // Constructors
    public Budgets(int idBudget, Date requestDate, double budgetAmount, int _idCompany) {
        this.idBudget = idBudget;
        this.requestDate = requestDate;
        this.budgetAmount = budgetAmount;
        this._idCompany = _idCompany;
    }

    // Getters
    public int getIdBudget() {
        return idBudget;
    }

    public Date getRequestDate() {
        return requestDate;
    }

    public double getBudgetAmount() {
        return budgetAmount;
    }

    public int get_idCompany() {
        return _idCompany;
    }

    // Setters
    public void setIdBudget(int idBudget) {
        this.idBudget = idBudget;
    }

    public void setRequestDate(Date requestDate) {
        this.requestDate = requestDate;
    }

    public void setBudgetAmount(double budgetAmount) {
        this.budgetAmount = budgetAmount;
    }

    public void set_idCompany(int _idCompany) {
        this._idCompany = _idCompany;
    }
}
