package br.com.devmedia.agenda;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import br.com.devmedia.agenda.controller.ContatoCrudController;
import br.com.devmedia.agenda.controller.ContatoOverviewController;
import br.com.devmedia.agenda.controller.RootLayoutController;
import br.com.devmedia.agenda.controller.dto.ContatoDTO;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;

public class MainApp extends Application {

	private Stage primaryStage;

	private BorderPane rootLayout;

	public MainApp() {

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

	public void showContatoEdit(final ContatoDTO contato) {

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

	public static void main(final String[] args) {

		Application.launch(args);
	}

	public void alertNenhumItemSelecionado() {

		final Alert alert = new Alert(AlertType.WARNING);
		alert.setTitle("Nenhum Item foi selecionado");
		alert.setHeaderText("Nenhum item foi selecionado");
		alert.setContentText("Por favor, selecione um item para realizar esta ação.");
		alert.setResizable(true);
		alert.getDialogPane().setPrefWidth(500);
		alert.showAndWait();
	}
	
	public void alertException( Exception ex){
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Exception Dialog");
		alert.setHeaderText("Look, an Exception Dialog");
		alert.setContentText("Could not find file blabla.txt!");

		// Create expandable Exception.
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		ex.printStackTrace(pw);
		String exceptionText = sw.toString();

		Label label = new Label("The exception stacktrace was:");

		TextArea textArea = new TextArea(exceptionText);
		textArea.setEditable(false);
		textArea.setWrapText(true);

		textArea.setMaxWidth(Double.MAX_VALUE);
		textArea.setMaxHeight(Double.MAX_VALUE);
		GridPane.setVgrow(textArea, Priority.ALWAYS);
		GridPane.setHgrow(textArea, Priority.ALWAYS);

		GridPane expContent = new GridPane();
		expContent.setMaxWidth(Double.MAX_VALUE);
		expContent.add(label, 0, 0);
		expContent.add(textArea, 0, 1);

		// Set expandable Exception into the dialog pane.
		alert.getDialogPane().setExpandableContent(expContent);

		alert.showAndWait();
	}
}
