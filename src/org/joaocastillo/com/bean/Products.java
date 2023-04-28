package org.joaocastillo.com.bean;

public class Products {
    // Attributes
    private int idProduct;
    private String nameProduct;
    private String descriptionProduct;

    // Constructors

    public Products(int idProduct, String nameProduct, String descriptionProduct) {
        this.idProduct = idProduct;
        this.nameProduct = nameProduct;
        this.descriptionProduct = descriptionProduct;
    }

    // Getters
    public int getIdProduct() {
        return idProduct;
    }

    public String getNameProduct() {
        return nameProduct;
    }

    public String getDescriptionProduct() {
        return descriptionProduct;
    }

    // Setters
    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
    }

    public void setNameProduct(String nameProduct) {
        this.nameProduct = nameProduct;
    }

    public void setDescriptionProduct(String descriptionProduct) {
        this.descriptionProduct = descriptionProduct;
    }
}
