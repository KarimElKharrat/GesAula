package dad.gesaula.controllers;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;

import dad.gesaula.GesAulaApp;
import dad.gesaula.ui.model.Alumno;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;

public class AlumnosController implements Initializable {
	
	// model
	
	private ListProperty<Alumno> alumnos = new SimpleListProperty<>(FXCollections.observableArrayList());

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
		
		// listeners
		
		datosAlumnoTable.getSelectionModel().selectedItemProperty().addListener(this::onSelectedItem);
		
		alumnos.addListener(this::onChangeListener);
		
		// bindings
		
		eliminarButton.disableProperty().bind(datosAlumnoTable.getSelectionModel().selectedItemProperty().isNull());
		
	}

	private void onChangeListener(ObservableValue<? extends ObservableList<Alumno>> o, ObservableList<Alumno> ov, ObservableList<Alumno> nv) {
		
		if(ov != null) {
			
			datosAlumnoTable.itemsProperty().unbind();
			
		}
		
		if(nv != null) {
			
			datosAlumnoTable.itemsProperty().bind(alumnos);
			
			// cell value factories
			
			nombreColumn.setCellValueFactory(v -> v.getValue().nombreProperty());
			apellidosColumn.setCellValueFactory(v -> v.getValue().apellidosProperty());
			fechaNacimientoColumn.setCellValueFactory(v -> v.getValue().fechaNacimientoProperty());
			
		}
		
	}
	
	private void onSelectedItem(ObservableValue<? extends Alumno> o, Alumno ov, Alumno nv) {
			
		if(nv != null) {
			
			formularioController = new FormularioController();
			formularioController.setDatos(nv);
			formularioBorderPane.setTop(formularioController.getView());
			noSelectedLabel.setVisible(false);
			
		} else {
			
			formularioBorderPane.setTop(null);
			noSelectedLabel.setVisible(true);
			
		}
		
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
    		alumnos.get().remove(datosAlumnoTable.getSelectionModel().getSelectedItem());
    	}
		
	}
	
	@FXML
	void onNuevoAction(ActionEvent event) {
		alumnos.get().add(new Alumno());
	}
	
	public void clear() {
		datosAlumnoTable.getItems().clear();
		datosAlumnoTable.itemsProperty().bind(alumnos);
	}	

	public SplitPane getView() {
		return view;
	}

	public final ListProperty<Alumno> alumnosProperty() {
		return this.alumnos;
	}
	
	public final ObservableList<Alumno> getAlumnos() {
		return this.alumnosProperty().get();
	}

	public final void setAlumnos(final ObservableList<Alumno> alumnos) {
		this.alumnosProperty().set(alumnos);
	}
	
}
