package dad.gesaula.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

public class MainController implements Initializable {

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
		
	}
	
	@FXML
	void onGuardarAction(ActionEvent event) {
		
	}
	
	@FXML
	void onNuevoAction(ActionEvent event) {
		
	}
	
	public BorderPane getView() {
		return view;
	}

}
