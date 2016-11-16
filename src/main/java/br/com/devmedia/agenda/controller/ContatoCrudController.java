package br.com.devmedia.agenda.controller;

import java.util.Optional;

import br.com.devmedia.agenda.MainApp;
import br.com.devmedia.agenda.controller.dto.ContatoDTO;
import br.com.devmedia.agenda.controller.dto.TelefoneDTO;
import br.com.devmedia.agenda.model.entidades.Contato;
import br.com.devmedia.agenda.util.DateUtil;
import br.com.devmedia.agenda.util.FieldsUtils;
import br.com.devmedia.service.AgendaFacadeImpl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
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

	@FXML
	TextField dddTelefoneField;

	@FXML
	TextField numeroTelefoneField;

	private MainApp mainApp;

	private ContatoDTO contato;

	private AgendaFacadeImpl facade;

	public ContatoCrudController() {

		this.facade = new AgendaFacadeImpl();
	}

	@FXML
	private void initialize() {

		this.dddColumn.setCellValueFactory(cellData -> cellData.getValue().getDddStringProperty());

		this.numeroColumn.setCellValueFactory(cellData -> cellData.getValue().getNumeroStringProperty());

		FieldsUtils.numericField(this.numeroField);
		FieldsUtils.maxField(this.numeroField, 9);

		FieldsUtils.maxField(this.cepField, 9);

		FieldsUtils.numericField(this.dddTelefoneField);
		FieldsUtils.maxField(this.dddTelefoneField, 2);

		FieldsUtils.numericField(this.numeroTelefoneField);
		FieldsUtils.maxField(this.numeroTelefoneField, 9);

		FieldsUtils.dateField(this.dataNascimentoField);

	}

	public void setMainApp(final MainApp mainApp) {

		this.mainApp = mainApp;

	}

	private void showContatoDetails(final ContatoDTO contato) {

		this.nomeField.textProperty().bindBidirectional(contato.getNomeProperty());

		this.dataNascimentoField.setText(contato.getDataNascimentoString());

		if (contato != null) {

			this.preencherEndereco();

			this.preencherTelefones();
		}
	}

	private void preencherEndereco() {

		if (this.contato != null ) {
			this.descricaoField.textProperty().bindBidirectional(this.contato.getEndereco().getDescricaoProperty());
			this.complementoField.textProperty().bindBidirectional(this.contato.getEndereco().getComplementoProperty());
			this.cepField.textProperty().bindBidirectional(this.contato.getEndereco().getCepProperty());
			this.numeroField.textProperty().bindBidirectional(this.contato.getEndereco().getNumeroProperty());

		}

	}

	private void preencherTelefones() {

		if (this.contato != null

				&& this.contato.getTelefones() != null

				&& !this.contato.getTelefones().isEmpty()) {

			final ObservableList<TelefoneDTO> telefoneData = FXCollections.observableArrayList();

			telefoneData.addAll(this.contato.getTelefones());

			this.telefoneTable.setItems(telefoneData);
		}

	}

	@FXML
	private void handleSalvar() {

		try {

			this.contato.setDataNascimento(DateUtil.parse(this.dataNascimentoField.getText()));

			this.contato.setTelefones(telefoneTable.getItems());

			if (this.validarContato()) {

				final Contato entidade = this.contato.getEntidadeSincronizada();

				this.facade.salvarContato(entidade);

				this.mainApp.showContatoOverview();
			}
		} catch (Exception e) {

			e.printStackTrace();

			this.mainApp.alertException(e);

		}

	}

	private boolean validarContato() {

		StringBuilder msgValidacao = new StringBuilder();

		if (this.contato.getNome() == null || this.contato.getNome().trim().length() == 0) {

			msgValidacao.append("Campo Nome é obrigatório.").append("\n");
		}

		if (this.contato.getDataNascimento() == null) {

			msgValidacao.append("Campo Dt. Nascimento é inválido.").append("\n");
		}

		if (this.contato.temEndereco()) {

			if (this.contato.getEndereco().getCep() == null || this.contato.getEndereco().getCep().trim().length() == 0) {

				msgValidacao.append("Campo Endereço -> Cep é obrigatório.").append("\n");
			}

			if (this.contato.getEndereco().getDescricao() == null || this.contato.getEndereco().getDescricao().trim().length() == 0) {

				msgValidacao.append("Campo Endereço -> Descrição é obrigatório.").append("\n");
			}

			if (this.contato.getEndereco().getNumero() == null || this.contato.getEndereco().getNumero().trim().length() == 0) {

				msgValidacao.append("Campo Endereço -> Número é obrigatório.").append("\n");
			}
		}

		if (msgValidacao.length() > 0) {

			final Alert alert = new Alert(AlertType.ERROR);

			alert.setTitle("Erro");

			alert.setHeaderText("Foram encontrados erros no seu formulário");

			alert.setContentText(msgValidacao.toString());

			alert.setResizable(true);

			alert.getDialogPane().setPrefWidth(500);

			alert.showAndWait();

			return false;
		}

		return true;

	}

	@FXML
	private void handleCancelar() {

		this.mainApp.showContatoOverview();
	}

	@FXML
	private void handleDeletarTelefone() {

		final TelefoneDTO item = this.telefoneTable.getSelectionModel().getSelectedItem();

		if (item != null) {

			final Alert alert = new Alert(AlertType.CONFIRMATION);

			alert.setTitle("Confirmação");

			alert.setHeaderText("Você está prestes a excluir o registro.");

			alert.setContentText("Deseja continuar esta operação?");

			final Optional<ButtonType> result = alert.showAndWait();

			if (result.get() == ButtonType.OK) {

				try {

					this.facade.excluirTelefone(item.getEntidade());
					this.telefoneTable.getItems().remove(item);
				} catch (Exception e) {

					this.mainApp.alertException(e);
				}

			}

		} else {

			this.mainApp.alertNenhumItemSelecionado();
		}
	}

	@FXML
	public void handleAdicionarTelefone() {

		if (this.validarDadosTelefone()) {

			this.telefoneTable.getItems().add(new TelefoneDTO(null, Long.valueOf(this.dddTelefoneField.getText()), Long.valueOf(this.numeroTelefoneField.getText())));
		}

	}

	private boolean validarDadosTelefone() {

		final StringBuilder msgValidacao = new StringBuilder();

		if (this.dddTelefoneField.getText() == null || this.dddTelefoneField.getText().trim().length() == 0) {

			msgValidacao.append("Campo DDD é obrigatório").append("\n");
		}

		if (this.numeroTelefoneField.getText() == null || this.numeroTelefoneField.getText().trim().length() == 0) {

			msgValidacao.append("Campo Número é obrigatório").append("\n");
		}

		if (msgValidacao.length() > 0) {

			final Alert alert = new Alert(AlertType.ERROR);

			alert.setTitle("Erro");

			alert.setHeaderText("Foram encontrados erros no seu formulário");

			alert.setContentText(msgValidacao.toString());

			alert.setResizable(true);

			alert.getDialogPane().setPrefWidth(500);

			alert.showAndWait();

			return false;
		}

		return true;

	}

	public void setContrato(final ContatoDTO contato) {

		this.contato = contato;

	}

	public void recarregarPagina() {

		this.showContatoDetails(this.contato);
	}

	public TextField getNomeField() {

		return this.nomeField;
	}

	public void setNomeField(final TextField nomeField) {

		this.nomeField = nomeField;
	}

	public TextField getDataNascimentoField() {

		return this.dataNascimentoField;
	}

	public void setDataNascimentoField(final TextField dataNascimentoField) {

		this.dataNascimentoField = dataNascimentoField;
	}

	public TextField getDescricaoField() {

		return this.descricaoField;
	}

	public void setDescricaoField(final TextField descricaoField) {

		this.descricaoField = descricaoField;
	}

	public TextField getNumeroField() {

		return this.numeroField;
	}

	public void setNumeroField(final TextField numeroField) {

		this.numeroField = numeroField;
	}

	public TextField getCepField() {

		return this.cepField;
	}

	public void setCepField(final TextField cepField) {

		this.cepField = cepField;
	}

	public TextField getComplementoField() {

		return this.complementoField;
	}

	public void setComplementoField(final TextField complementoField) {

		this.complementoField = complementoField;
	}

}
