package org.joaocastillo.com.bean;

public class Services_has_Dishes {
    // Attributes
    private int idServiceDish;
    private int _idService;
    private int _idDish;

    // Constructors
    public Services_has_Dishes(int idServiceDish, int _idService, int _idDish) {
        this.idServiceDish = idServiceDish;
        this._idService = _idService;
        this._idDish = _idDish;
    }

    // Getters
    public int getIdServiceDish() {
        return idServiceDish;
    }

    public int get_idService() {
        return _idService;
    }

    public int get_idDish() {
        return _idDish;
    }

    // Setters
    public void setIdServiceDish(int idServiceDish) {
        this.idServiceDish = idServiceDish;
    }

    public void set_idService(int _idService) {
        this._idService = _idService;
    }

    public void set_idDish(int _idDish) {
        this._idDish = _idDish;
    }
}
