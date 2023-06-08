package org.joaocastillo.com.controller;

import eu.schudt.javafx.controls.calendar.DatePicker;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.text.SimpleDateFormat;
import java.util.HashMap;

public class Field {
    private HashMap<FieldType, InputCreator> mapFields = new HashMap<FieldType, InputCreator>() {{
        put(FieldType.INPUT, () -> new TextField());
        put(FieldType.COMBOBOX, () -> new ComboBox<>());
        put(FieldType.DATEPICKER, () -> {
            DatePicker date = new DatePicker();
            date.setDateFormat(new SimpleDateFormat("dd/MM/yyyy"));
            return date;
        });
    }};

    private String fieldName;
    private FieldType fieldType;

    public Field(String fieldName, String fieldType) {
        this.fieldName = fieldName;
        this.fieldType = FieldType.valueOf(fieldType.toUpperCase());
    }

    public Field(String fieldName, FieldType fieldType) {
        this.fieldName = fieldName;
        this.fieldType = fieldType;
    }

    public String getFieldName() {
        return fieldName;
    }

    public FieldType getFieldType() {
        return fieldType;
    }

    public Node getField(Field field) {
        return mapFields.get(field.getFieldType()).createInput();
    }
}

interface InputCreator {
    public Node createInput();
}
