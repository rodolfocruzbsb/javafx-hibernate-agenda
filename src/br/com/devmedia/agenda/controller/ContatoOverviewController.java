package br.com.devmedia.agenda.controller;

import br.com.devmedia.agenda.MainApp;
import br.com.devmedia.agenda.controller.dto.ContatoDTO;
import br.com.devmedia.agenda.controller.dto.TelefoneDTO;
import br.com.devmedia.agenda.util.DateUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class ContatoOverviewController {

	@FXML
	private TableView<ContatoDTO> contatoTable;

	@FXML
	private TableView<TelefoneDTO> telefoneTable;

	@FXML
	private TableColumn<ContatoDTO, String> nomeColumn;

	@FXML
	private TableColumn<ContatoDTO, String> dataNascimentoColumn;

	@FXML
	private TableColumn<TelefoneDTO, String> dddColumn;

	@FXML
	private TableColumn<TelefoneDTO, String> numeroColumn;

	@FXML
	private Label nomeLabel;

	@FXML
	private Label dataNascimentoLabel;
	
	/*
	 * ENDERECO
	 */
	@FXML
	private Label enderecoLabel;

	@FXML
	private Label complementoLabel;
	
	@FXML
	private Label cepLabel;
	
	@FXML
	private Label numeroLabel;
	
	private MainApp mainApp;

	public ContatoOverviewController() {
	}

	@FXML
	private void initialize() {

		nomeColumn.setCellValueFactory(cellData -> cellData.getValue().getNomeProperty());

		dataNascimentoColumn.setCellValueFactory(cellData -> cellData.getValue().getDataNascimentoStringProperty());
		
		dddColumn.setCellValueFactory(cellData -> cellData.getValue().getDddStringProperty());
		
		numeroColumn.setCellValueFactory(cellData -> cellData.getValue().getNumeroStringProperty());

		showContatoDetails(null);

		contatoTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> showContatoDetails(newValue));
	}

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

			this.nomeLabel.setText(contato.getNome());
			this.dataNascimentoLabel.setText(DateUtil.format(contato.getDataNascimento()));
			this.enderecoLabel.setText(contato.getEndereco().getDescricao());
			this.complementoLabel.setText(contato.getEndereco().getComplemento());
			this.cepLabel.setText(String.valueOf(contato.getEndereco().getCep()));
			this.numeroLabel.setText(contato.getEndereco().getNumero());
			
			final ObservableList<TelefoneDTO> telefoneData = FXCollections.observableArrayList();
			telefoneData.addAll(contato.getTelefones());
			
			this.telefoneTable.setItems(telefoneData);
		} else {

			this.nomeLabel.setText("");
			this.dataNascimentoLabel.setText("");
			this.enderecoLabel.setText("");
			this.complementoLabel.setText("");
			this.cepLabel.setText("");
			this.numeroLabel.setText("");
		}
	}

	@FXML
	private void handleDeleteContato() {

		int selectedIndex = contatoTable.getSelectionModel().getSelectedIndex();

		if (selectedIndex >= 0) {

			contatoTable.getItems().remove(selectedIndex);
		} else {

			alertNenhumItemSelecionado();
		}
	}

	@FXML
	private void handleNewContato() {

		mainApp.showContatoEditDialog(new ContatoDTO());
	}

	@FXML
	private void handleEditContato() {

		ContatoDTO selectedContato = contatoTable.getSelectionModel().getSelectedItem();
		if (selectedContato != null) {

			mainApp.showContatoEditDialog(selectedContato);
		} else {

			alertNenhumItemSelecionado();
		}
	}

	private void alertNenhumItemSelecionado() {

		Alert alert = new Alert(AlertType.WARNING);
		alert.setTitle("Nenhum Item foi selecionado");
		alert.setHeaderText("Nenhum contato foi selecionado");
		alert.setContentText("Por favor, selecione um contato para realizar esta ação.");
		alert.setResizable(true);
		alert.getDialogPane().setPrefWidth(500);
		alert.showAndWait();
	}
	
}
