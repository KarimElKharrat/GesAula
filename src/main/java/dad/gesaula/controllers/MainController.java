package dad.gesaula.controllers;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import dad.gesaula.ui.model.Grupo;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

public class MainController implements Initializable {

	// model
	
	public ObjectProperty<Grupo> grupo = new SimpleObjectProperty<>();
	
	// controllers
	
	private GrupoController grupoController = new GrupoController();
	private AlumnosController alumnosController = new AlumnosController();
	
	// view
	
	@FXML
    private Tab alumnosTab;
    @FXML
    private Tab grupoTab;

    @FXML
    private TextField nombreText;

    @FXML
    private BorderPane view;
    
    public MainController() {
    	try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/MainView.fxml"));
			loader.setController(this);
			loader.load();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		// tab content
		
		grupoTab.setContent(grupoController.getView());
		alumnosTab.setContent(alumnosController.getView());
		
		// listeners
		
		grupo.addListener(this::onGrupoChangeListener);
		grupo.set(new Grupo());
		
	}
	
	private void onGrupoChangeListener(ObservableValue<? extends Grupo> o, Grupo ov, Grupo nv) {
		
		if(ov != null) {
			alumnosController.alumnosProperty().unbind();
			grupoController.grupoProperty().unbind();
		}
		
		if(nv != null) {
			alumnosController.alumnosProperty().bind(grupo.get().alumnosProperty());
			grupoController.grupoProperty().bind(grupo);
		}
		
	}

	@FXML
	void onGuardarAction(ActionEvent event) {
		
		try {
			if(!nombreText.getText().isBlank()) {
				File file = new File(nombreText.getText() + ".xml");
				if(!file.exists())
					file.createNewFile();
				grupo.get().save(file);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@FXML
	void onNuevoAction(ActionEvent event) {
		
		nombreText.setText("");
		grupo.set(new Grupo());
		
	}
	
	public BorderPane getView() {
		return view;
	}

}
