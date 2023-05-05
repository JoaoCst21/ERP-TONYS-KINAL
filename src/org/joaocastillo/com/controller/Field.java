package org.joaocastillo.com.controller;

import eu.schudt.javafx.controls.calendar.DatePicker;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.text.SimpleDateFormat;
import java.util.HashMap;

public class Field {
    private HashMap<String, InputCreator> mapFields = new HashMap<String, InputCreator>() {{
        put("input", () -> new TextField());
        put("combobox", () -> new ComboBox<>());
        put("datepicker", () -> {
            DatePicker date = new DatePicker();
            date.setDateFormat(new SimpleDateFormat("dd/MM/yyyy"));
            return date;
        });
    }};

    private String fieldName;
    private String fieldType;

    public Field(String fieldName, String fieldType) {
        this.fieldName = fieldName;
        this.fieldType = fieldType;
    }

    public String getFieldName() {
        return fieldName;
    }

    public String getFieldType() {
        return fieldType;
    }

    public Node getField(Field field) {
        return mapFields.get(field.getFieldType()).createInput();
    }
}

interface InputCreator {
    public Node createInput();
}
