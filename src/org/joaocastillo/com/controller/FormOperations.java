package org.joaocastillo.com.controller;

import eu.schudt.javafx.controls.calendar.DatePicker;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.joaocastillo.com.view.components.GeneralModelComponent;

import java.util.HashMap;
import java.util.Map;

public class FormOperations<M> {

    private GeneralModelComponent<M> component;

    public FormOperations(GeneralModelComponent<M> component) {
        this.component = component;
    }

    private void makeOperation(FormOperation operation) {
        for (Node node : component.getFields().values()) {
            operation.operate(node);
        }
    }

    public void clearFields() {
        makeOperation((node) -> {
            if (node instanceof TextField) {
                ((TextField) node).setText("");
            }

            if (node instanceof ComboBox) {
                ((ComboBox) node).getSelectionModel().clearSelection();
            }

            if (node instanceof DatePicker) {
                System.out.println("clearing datepicker");
                ((DatePicker) node).setSelectedDate(null);
                ;
            }
        });
    }

    public void enableFields() {
        makeOperation((node) -> node.setDisable(false));

        if (component.isAutoIncrement) component.getFields().get(component.fieldID).setDisable(true);
    }

    public void disableFields() {
        makeOperation((node) -> node.setDisable(true));
    }

    public boolean areFieldsEmpty() {
        for (Map.Entry<String, Node> field : component.getFields().entrySet()) {
            if (component.isAutoIncrement && component.getFields().get(component.fieldID) == field.getValue()) continue;
            if (field.getValue() instanceof TextField) {
                if (((TextField) field.getValue()).getText().isEmpty()) return true;
            }

            if (field.getValue() instanceof ComboBox) {
                if (((ComboBox) field.getValue()).getSelectionModel().isEmpty()) return true;
            }

            if (field.getValue() instanceof DatePicker) {
                if (((DatePicker) field.getValue()).getSelectedDate() == null) return true;
            }
        }
        return false;
    }

    public void setDefaultFields() {
        component.setFields(new HashMap<>());
        component.getMapFields().forEach((key, value) -> {
            component.getFields().put(key, value.getField(value));
        });
    }

    public void setFormData() {
        int iteration = 0;
        int indexRow = 0;
        int indexColumn = 0;


        double formWidth = component.formModel.getPrefWidth();
        // size must be even, if not add 1
        double size = (component.getMapFields().size() % 2 == 0 ? component.getMapFields().size() : component.getMapFields().size() + 1);

        // width of each label - textfield pair including padding
        double width = formWidth / size;

        // width of all textfields together
        double nodesSizeAcc = width * (size / 2);

        // width of all labels together
        double lblFieldsSizeAcc = nodesSizeAcc / 1.5;

        // calculate right padding with left space
        double padding = (width - (lblFieldsSizeAcc + nodesSizeAcc)) / (size - 1);

        // set Space between label and textfield
        System.out.println(padding + width);
        System.out.println(lblFieldsSizeAcc);
        System.out.println(nodesSizeAcc);
        component.formModel.setHgap(padding + width);

        for (Map.Entry<String, Field> entry : component.getMapFields().entrySet()) {
            Label label = new Label(entry.getValue().getFieldName());
            label.setPrefWidth(width / 1.5);
            label.setStyle("-fx-font-weight: bold;");

            if (component.getFields().get(entry.getKey()) instanceof Control) {
                ((Control) component.getFields().get(entry.getKey())).setPrefWidth(width);
            }

            component.formModel.add(label, indexColumn, indexRow);
            component.formModel.add(component.getFields().get(entry.getKey()), indexColumn + 1, indexRow);
            if (iteration % 2 == 0) indexRow++;
            else indexRow--;

            iteration++;

            if (iteration % 2 == 0) indexColumn += 2;
        }
    }

}

interface FormOperation {
    void operate(Node node);
}
