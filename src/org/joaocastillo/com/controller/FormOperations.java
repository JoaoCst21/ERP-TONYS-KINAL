package org.joaocastillo.com.controller;

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
		for (TextField txtField : component.getFields().values()) {
			operation.operate(txtField);
		}
	}

	public void clearFields() {
		makeOperation((txtField) -> txtField.setText(""));
	}

	public void enableFields() {
		makeOperation((txtField) -> txtField.setDisable(false));

		if (component.isAutoIncrement) component.getFields().get(component.fieldID).setDisable(true);
	}

	public void disableFields() {
		makeOperation((txtField) -> txtField.setDisable(true));
	}

	public boolean areFieldsEmpty() {
		for (Map.Entry<String, TextField> field : component.getFields().entrySet()) {
			if (component.isAutoIncrement && component.getFields().get(component.fieldID) == field.getValue()) continue;
			if (field.getValue().getText().isEmpty()) return true;
		}
		return false;
	}

	public void setDefaultFields() {
		component.setFields(new HashMap<String, TextField>());
		component.getMapFields().forEach((key, value) -> {
			component.getFields().put(key, new TextField());
		});
	}

	public void setFormData() {
		int iteration = 0;
		int indexRow = 0;
		int indexColumn = 0;


		double formWidth = component.formModel.getPrefWidth();
		// size must be even, if not add 1
		double size = (component.getMapFields().size() % 2 == 0
				? component.getMapFields().size()
				: component.getMapFields().size() + 1);

		// width of each label - textfield pair including padding
		double width = formWidth / size;

		// width of all textfields together
		double txtFieldsSizeAcc = width * (size / 2);

		// width of all labels together
		double lblFieldsSizeAcc = txtFieldsSizeAcc / 1.5;

		// calculate right padding with left space
		double padding = (width - (lblFieldsSizeAcc + txtFieldsSizeAcc)) / (size - 1);

		// set Space between label and textfield
		System.out.println(padding + width);
		System.out.println(lblFieldsSizeAcc);
		System.out.println(txtFieldsSizeAcc);
		component.formModel.setHgap(padding + width);

		for (Map.Entry<String, String> entry : component.getMapFields().entrySet()) {

			Label label = new Label(entry.getValue());
			label.setPrefWidth(width / 1.5);
			label.setStyle("-fx-font-weight: bold;");

			component.getFields().get(entry.getKey()).setPrefWidth(width);
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
	void operate(TextField txtField);
}
