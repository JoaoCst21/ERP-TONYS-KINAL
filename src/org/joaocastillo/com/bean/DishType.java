package org.joaocastillo.com.bean;

public class DishType {
    // Attributes
    private int idDishType;
    private String descriptionDishType;

    // Constructors

    public DishType(int idDishType, String descriptionDishType) {
        this.idDishType = idDishType;
        this.descriptionDishType = descriptionDishType;
    }

    // Getters
    public int getIdDishType() {
        return idDishType;
    }

    public String getDescriptionDishType() {
        return descriptionDishType;
    }

    // Setters
    public void setIdDishType(int idDishType) {
        this.idDishType = idDishType;
    }

    public void setDescriptionDishType(String descriptionDishType) {
        this.descriptionDishType = descriptionDishType;
    }
}
