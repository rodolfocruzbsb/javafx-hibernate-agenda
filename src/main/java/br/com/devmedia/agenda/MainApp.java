package br.com.devmedia.agenda;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import br.com.devmedia.agenda.controller.ContatoCrudController;
import br.com.devmedia.agenda.controller.ContatoOverviewController;
import br.com.devmedia.agenda.controller.RootLayoutController;
import br.com.devmedia.agenda.controller.dto.ContatoDTO;
import br.com.devmedia.agenda.controller.dto.EnderecoDTO;
import br.com.devmedia.agenda.controller.dto.TelefoneDTO;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MainApp extends Application {

	private Stage primaryStage;

	private BorderPane rootLayout;

	private final ObservableList<ContatoDTO> contatos = FXCollections.observableArrayList();

	public MainApp() {

		// TODO aqui será recuperado do banco de dados
		ContatoDTO c1 = new ContatoDTO("Rodolfo dos Santos Cruz", LocalDate.of(1988, 10, 19));
		//endereco
		EnderecoDTO e1 = new EnderecoDTO();
		e1.setCep(71927180);
		e1.setComplemento("Complemento teste 1");
		e1.setDescricao("Rua 25 Sul");
		e1.setNumero("903");
		
		//telefones
		List<TelefoneDTO> telefones1 = new ArrayList<>();
		telefones1.add(new TelefoneDTO(61, 994234849));
		telefones1.add(new TelefoneDTO(61, 30126135));
		
		c1.setEndereco(e1);
		c1.setTelefones(telefones1);
		
		this.contatos.add(c1);
		ContatoDTO c2 = new ContatoDTO("Camila Milena Barbosa", LocalDate.of(1986, 3, 12));

		//endereco
		EnderecoDTO e2 = new EnderecoDTO();
		e2.setCep(72222222);
		e2.setComplemento("Complemento teste 2");
		e2.setDescricao("Rua 2");
		e2.setNumero("2");
		
		//telefones
		List<TelefoneDTO> telefones2 = new ArrayList<>();
		telefones2.add(new TelefoneDTO(62, 12345678));
		
		c2.setEndereco(e2);
		c2.setTelefones(telefones2);
		
		this.contatos.add(c2);
	}

	@Override
	public void start(final Stage primaryStage) {

		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("Agenda DevMedia");

		this.primaryStage.getIcons().add(new Image("file:resources/images/address_book_32.png"));

		this.initRootLayout();

		this.showContatoOverview();
	}

	public void initRootLayout() {

		try {

			final FXMLLoader loader = new FXMLLoader();

			loader.setLocation(MainApp.class.getResource("view/RootLayout.fxml"));

			this.rootLayout = (BorderPane) loader.load();

			final Scene scene = new Scene(this.rootLayout);
			this.primaryStage.setScene(scene);

			final RootLayoutController controller = loader.getController();
			controller.setMainApp(this);

			this.primaryStage.show();
		} catch (final IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Shows the Contato overview inside the root layout.
	 */
	public void showContatoOverview() {

		try {
			final FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/ContatoOverview.fxml"));
			final AnchorPane contatoOverview = (AnchorPane) loader.load();

			this.rootLayout.setCenter(contatoOverview);

			final ContatoOverviewController controller = loader.getController();
			controller.setMainApp(this);

		} catch (final IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Opens a dialog to edit details for the specified Contato. If the user clicks OK, the changes are saved into the provided Contato object and true is returned.
	 * 
	 * @param Contato
	 *            the Contato object to be edited
	 * @return true if the user clicked OK, false otherwise.
	 */
	public void showContatoEditDialog(final ContatoDTO contato) {

		try {
			final FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/ContatoCrud.fxml"));
			final AnchorPane contatoCrud = (AnchorPane) loader.load();

			this.rootLayout.setCenter(contatoCrud);

			final ContatoCrudController controller = loader.getController();

			controller.setContrato(contato);
			controller.recarregarPagina();
			controller.setMainApp(this);

		} catch (final IOException e) {
			e.printStackTrace();
		}
	}

	public Stage getPrimaryStage() {

		return this.primaryStage;
	}

	public ObservableList<ContatoDTO> getContatoData() {

		return contatos;
	}

	public static void main(final String[] args) {

		Application.launch(args);
	}

}
