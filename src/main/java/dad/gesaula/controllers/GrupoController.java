package dad.gesaula.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class GrupoController implements Initializable {

	@FXML
    private Label examenesLabel;
	@FXML
    private Label practicasLabel;
	@FXML
    private Label actitudLabel;
	
	@FXML
	private Slider examenesSlider;
	@FXML
	private Slider practicasSlider;
	@FXML
    private Slider actitudSlider;

    @FXML
    private TextField denominacionText;

    @FXML
    private DatePicker finDate;
    @FXML
    private DatePicker inicioDate;

    @FXML
    private GridPane view;

	
	public GrupoController() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/GrupoView.fxml"));
			loader.setController(this);
			loader.load();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		// bindings
		
		MainController.grupo.denominacionProperty().bind(denominacionText.textProperty());
		MainController.grupo.iniCursoProperty().bind(inicioDate.valueProperty());
		MainController.grupo.finCursoProperty().bind(finDate.valueProperty());
		MainController.grupo.getPesos().examenesProperty().bind(examenesSlider.valueProperty());
		MainController.grupo.getPesos().practicasProperty().bind(practicasSlider.valueProperty());
		MainController.grupo.getPesos().actitudProperty().bind(actitudSlider.valueProperty());
		examenesLabel.textProperty().bind(MainController.grupo.getPesos().examenesProperty().asString("%.2f%%"));
		practicasLabel.textProperty().bind(MainController.grupo.getPesos().practicasProperty().asString("%.2f%%"));
		actitudLabel.textProperty().bind(MainController.grupo.getPesos().actitudProperty().asString("%.2f%%"));
		
	}
	
	public GridPane getView() {
		return view;
	}

}
