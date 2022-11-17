package dad.gesaula.controllers;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import dad.gesaula.ui.model.Grupo;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

public class MainController implements Initializable {

	// model
	
	public static Grupo grupo = new Grupo();
	
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
		
		tabContent();
		
	}
	
	@FXML
	void onGuardarAction(ActionEvent event) {
		
		try {
			File file = new File(nombreText.getText() + ".xml");
			if(!file.exists())
				file.createNewFile();
			MainController.grupo.save(file);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@FXML
	void onNuevoAction(ActionEvent event) {
		
		MainController.grupo = new Grupo();
		grupoController = new GrupoController();
		alumnosController = new AlumnosController();
		tabContent();
		
	}
	
	private void tabContent() {
		grupoTab.setContent(grupoController.getView());
		alumnosTab.setContent(alumnosController.getView());
	}
	
	public BorderPane getView() {
		return view;
	}

}
