package br.com.devmedia.agenda.controller;

import br.com.devmedia.agenda.MainApp;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class RootLayoutController {

	private MainApp mainApp;

	public void setMainApp(MainApp mainApp) {

		this.mainApp = mainApp;
	}
	/*
	 * @FXML private void handleProprietario() { mainApp.getVeiculoData().clear(); mainApp.setVeiculoFilePath(null); }
	 * 
	 * @FXML private void handleVeiculo() { mainApp.getVeiculoData().clear(); mainApp.setVeiculoFilePath(null); }
	 * 
	 * @FXML private void handleMecanico() { mainApp.getVeiculoData().clear(); mainApp.setVeiculoFilePath(null); }
	 * 
	 * 
	 * @FXML private void handleNew() { mainApp.getVeiculoData().clear(); mainApp.setVeiculoFilePath(null); }
	 */

	/**
	 * Opens a FileChooser to let the user select an address book to load.
	 */
	// @FXML
	// private void handleOpen() {
	// FileChooser fileChooser = new FileChooser();
	//
	// // Set extension filter
	// FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("XML files (*.xml)", "*.xml");
	// fileChooser.getExtensionFilters().add(extFilter);
	//
	// // Show save file dialog
	// File file = fileChooser.showOpenDialog(mainApp.getPrimaryStage());
	//
	// if (file != null) {
	// mainApp.loadVeiculoDataFromFile(file);
	// }
	// }

	/**
	 * Saves the file to the Veiculo file that is currently open. If there is no open file, the "save as" dialog is shown.
	 */
	// @FXML
	// private void handleSave() {
	// File VeiculoFile = mainApp.getVeiculoFilePath();
	// if (VeiculoFile != null) {
	// mainApp.saveVeiculoDataToFile(VeiculoFile);
	// } else {
	// handleSaveAs();
	// }
	// }

	/**
	 * Opens a FileChooser to let the user select a file to save to.
	 */
	// @FXML
	// private void handleSaveAs() {
	// FileChooser fileChooser = new FileChooser();
	//
	// // Set extension filter
	// FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("XML files (*.xml)", "*.xml");
	// fileChooser.getExtensionFilters().add(extFilter);
	//
	// // Show save file dialog
	// File file = fileChooser.showSaveDialog(mainApp.getPrimaryStage());
	//
	// if (file != null) {
	// // Make sure it has the correct extension
	// if (!file.getPath().endsWith(".xml")) {
	// file = new File(file.getPath() + ".xml");
	// }
	// mainApp.saveVeiculoDataToFile(file);
	// }
	// }

	/**
	 * Opens the birthday statistics.
	 */
	// @FXML
	// private void handleShowRevisaoStatistics() {
	// mainApp.showRevisaoStatistics();
	// }

	/**
	 * Opens an about dialog.
	 */
	@FXML
	private void handleAbout() {

		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Revisão Veícular");
		alert.setHeaderText("Sobre");
		alert.setContentText("Author: DevMedia\nWebsite: http://www.devmedia.com.br");
		alert.showAndWait();
	}

	/**
	 * Closes the application.
	 */
	@FXML
	private void handleExit() {

		System.exit(0);
	}
}
