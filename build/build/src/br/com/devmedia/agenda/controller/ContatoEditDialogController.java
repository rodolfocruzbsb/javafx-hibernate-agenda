package br.com.devmedia.agenda.controller;

import java.time.format.DateTimeFormatter;

import br.com.devmedia.agenda.controller.dto.ContatoDTO;
import br.com.devmedia.agenda.util.DateUtil;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * Dialog to edit details of a Contato.
 * 
 * @author Marco Jakob
 */
public class ContatoEditDialogController {

	@FXML
	private TextField nomeField;

	@FXML
	private TextField dataNascimentoField;

	private Stage dialogStage;

	private ContatoDTO contato;

	private boolean okClicked = false;

	/**
	 * Initializes the controller class. This method is automatically called after the fxml file has been loaded.
	 */
	@FXML
	private void initialize() {

	}

	/**
	 * Sets the stage of this dialog.
	 * 
	 * @param dialogStage
	 */
	public void setDialogStage(Stage dialogStage) {

		this.dialogStage = dialogStage;
	}

	/**
	 * Sets the Contato to be edited in the dialog.
	 * 
	 * @param contato
	 */
	public void setContato(ContatoDTO contato) {

		this.contato = contato;

		nomeField.setText(contato.getNome());

		dataNascimentoField.setText(contato.getDataNascimento() != null ? contato.getDataNascimento().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) : null);
	}

	/**
	 * Returns true if the user clicked OK, false otherwise.
	 * 
	 * @return
	 */
	public boolean isOkClicked() {

		return okClicked;
	}

	@FXML
	private void handleOk() {

		if (isInputValid()) {
			contato.setNome(nomeField.getText());
			contato.setDataNascimento(DateUtil.parse(dataNascimentoField.getText()));

			okClicked = true;
			dialogStage.close();
		}
	}

	@FXML
	private void handleCancel() {

		dialogStage.close();
	}

	/**
	 * Validates the user input in the text fields.
	 * 
	 * @return true if the input is valid
	 */
	private boolean isInputValid() {

		String errorMessage = "";

		if (nomeField.getText() == null || nomeField.getText().length() == 0) {
			errorMessage += "Nome não informada!\n";
		}

		if (dataNascimentoField.getText() == null || dataNascimentoField.getText().length() == 0) {
			errorMessage += "Data de nascimento não informada!\n";
		} else {
			if (!DateUtil.validDate(dataNascimentoField.getText())) {
				errorMessage += "Data de nascimento inválida. Use o formato dd/mm/yyyy!\n";
			}
		}

		if (errorMessage.length() == 0) {
			return true;
		} else {
			// Show the error message.
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Campos não atendem ás regras de validação");
			alert.setHeaderText("Por favor, corrija os error para prosseguir!");
			alert.setContentText(errorMessage);
			alert.showAndWait();
			return false;
		}
	}
}
