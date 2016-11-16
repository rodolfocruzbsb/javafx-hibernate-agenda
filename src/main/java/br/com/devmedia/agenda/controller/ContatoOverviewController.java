package br.com.devmedia.agenda.controller;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import br.com.devmedia.agenda.MainApp;
import br.com.devmedia.agenda.controller.dto.ContatoDTO;
import br.com.devmedia.agenda.controller.dto.TelefoneDTO;
import br.com.devmedia.agenda.model.entidades.Contato;
import br.com.devmedia.agenda.util.DateUtil;
import br.com.devmedia.service.AgendaFacadeImpl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
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

	private final ObservableList<ContatoDTO> contatos = FXCollections.observableArrayList();

	private AgendaFacadeImpl facade;

	public ContatoOverviewController() {
		this.facade = new AgendaFacadeImpl();

	}

	@FXML
	private void initialize() {

		this.carregarContatosDoBanco();

		this.carregarTela();

	}

	private void enviarContatosParaTela(final Collection<Contato> contatosEntity) {

		this.contatos.addAll(contatosEntity.stream().map(c -> ContatoDTO.from(c)).collect(Collectors.toList()));

		this.contatoTable.setItems(this.contatos);
	}

	private void carregarTela() {

		this.nomeColumn.setCellValueFactory(cellData -> cellData.getValue().getNomeProperty());

		this.dataNascimentoColumn.setCellValueFactory(cellData -> cellData.getValue().getDataNascimentoStringProperty());

		this.dddColumn.setCellValueFactory(cellData -> cellData.getValue().getDddStringProperty());

		this.numeroColumn.setCellValueFactory(cellData -> cellData.getValue().getNumeroStringProperty());

		this.detalharContatoSelecionado(null);

		this.contatoTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> this.detalharContatoSelecionado(newValue));
	}

	public void setMainApp(final MainApp mainApp) {

		this.mainApp = mainApp;

	}

	private void detalharContatoSelecionado(final ContatoDTO contato) {

		if (contato != null) {

			this.nomeLabel.setText(contato.getNome());
			this.dataNascimentoLabel.setText(DateUtil.format(contato.getDataNascimento()));
			this.enderecoLabel.setText(contato.getEndereco().getDescricao());
			this.complementoLabel.setText(contato.getEndereco().getComplemento());
			this.cepLabel.setText(contato.getEndereco().getCep());
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

		final ContatoDTO selectedContato = this.contatoTable.getSelectionModel().getSelectedItem();

		if (selectedContato != null) {
			final Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("Confirmação");
			alert.setHeaderText("Você está prestes a excluir o registro.");
			alert.setContentText("Deseja continuar esta operação?");

			final Optional<ButtonType> result = alert.showAndWait();
			if (result.get() == ButtonType.OK) {
				System.out.println("Excluiu!!!");
			}

		} else {

			this.mainApp.alertNenhumItemSelecionado();
		}

	}

	@FXML
	private void handleNewContato() {

		this.mainApp.showContatoEdit(new ContatoDTO());
	}

	@FXML
	private void handleEditContato() {

		final ContatoDTO selectedContato = this.contatoTable.getSelectionModel().getSelectedItem();

		if (selectedContato != null) {

			this.mainApp.showContatoEdit(selectedContato);
		} else {

			this.mainApp.alertNenhumItemSelecionado();
		}
	}

	private void carregarContatosDoBanco() {

		final List<Contato> contatosEntity = this.facade.buscarTodosContatos();

		this.enviarContatosParaTela(contatosEntity);

	}

}
