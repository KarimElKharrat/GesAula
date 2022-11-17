package dad.gesaula.controllers;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;

import dad.gesaula.GesAulaApp;
import dad.gesaula.ui.model.Alumno;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;

public class AlumnosController implements Initializable {
	
	// model
	
//	private ListProperty<Alumno> alumnos = new SimpleListProperty<>(FXCollections.observableArrayList());

	// controllers
	
	private FormularioController formularioController = new FormularioController();
	
	// view

	@FXML
    private Label noSelectedLabel;
	
	@FXML
    private Button eliminarButton;
	
    @FXML
    private TableColumn<Alumno, String> nombreColumn;
	@FXML
    private TableColumn<Alumno, String> apellidosColumn;
	@FXML
	private TableColumn<Alumno, LocalDate> fechaNacimientoColumn;

    @FXML
    private TableView<Alumno> datosAlumnoTable;

    @FXML
    private BorderPane formularioBorderPane;
    
    @FXML
    private ImageView eliminarImage;
    @FXML
    private ImageView nuevoImage;

    @FXML
    private SplitPane view;
	
	public AlumnosController() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/AlumnosView.fxml"));
			loader.setController(this);
			loader.load();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		datosAlumnoTable.getSelectionModel().selectedItemProperty().addListener((o, ov, nv) -> {
			
			if(nv != null) {
				formularioController = new FormularioController();
				formularioController.setDatos(nv);
				formularioBorderPane.setTop(formularioController.getView());
				noSelectedLabel.setVisible(false);
			} else {
				formularioBorderPane.setTop(null);
				noSelectedLabel.setVisible(true);
			}
			
		});
		
		// bindings
		
		datosAlumnoTable.itemsProperty().bind(MainController.grupo.alumnosProperty());
		eliminarButton.disableProperty().bind(datosAlumnoTable.getSelectionModel().selectedItemProperty().isNull());
		
		// cell value factories
		
		nombreColumn.setCellValueFactory(v -> v.getValue().nombreProperty());
		apellidosColumn.setCellValueFactory(v -> v.getValue().apellidosProperty());
		fechaNacimientoColumn.setCellValueFactory(v -> v.getValue().fechaNacimientoProperty());
		
		// load data
		
		nuevoImage.setImage(new Image(getClass().getResource("/images/add-32x32.png").toString()));
		eliminarImage.setImage(new Image(getClass().getResource("/images/del-32x32.png").toString()));
		
	}
	
	@FXML
	void onEliminarAction(ActionEvent event) {
		Alert alert = new Alert(AlertType.CONFIRMATION);
    	alert.setTitle("Eliminar alumno");
    	alert.setHeaderText(
			"Se va a eliminar al alumno '"
	    	+ datosAlumnoTable.getSelectionModel().getSelectedItem().getNombre() + " "
	    	+ datosAlumnoTable.getSelectionModel().getSelectedItem().getApellidos() + "'."
    	);
    	
    	alert.setContentText("¿Está seguro?");
    	alert.initOwner(GesAulaApp.primaryStage);
    	
    	Optional<ButtonType> result = alert.showAndWait();
    	if (result.get() == ButtonType.OK){
    		MainController.grupo.getAlumnos().remove(datosAlumnoTable.getSelectionModel().getSelectedItem());
    	}
		
	}
	
	@FXML
	void onNuevoAction(ActionEvent event) {
		MainController.grupo.getAlumnos().add(new Alumno());
	}
	
	public void clear() {
		datosAlumnoTable.getItems().clear();
		datosAlumnoTable.itemsProperty().bind(MainController.grupo.alumnosProperty());
	}	

	public SplitPane getView() {
		return view;
	}
	
}
