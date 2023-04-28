package org.joaocastillo.com.bean;

public class Dishes {
    // Attributes
    private int idDish;
    private int quantity;
    private int dishName;
    private String descriptionDish;
    private double dishPrice;

    // Constructors
    public Dishes(int idDish, int quantity, int dishName, String descriptionDish, double dishPrice) {
        this.idDish = idDish;
        this.quantity = quantity;
        this.dishName = dishName;
        this.descriptionDish = descriptionDish;
        this.dishPrice = dishPrice;
    }

    // Getters
    public int getIdDish() {
        return idDish;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getDishName() {
        return dishName;
    }

    public String getDescriptionDish() {
        return descriptionDish;
    }

    public double getDishPrice() {
        return dishPrice;
    }

    // Setters
    public void setIdDish(int idDish) {
        this.idDish = idDish;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setDishName(int dishName) {
        this.dishName = dishName;
    }

    public void setDescriptionDish(String descriptionDish) {
        this.descriptionDish = descriptionDish;
    }

    public void setDishPrice(double dishPrice) {
        this.dishPrice = dishPrice;
    }
}
