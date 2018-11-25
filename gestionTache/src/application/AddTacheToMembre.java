package application;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;

import controller.TacheController;
import domaine.Membre;
import domaine.Operation;
import domaine.Tache;
import domaine.Tache_Membre;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;

public class AddTacheToMembre implements Initializable{
	
	@FXML
	private TableView<Tache> lstTache;
	@FXML
	private TableColumn<Membre,String> idTache;
	@FXML
	private TableColumn<Membre,String> nomTache;
	@FXML
	private TableColumn<Membre,String> descriptionTache;
	@FXML
	private TableColumn<Membre,String> dateDebutTache;
	@FXML
	private TableColumn<Membre,String> dateFinTache;
	@FXML
	private TableColumn<Membre,String> etatTache;
	
	@FXML JFXTextField txtIdTache;
	@FXML JFXTextField txtIdMembre;
	@FXML JFXTextField txtNomTache;
	@FXML JFXTextArea txtDescriptionTache;
	@FXML JFXDatePicker txtDateDebutTache;
	@FXML JFXDatePicker txtDateFinTache;
	@FXML JFXComboBox<String> txtEtatTache;
	
	@FXML JFXButton btnEnregistrerTache;

	@FXML private JFXComboBox<String> cmbNomTache;
	private ObservableList<String> lstNomTache;
	TacheController t=new TacheController();
	
	private ObservableList<Tache> data;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		btnEnregistrerTache.setDisable(true);
		//txtDateDebutTache.setDisable(true);
		//txtIdMembre.setDisable(true);
		//txtDescriptionTache.setDisable(true);
		//txtIdTache.setDisable(true);txtNomTache.setDisable(true);
		//txtDateFinTache.setDisable(true);txtEtatTache.setDisable(true);
		listNomTache();
		txtEtatTache.getItems().addAll("Disponible","Nom disponible");
		cmbNomTache.getSelectionModel().selectedItemProperty().addListener((v,oldValue,newValue)->selectedNomTache(newValue));
		
	}

	public void listNomTache(){

		try {
			lstNomTache=TacheController.getListNomTache();
		} catch (Exception e) {
			// TODO: handle exception
			ExceptionEnregistrer();
			System.out.print(""+e);
		}
		cmbNomTache.setItems(null);
		cmbNomTache.setItems(lstNomTache);
	}
	
	public void ExceptionEnregistrer(){
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Exception Dialog");
		alert.setHeaderText("Look, an Exception Dialog");
		alert.setContentText("Une erreur d'exception s'est produite!");

		Exception ex = new FileNotFoundException("Une erreur d'exception s'est produite");

		// Create expandable Exception.
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		ex.printStackTrace(pw);
		String exceptionText = sw.toString();

		Label label = new Label("L'erreur d'exception etait:");

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

	public void selectedNomTache(String value){
		idTache.setCellValueFactory(new PropertyValueFactory<>("idTache"));
		nomTache.setCellValueFactory(new PropertyValueFactory<>("nomTache"));
		descriptionTache.setCellValueFactory(new PropertyValueFactory<>("descriptionTache"));
		dateDebutTache.setCellValueFactory(new PropertyValueFactory<>("dateDebutTache"));
		dateFinTache.setCellValueFactory(new PropertyValueFactory<>("dateFinTache"));
		etatTache.setCellValueFactory(new PropertyValueFactory<>("etatTache"));
		
		try {
			data=t.getTacheFromNomTache(value);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.print("Erreur "+e);
		}
		
		lstTache.setItems(null);
		lstTache.setItems(data);
		
	}

	@FXML
	public void addTacheToMembre(ActionEvent event){
		Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText(null);
        alert.setContentText("Voulez vous Enregistrer ?");
        ButtonType buttonEnregistrer = new ButtonType("Enregistrer");
        ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);
       
        alert.getButtonTypes().setAll(buttonEnregistrer, buttonTypeCancel);
        Optional<ButtonType> result = alert.showAndWait();
        
        if (result.get() == buttonEnregistrer){
        	try {
				EnregistrerTache();
			} catch (Exception e) {
				// TODO: handle exception
				ExceptionEnregistrer();
			}
           
        } else{
        	System.out.print("Button Cancel presser");
        }
	}
	
	public void EnregistrerTache(){
		
		int result=0;
		Tache_Membre t=null;
		Operation o=null;
		TacheController mc = new TacheController();
		Date dj=new Date();
		SimpleDateFormat df=new SimpleDateFormat("MMMM/dd/Y hh:mm a");
		
		try {
			
			t=new Tache_Membre(Integer.parseInt(txtIdMembre.getText()), Integer.parseInt(txtIdTache.getText()), df.format(dj), "actif");
			o=new Operation("Enregistrement Nouvelle tache", df.format(dj).toString(), 34764893);
			result= mc.AddTacheToMembre(t, o);
			if(result>0){
				System.out.print("Enregistrement effectuer");
			}
			else{
				System.out.print("Echec !");
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
	}
	
	@FXML
	public void tbListTachePesed(MouseEvent event) throws ParseException{
		
		if (lstTache.getSelectionModel().getSelectedItem() != null){
			//btnModifierTache.setDisable(false);
			btnEnregistrerTache.setDisable(false);
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			
			int id=lstTache.getSelectionModel().getSelectedItem().getIdTache();
			txtIdTache.setText(String.valueOf(id));
			txtNomTache.setText(lstTache.getSelectionModel().getSelectedItem().getNomTache());
			txtDescriptionTache.setText(lstTache.getSelectionModel().getSelectedItem().getDescriptionTache());
			
			LocalDate dateDebut = LocalDate.parse(lstTache.getSelectionModel().getSelectedItem().getDateDebutTache(),formatter);
			txtDateDebutTache.setValue(dateDebut);
			
			LocalDate dateFin = LocalDate.parse(lstTache.getSelectionModel().getSelectedItem().getDateDebutTache(),formatter);
			txtDateFinTache.setValue(dateFin);
			
			txtEtatTache.setValue(lstTache.getSelectionModel().getSelectedItem().getEtatTache());
		}
		else{
			
		}
	}

	public void getUser(String text) {
		// TODO Auto-generated method stub
		txtIdMembre.setText(text);
	}
}
