package org.joaocastillo.com.bean;

import java.sql.Date;
import java.sql.Time;

public class Services {
    // Attributes
    private int idService;
    private Date dateService;
    private String typeService;
    private Time timeService;
    private String locationService;
    private String contactPhoneService;
    private int _idCompany;

    // Constructors
    public Services(int idService, Date dateService, String typeService, Time timeService, String locationService, String contactPhoneService, int _idCompany) {
        this.idService = idService;
        this.dateService = dateService;
        this.typeService = typeService;
        this.timeService = timeService;
        this.locationService = locationService;
        this.contactPhoneService = contactPhoneService;
        this._idCompany = _idCompany;
    }

    // Getters
    public int getIdService() {
        return idService;
    }

    public Date getDateService() {
        return dateService;
    }

    public String getTypeService() {
        return typeService;
    }

    public Time getTimeService() {
        return timeService;
    }

    public String getLocationService() {
        return locationService;
    }

    public String getContactPhoneService() {
        return contactPhoneService;
    }

    public int get_idCompany() {
        return _idCompany;
    }

    // Setters
    public void setIdService(int idService) {
        this.idService = idService;
    }

    public void setDateService(Date dateService) {
        this.dateService = dateService;
    }

    public void setTypeService(String typeService) {
        this.typeService = typeService;
    }

    public void setTimeService(Time timeService) {
        this.timeService = timeService;
    }

    public void setLocationService(String locationService) {
        this.locationService = locationService;
    }

    public void setContactPhoneService(String contactPhoneService) {
        this.contactPhoneService = contactPhoneService;
    }

    public void set_idCompany(int _idCompany) {
        this._idCompany = _idCompany;
    }
}
