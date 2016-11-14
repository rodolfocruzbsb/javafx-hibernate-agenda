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

	public MainApp getMainApp() {

		return this.mainApp;
	}

	@FXML
	private void handleAbout() {

		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Agenda");
		alert.setHeaderText("Sobre");
		alert.setContentText("Author: DevMedia\nWebsite: http://www.devmedia.com.br");
		alert.showAndWait();
	}

	@FXML
	private void handleExit() {

		System.exit(0);
	}
}
