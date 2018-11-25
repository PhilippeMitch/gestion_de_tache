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
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
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

public class ListTacheController implements Initializable {

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
	@FXML JFXTextField txtNomTache;
	@FXML JFXTextArea txtDescriptionTache;
	@FXML JFXDatePicker txtDateDebutTache;
	@FXML JFXDatePicker txtDateFinTache;
	@FXML JFXComboBox<String> txtEtatTache;
	
	@FXML JFXButton btnModifierTache;
	@FXML JFXButton btnAddMembreToTache;
	@FXML JFXTextField txtSearchTache;
	private ObservableList<Tache> data;
	TacheController t=new TacheController();
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		txtEtatTache.getItems().addAll("Disponible","Nom disponible");
		//btnModifierTache.setDisable(true);
		//btnAddMembreToTache.setDisable(true);
		txtIdTache.setDisable(true);
		loadData(); 
		
		FilteredList<Tache> filteredData = new FilteredList<>(data, p -> true);
		
		txtSearchTache.textProperty().addListener((o,oldValue,newValue)->{
			filteredData.setPredicate(lstTache ->{
				if(newValue==null || newValue.isEmpty()){
					return true;
				}
				String lowerCaseFilter = newValue.toLowerCase();
				
				 if (lstTache.getNomTache().toLowerCase().contains(lowerCaseFilter)) {
	                    return true; // Filter matches first name.
	                } 
	                return false; // Does not match.
			});
		});
		
		SortedList<Tache> sortedData = new SortedList<>(filteredData);
		sortedData.comparatorProperty().bind(lstTache.comparatorProperty());
		lstTache.setItems(sortedData);
		
		
	}
		
	public void loadData(){
		
		idTache.setCellValueFactory(new PropertyValueFactory<>("idTache"));
		nomTache.setCellValueFactory(new PropertyValueFactory<>("nomTache"));
		descriptionTache.setCellValueFactory(new PropertyValueFactory<>("descriptionTache"));
		dateDebutTache.setCellValueFactory(new PropertyValueFactory<>("dateDebutTache"));
		dateFinTache.setCellValueFactory(new PropertyValueFactory<>("dateFinTache"));
		etatTache.setCellValueFactory(new PropertyValueFactory<>("etatTache"));
		
		try {
			data=t.getTache();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.print("Erreur "+e);
		}
		
		lstTache.setItems(null);
		lstTache.setItems(data);
	
	}
	
	public void clearForm(){
		txtNomTache.setText("");
		txtDescriptionTache.setText("");
		txtDateDebutTache.setValue(null);
		txtDateFinTache.setValue(null);
	}
	
	
	@FXML
	private void btnCreateTache(ActionEvent event){
		
		Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText(null);
        alert.setContentText("Voulez vous enregistrer ou modifier une tache ?");
        ButtonType buttonEnregistrer = new ButtonType("Enregistrer");
        ButtonType buttonModifier = new ButtonType("Modifier");
        ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);
       
        alert.getButtonTypes().setAll(buttonEnregistrer, buttonModifier, buttonTypeCancel);
        Optional<ButtonType> result = alert.showAndWait();
        
        if (result.get() == buttonEnregistrer){
        	try {
				creerTache();
			} catch (Exception e) {
				// TODO: handle exception
				ExceptionEnregistrer();
			}
           
        }  else if (result.get() == buttonModifier) {
        	
        	try {
        		modifierTache(Integer.parseInt(txtIdTache.getText()));
			} catch (Exception e) {
				// TODO: handle exception
				ExceptionEnregistrer();
			}
        	
        }else{
        	System.out.print("Button Cancel presser");
        }
	}
	
	public void modifierTache(int idTache){
		
	}
	
	public void creerTache(){
		int result=0;
		Tache t=null;
		Operation o=null;
		TacheController mc = new TacheController();
		Date dj=new Date();
		SimpleDateFormat df=new SimpleDateFormat("MMMM/dd/Y hh:mm a");
		LocalDate dateDb=txtDateDebutTache.getValue();
		LocalDate dateFn=txtDateFinTache.getValue();
		
		try {
			
			t=new Tache( txtNomTache.getText(), txtDescriptionTache.getText(), dateDb.toString(),dateFn.toString(), txtEtatTache.getValue());
			o=new Operation("Enregistrement Nouvelle tache", df.format(dj).toString(), 34764893);
			result= mc.EnregistrerTache(t, o);
			if(result>0){
				System.out.print("Enregistrement effectuer");
				clearForm();
				loadData();
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

}