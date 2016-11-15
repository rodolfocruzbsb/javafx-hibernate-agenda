package br.com.devmedia.agenda.controller;

import br.com.devmedia.agenda.MainApp;
import br.com.devmedia.agenda.controller.dto.ContatoDTO;
import br.com.devmedia.agenda.controller.dto.TelefoneDTO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class ContatoCrudController {

	@FXML
	private TextField nomeField;

	@FXML
	private TextField dataNascimentoField;

	@FXML
	private TextField descricaoField;

	@FXML
	private TextField numeroField;

	@FXML
	private TextField cepField;

	@FXML
	private TextField complementoField;

	@FXML
	private TableView<TelefoneDTO> telefoneTable;

	@FXML
	private TableColumn<TelefoneDTO, String> dddColumn;

	@FXML
	private TableColumn<TelefoneDTO, String> numeroColumn;

	private MainApp mainApp;

	private ContatoDTO contato;

	public ContatoCrudController() {
	}

	/**
	 * Initializes the controller class. This method is automatically called after the fxml file has been loaded.
	 */
	@FXML
	private void initialize() {

		this.showContatoDetails(this.contato);

		dddColumn.setCellValueFactory(cellData -> cellData.getValue().getDddStringProperty());

		numeroColumn.setCellValueFactory(cellData -> cellData.getValue().getNumeroStringProperty());

	}

	/**
	 * Is called by the main application to give a reference back to itself.
	 * 
	 * @param mainApp
	 */
	public void setMainApp(MainApp mainApp) {

		this.mainApp = mainApp;

	}

	/**
	 * Fills all text fields to show details about the Contato. If the specified Contato is null, all text fields are cleared.
	 * 
	 * @param contato
	 *            the Contato or null
	 */
	private void showContatoDetails(ContatoDTO contato) {

		if (contato != null) {
			nomeField.setText(contato.getNome());
			dataNascimentoField.setText(contato.getDataNascimentoString());

			this.preencherEndereco();

			this.preencherTelefones();
		} else {

			nomeField.setText("");
			dataNascimentoField.setText("");
		}
	}

	private void preencherEndereco() {

		if (this.contato != null && this.contato.getEndereco() != null) {
			this.descricaoField.setText(this.contato.getEndereco().getDescricao());
			this.complementoField.setText(this.contato.getEndereco().getComplemento());
			this.cepField.setText(String.valueOf(this.contato.getEndereco().getCep()));
			this.numeroField.setText(this.contato.getEndereco().getNumero());

		}

	}

	private void preencherTelefones() {

		if (this.contato != null && this.contato.getTelefones() != null && !this.contato.getTelefones().isEmpty()) {
			final ObservableList<TelefoneDTO> telefoneData = FXCollections.observableArrayList();
			telefoneData.addAll(contato.getTelefones());

			this.telefoneTable.setItems(telefoneData);
		}

	}

	@FXML
	private void handleSalvar() {

	}

	@FXML
	private void handleCancelar() {

		mainApp.showContatoOverview();
	}

	public void setContrato(ContatoDTO contato) {

		this.contato = contato;

	}

	public void recarregarPagina() {

		this.showContatoDetails(this.contato);
	}

	public TextField getNomeField() {

		return nomeField;
	}

	public void setNomeField(TextField nomeField) {

		this.nomeField = nomeField;
	}

	public TextField getDataNascimentoField() {

		return dataNascimentoField;
	}

	public void setDataNascimentoField(TextField dataNascimentoField) {

		this.dataNascimentoField = dataNascimentoField;
	}

	public TextField getDescricaoField() {

		return descricaoField;
	}

	public void setDescricaoField(TextField descricaoField) {

		this.descricaoField = descricaoField;
	}

	public TextField getNumeroField() {

		return numeroField;
	}

	public void setNumeroField(TextField numeroField) {

		this.numeroField = numeroField;
	}

	public TextField getCepField() {

		return cepField;
	}

	public void setCepField(TextField cepField) {

		this.cepField = cepField;
	}

	public TextField getComplementoField() {

		return complementoField;
	}

	public void setComplementoField(TextField complementoField) {

		this.complementoField = complementoField;
	}

}
