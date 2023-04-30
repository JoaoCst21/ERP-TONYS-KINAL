package org.joaocastillo.com.bean;

public class Products {
    // Attributes
    private int idProduct;
    private String productName;
    private int productQuantity;

    // Constructors

    public Products(int idProduct, String productName, int productQuantity) {
        this.idProduct = idProduct;
        this.productName = productName;
        this.productQuantity = productQuantity;
    }

    // Getters
    public int getIdProduct() {
        return idProduct;
    }

    public String getProductName() {
        return productName;
    }

    public int getProductQuantity() {
        return productQuantity;
    }

    // Setters
    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setProductQuantity(int productQuantity) {
        this.productQuantity = productQuantity;
    }
}
