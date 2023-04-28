package org.joaocastillo.com.bean;

import java.sql.Date;
import java.sql.Time;

public class Services_has_Employees {
    // Attributes
    private int idServiceEmployee;
    private int _idService;
    private int _idEmployee;
    private Date eventDate;
    private Time eventTime;
    private String eventLocation;

    // Constructors
    public Services_has_Employees(int idServiceEmployee, int _idService, int _idEmployee, Date eventDate, Time eventTime, String eventLocation) {
        this.idServiceEmployee = idServiceEmployee;
        this._idService = _idService;
        this._idEmployee = _idEmployee;
        this.eventDate = eventDate;
        this.eventTime = eventTime;
        this.eventLocation = eventLocation;
    }

    // Getters
    public int getIdServiceEmployee() {
        return idServiceEmployee;
    }

    public int get_idService() {
        return _idService;
    }

    public int get_idEmployee() {
        return _idEmployee;
    }

    public Date getEventDate() {
        return eventDate;
    }

    public Time getEventTime() {
        return eventTime;
    }

    public String getEventLocation() {
        return eventLocation;
    }

    // Setters
    public void setIdServiceEmployee(int idServiceEmployee) {
        this.idServiceEmployee = idServiceEmployee;
    }

    public void set_idService(int _idService) {
        this._idService = _idService;
    }

    public void set_idEmployee(int _idEmployee) {
        this._idEmployee = _idEmployee;
    }

    public void setEventDate(Date eventDate) {
        this.eventDate = eventDate;
    }

    public void setEventTime(Time eventTime) {
        this.eventTime = eventTime;
    }

    public void setEventLocation(String eventLocation) {
        this.eventLocation = eventLocation;
    }
}
