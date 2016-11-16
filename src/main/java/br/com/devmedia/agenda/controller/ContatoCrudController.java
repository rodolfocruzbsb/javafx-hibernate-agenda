package br.com.devmedia.agenda.controller;

import java.util.Optional;

import br.com.devmedia.agenda.MainApp;
import br.com.devmedia.agenda.controller.dto.ContatoDTO;
import br.com.devmedia.agenda.controller.dto.GrupoDTO;
import br.com.devmedia.agenda.controller.dto.TelefoneDTO;
import br.com.devmedia.agenda.model.entidades.Contato;
import br.com.devmedia.agenda.model.entidades.Grupo;
import br.com.devmedia.agenda.util.DateUtil;
import br.com.devmedia.agenda.util.FieldsUtils;
import br.com.devmedia.service.AgendaFacadeImpl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.util.StringConverter;

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
	private TableView<GrupoDTO> grupoTable;

	@FXML
	private TableColumn<TelefoneDTO, String> dddColumn;

	@FXML
	private TableColumn<TelefoneDTO, String> numeroColumn;

	@FXML
	private TableColumn<GrupoDTO, String> grupoNomeColumn;

	@FXML
	private TableColumn<GrupoDTO, String> grupoDescricaoColumn;

	@FXML
	private TextField dddTelefoneField;

	@FXML
	private TextField numeroTelefoneField;

	private MainApp mainApp;

	private ContatoDTO contato;

	private AgendaFacadeImpl facade;

	@FXML
	private ComboBox<Grupo> grupoCombobox;

	public ContatoCrudController() {

		this.facade = new AgendaFacadeImpl();
	}

	@FXML
	private void initialize() {

		this.dddColumn.setCellValueFactory(cellData -> cellData.getValue().getDddStringProperty());

		this.numeroColumn.setCellValueFactory(cellData -> cellData.getValue().getNumeroStringProperty());

		this.grupoNomeColumn.setCellValueFactory(cellData -> cellData.getValue().getNomeProperty());

		this.grupoDescricaoColumn.setCellValueFactory(cellData -> cellData.getValue().getDescricaoProperty());

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

	private void showContatoDetails() {

		this.nomeField.textProperty().bindBidirectional(this.contato.getNomeProperty());

		this.dataNascimentoField.setText(this.contato.getDataNascimentoString());

		this.preencherEndereco();

		this.preencherTelefones();

		this.preencherGrupos();
	}

	private void preencherEndereco() {

		if (this.contato != null) {

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

			final ObservableList<TelefoneDTO> telefoneItens = FXCollections.observableArrayList();

			telefoneItens.addAll(this.contato.getTelefones());

			this.telefoneTable.setItems(telefoneItens);
		}

	}

	private class GrupoChooserConverter<T> extends StringConverter<Grupo> {

		@Override
		public Grupo fromString(final String grupo) {

			// This is the important code!
			return null;
		}

		@Override
		public String toString(final Grupo grupo) {

			if (grupo == null) {
				return null;
			}
			return grupo.getNome();
		}
	}

	private void preencherGrupos() {

		// preenche combobox
		final ObservableList<Grupo> grupoItens = FXCollections.observableArrayList();

		grupoItens.addAll(this.facade.buscarTodosGrupos());

		this.grupoCombobox.setItems(grupoItens);

		this.grupoCombobox.setCellFactory((comboBox) -> {
			return new ListCell<Grupo>() {

				@Override
				protected void updateItem(Grupo item, boolean empty) {

					super.updateItem(item, empty);

					if (item == null || empty) {
						setText(null);
					} else {
						setText(item.getId() + " - " + item.getNome());
					}
				}
			};
		});
		
		this.grupoCombobox.setConverter(new GrupoChooserConverter<>());
		
		//StringConverter<Grupo> converter = this.grupoCombobox.getConverter();

		

		if (this.contato != null

				&& this.contato.getGrupos() != null

				&& !this.contato.getGrupos().isEmpty()) {

			final ObservableList<GrupoDTO> grupoDTOItens = FXCollections.observableArrayList();

			grupoDTOItens.addAll(this.contato.getGrupos());

			this.grupoTable.setItems(grupoDTOItens);
		}

	}

	@FXML
	private void handleSalvar() {

		try {

			this.contato.setDataNascimento(DateUtil.parse(this.dataNascimentoField.getText()));

			this.contato.setTelefones(telefoneTable.getItems());

			this.contato.setGrupos(grupoTable.getItems());

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

	@FXML
	private void handleDeletarGrupo() {

		final int index = this.grupoTable.getSelectionModel().getSelectedIndex();

		if (index >= 0) {

			final Alert alert = new Alert(AlertType.CONFIRMATION);

			alert.setTitle("Confirmação");

			alert.setHeaderText("Você está prestes a excluir o registro.");

			alert.setContentText("Deseja continuar esta operação?");

			final Optional<ButtonType> result = alert.showAndWait();

			if (result.get() == ButtonType.OK) {

				this.grupoTable.getItems().remove(index);

			}

		} else {

			this.mainApp.alertNenhumItemSelecionado();
		}
	}

	@FXML
	public void handleAdicionarGrupo() {

		if (this.validarDadosGrupo()) {

			final Grupo grupo = this.grupoCombobox.getSelectionModel().getSelectedItem();

			this.grupoTable.getItems().add(new GrupoDTO(grupo, grupo.getNome(), grupo.getDescricao()));
		}

	}

	private boolean validarDadosGrupo() {

		final StringBuilder msgValidacao = new StringBuilder();
		Grupo selected = this.grupoCombobox.getSelectionModel().getSelectedItem();

		if (selected == null || selected.getId() == null) {

			msgValidacao.append("Selecione o grupo...").append("\n");
		} else {

			boolean jaExiste = this.grupoTable.getItems().stream().filter(g -> selected.getId().equals(g.getEntidade().getId())).count() > 0;

			if (jaExiste) {

				msgValidacao.append("Este grupo já foi adicionado!").append("\n");
			}
		}

		if (msgValidacao.length() > 0) {

			final Alert alert = new Alert(AlertType.ERROR);

			alert.setTitle("Erro");

			alert.setHeaderText("Foram encontrados ao adicionar o item");

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

		this.showContatoDetails();
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

	/**
	 * Retorna o valor do atributo <code>grupoCombobox</code>
	 *
	 * @return <code>ComboBox<Grupo></code>
	 */
	public ComboBox<Grupo> getGrupoCombobox() {

		return grupoCombobox;
	}

	/**
	 * Define o valor do atributo <code>grupoCombobox</code>.
	 *
	 * @param grupoCombobox
	 */
	public void setGrupoCombobox(ComboBox<Grupo> grupoCombobox) {

		this.grupoCombobox = grupoCombobox;
	}

}
