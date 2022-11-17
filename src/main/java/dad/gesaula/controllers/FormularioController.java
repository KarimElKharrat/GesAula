package dad.gesaula.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import dad.gesaula.ui.model.Alumno;
import dad.gesaula.ui.model.Sexo;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class FormularioController implements Initializable {
	
	@FXML
	private TextField nombreText;
	@FXML
    private TextField apellidosText;

    @FXML
    private DatePicker nacimientoDate;
    
    @FXML
    private ComboBox<Sexo> sexoBox;

    @FXML
    private CheckBox repiteCheck;

    @FXML
    private GridPane view;
    
    public FormularioController() {
    	try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/FormularioView.fxml"));
			loader.setController(this);
			loader.load();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		// load data
		
		sexoBox.getItems().addAll(Sexo.HOMBRE, Sexo.MUJER);
		
	}
	
	public void setDatos(Alumno alumno) {
		nombreText.textProperty().bindBidirectional(alumno.nombreProperty());
		apellidosText.textProperty().bindBidirectional(alumno.apellidosProperty());
		nacimientoDate.valueProperty().bindBidirectional(alumno.fechaNacimientoProperty());
		sexoBox.valueProperty().bindBidirectional(alumno.sexoProperty());
		repiteCheck.selectedProperty().bindBidirectional(alumno.repiteProperty());
		
	}
	
	public GridPane getView() {
		return view;
	}

}
