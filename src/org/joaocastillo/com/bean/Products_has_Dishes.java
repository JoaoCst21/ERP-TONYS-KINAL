package org.joaocastillo.com.bean;

public class Products_has_Dishes {
    // Attributes
    private int idProductDish;
    private int _idProduct;
    private int _idDish;

    // Constructors
    public Products_has_Dishes(int idProductDish, int _idProduct, int _idDish) {
        this.idProductDish = idProductDish;
        this._idProduct = _idProduct;
        this._idDish = _idDish;
    }

    // Getters
    public int getIdProductDish() {
        return idProductDish;
    }

    public int get_idProduct() {
        return _idProduct;
    }

    public int get_idDish() {
        return _idDish;
    }

    // Setters
    public void setIdProductDish(int idProductDish) {
        this.idProductDish = idProductDish;
    }

    public void set_idProduct(int _idProduct) {
        this._idProduct = _idProduct;
    }

    public void set_idDish(int _idDish) {
        this._idDish = _idDish;
    }
}
