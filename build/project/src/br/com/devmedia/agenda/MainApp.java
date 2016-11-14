package br.com.devmedia.agenda;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;

import br.com.devmedia.agenda.controller.ContatoEditDialogController;
import br.com.devmedia.agenda.controller.ContatoOverviewController;
import br.com.devmedia.agenda.controller.RootLayoutController;
import br.com.devmedia.agenda.controller.dto.ContatoDTO;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class MainApp extends Application {

	private Stage primaryStage;

	private BorderPane rootLayout;

	/**
	 * The data as an observable list of Veiculos.
	 */
	private final ObservableList<ContatoDTO> contatos = FXCollections.observableArrayList();

	/**
	 * Constructor
	 */
	public MainApp() {

		// TODO aqui será recuperado do banco de dados
		this.contatos.add(new ContatoDTO("Rodolfo dos Santos Cruz", LocalDate.of(1988, 10, 19)));
		this.contatos.add(new ContatoDTO("Camila Milena Barbosa", LocalDate.of(1986, 3, 12)));
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
			// Load Contato overview.
			final FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/ContatoOverview.fxml"));
			final AnchorPane contatoOverview = (AnchorPane) loader.load();

			// Set Contato overview into the center of root layout.
			this.rootLayout.setCenter(contatoOverview);

			// Give the controller access to the main app.
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
	public boolean showContatoEditDialog(final ContatoDTO contato) {

		try {
			// Load the fxml file and create a new stage for the popup dialog.
			final FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/ContatoEditDialog.fxml"));
			final AnchorPane page = (AnchorPane) loader.load();

			// Create the dialog Stage.
			final Stage dialogStage = new Stage();
			dialogStage.setTitle("Edit Contato");
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(this.primaryStage);
			dialogStage.getIcons().add(new Image("file:resources/images/edit.png"));
			final Scene scene = new Scene(page);
			dialogStage.setScene(scene);

			// Set the Contato into the controller.
			final ContatoEditDialogController controller = loader.getController();
			controller.setDialogStage(dialogStage);
			controller.setContato(contato);

			// Show the dialog and wait until the user closes it
			dialogStage.showAndWait();

			return controller.isOkClicked();
		} catch (final IOException e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Loads Contato data from the specified file. The current Contato data will be replaced.
	 * 
	 * @param file
	 */
	public void loadContatoDataFromFile(final File file) {

		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Testing");
		alert.setHeaderText(null);
		alert.setContentText("br.com.devmedia.agenda.MainApp.loadContatoDataFromFile(File)");
		alert.showAndWait();
	}

	public void saveContatoDataToFile(final File file) {

		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Testing");
		alert.setHeaderText(null);
		alert.setContentText("br.com.devmedia.agenda.MainApp.saveContatoDataToFile(File)");
		alert.showAndWait();
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
