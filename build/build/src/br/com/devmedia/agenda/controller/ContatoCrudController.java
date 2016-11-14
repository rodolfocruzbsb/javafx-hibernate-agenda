package br.com.devmedia.agenda.controller;

import br.com.devmedia.agenda.MainApp;
import br.com.devmedia.agenda.controller.dto.ContatoDTO;
import br.com.devmedia.agenda.util.DateUtil;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class ContatoCrudController {

	@FXML
	private TableView<ContatoDTO> contatoTable;

	@FXML
	private TableColumn<ContatoDTO, String> nomeColumn;

	@FXML
	private TableColumn<ContatoDTO, String> dataNascimentoColumn;

	@FXML
	private Label nomeLabel;

	@FXML
	private Label dataNascimentoLabel;

	// Reference to the main application.
	private MainApp mainApp;

	/**
	 * The constructor. The constructor is called before the initialize() method.
	 */
	public ContatoCrudController() {
	}

	/**
	 * Initializes the controller class. This method is automatically called after the fxml file has been loaded.
	 */
	@FXML
	private void initialize() {

		nomeColumn.setCellValueFactory(cellData -> cellData.getValue().getNomeProperty());
		
		dataNascimentoColumn.setCellValueFactory(cellData -> cellData.getValue().getDataNascimentoStringProperty());

		showContatoDetails(null);

		contatoTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> showContatoDetails(newValue));
	}

	/**
	 * Is called by the main application to give a reference back to itself.
	 * 
	 * @param mainApp
	 */
	public void setMainApp(MainApp mainApp) {

		this.mainApp = mainApp;

		contatoTable.setItems(mainApp.getContatoData());
	}

	/**
	 * Fills all text fields to show details about the Contato. If the specified Contato is null, all text fields are cleared.
	 * 
	 * @param contato
	 *            the Contato or null
	 */
	private void showContatoDetails(ContatoDTO contato) {

		if (contato != null) {

			nomeLabel.setText(contato.getNome());
			dataNascimentoLabel.setText(DateUtil.format(contato.getDataNascimento()));
		} else {

			nomeLabel.setText("");
			dataNascimentoLabel.setText("");
		}
	}

	/**
	 * Called when the user clicks on the delete button.
	 */
	@FXML
	private void handleDeleteContato() {

		int selectedIndex = contatoTable.getSelectionModel().getSelectedIndex();

		if (selectedIndex >= 0) {

			contatoTable.getItems().remove(selectedIndex);
		} else {

			// Nothing selected.
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Nenhum item selecionado");
			alert.setHeaderText("Nenhum veículo foi selecionado para exclusão!");
			alert.setContentText("Por favor, selecione um veículo na tabela para esta listagem.");
			alert.showAndWait();
		}
	}

	/**
	 * Called when the user clicks the new button. Opens a dialog to edit details for a new Contato.
	 */
	@FXML
	private void handleNewContato() {

		ContatoDTO tempContato = new ContatoDTO();
		boolean okClicked = mainApp.showContatoEditDialog(tempContato);
		if (okClicked) {
			mainApp.getContatoData().add(tempContato);
		}
	}

	@FXML
	private void handleEditContato() {

		ContatoDTO selectedContato = contatoTable.getSelectionModel().getSelectedItem();
		if (selectedContato != null) {
			boolean okClicked = mainApp.showContatoEditDialog(selectedContato);
			if (okClicked) {
				showContatoDetails(selectedContato);
			}

		} else {
			// Nothing selected.
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("No Selection");
			alert.setHeaderText("No Contato Selected");
			alert.setContentText("Please select a Contato in the table.");
			alert.showAndWait();
		}
	}
}
