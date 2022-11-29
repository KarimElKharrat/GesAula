package dad.gesaula.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import dad.gesaula.ui.model.Grupo;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class GrupoController implements Initializable {
	
	// model
	
	public ObjectProperty<Grupo> grupo = new SimpleObjectProperty<>();
	
	// view 

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
		
		// listeners
		
		grupo.addListener(this::onChangeListener);
		
		// bindings
		
		
	}
	
	private void onChangeListener(ObservableValue<? extends Grupo> o, Grupo ov, Grupo nv) {
		
		if(ov != null) {
			
			denominacionText.textProperty().unbindBidirectional(ov.denominacionProperty());
			inicioDate.valueProperty().unbindBidirectional(ov.iniCursoProperty());
			finDate.valueProperty().unbindBidirectional(ov.finCursoProperty());
			examenesSlider.valueProperty().unbindBidirectional(ov.getPesos().examenesProperty());
			practicasSlider.valueProperty().unbindBidirectional(ov.getPesos().practicasProperty());
			actitudSlider.valueProperty().unbindBidirectional(ov.getPesos().actitudProperty());
			
			examenesLabel.textProperty().unbind();
			practicasLabel.textProperty().unbind();
			actitudLabel.textProperty().unbind();
			
		}
		
		if(nv != null) {
			
			denominacionText.textProperty().bindBidirectional(nv.denominacionProperty());
			inicioDate.valueProperty().bindBidirectional(nv.iniCursoProperty());
			finDate.valueProperty().bindBidirectional(nv.finCursoProperty());
			examenesSlider.valueProperty().bindBidirectional(nv.getPesos().examenesProperty());
			practicasSlider.valueProperty().bindBidirectional(nv.getPesos().practicasProperty());
			actitudSlider.valueProperty().bindBidirectional(nv.getPesos().actitudProperty());
			
			examenesLabel.textProperty().bind(nv.getPesos().examenesProperty().asString("%.2f%%"));
			practicasLabel.textProperty().bind(nv.getPesos().practicasProperty().asString("%.2f%%"));
			actitudLabel.textProperty().bind(nv.getPesos().actitudProperty().asString("%.2f%%"));
			
		}
		
	}

	public GridPane getView() {
		return view;
	}

	public final ObjectProperty<Grupo> grupoProperty() {
		return this.grupo;
	}
	
	public final Grupo getGrupo() {
		return this.grupoProperty().get();
	}

	public final void setGrupo(final Grupo grupo) {
		this.grupoProperty().set(grupo);
	}

}
