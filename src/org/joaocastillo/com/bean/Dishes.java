package org.joaocastillo.com.bean;

public class Dishes {
    // Attributes
    private int idDish;
    private int quantity;
    private String dishName;
    private String descriptionDish;
    private double dishPrice;
    private int _idDishType;

    // Constructors

    public Dishes(int idDish, int quantity, String dishName, String descriptionDish, double dishPrice, int _idDishType) {
        this.idDish = idDish;
        this.quantity = quantity;
        this.dishName = dishName;
        this.descriptionDish = descriptionDish;
        this.dishPrice = dishPrice;
        this._idDishType = _idDishType;
    }

    // Getters
    public int getIdDish() {
        return idDish;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getDishName() {
        return dishName;
    }

    public String getDescriptionDish() {
        return descriptionDish;
    }

    public double getDishPrice() {
        return dishPrice;
    }

    public int get_idDishType() {
        return _idDishType;
    }

    // Setters
    public void setIdDish(int idDish) {
        this.idDish = idDish;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setDishName(String dishName) {
        this.dishName = dishName;
    }

    public void setDescriptionDish(String descriptionDish) {
        this.descriptionDish = descriptionDish;
    }

    public void setDishPrice(double dishPrice) {
        this.dishPrice = dishPrice;
    }

    public void set_idDishType(int _idDishType) {
        this._idDishType = _idDishType;
    }
}
