package application;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

import controller.MembreController;
import domaine.Membre;
import domaine.Operation;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;

public class ListMembreController implements Initializable {
	
	@FXML
	private TableView<Membre> lstMembre;
	@FXML
	private TableColumn<Membre,String> idMembre;
	@FXML
	private TableColumn<Membre,String> nomMembre;
	@FXML
	private TableColumn<Membre,String> prenomMembre;
	@FXML
	private TableColumn<Membre,String> telMembre;
	@FXML
	private TableColumn<Membre,String> adresseMembre;
	@FXML
	private TableColumn<Membre,String> mailMembre;
	
	@FXML JFXTextField txtIdMembre;
	@FXML JFXTextField txtNom;
	@FXML JFXTextField txtPrenom;
	@FXML JFXTextField txtadresseMembre;
	@FXML JFXTextField txtPhone;
	@FXML JFXTextField txtMail;
	@FXML JFXTextField txtSearcheMembre;
	
	@FXML JFXButton btnAddTacheMembre;
	@FXML JFXButton btnClearMembre;
	
	private ObservableList<Membre> data;
	MembreController m=new MembreController();
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		btnAddTacheMembre.setDisable(true);
		btnClearMembre.setDisable(true);
		txtIdMembre.setDisable(true);
		loadData(); 
		FilteredList<Membre> filteredData = new FilteredList<>(data, p -> true);
		
		txtSearcheMembre.textProperty().addListener((o,oldValue,newValue)->{
			filteredData.setPredicate(lstMembre ->{
				if(newValue==null || newValue.isEmpty()){
					return true;
				}
				String lowerCaseFilter = newValue.toLowerCase();
				
				 if (String.valueOf(lstMembre.getIdMembre()).toLowerCase().contains(lowerCaseFilter)) {
	                    return true; // Filter matches first name.
	                    
	                } 
	                return false; // Does not match.
			});
		});
		
		SortedList<Membre> sortedData = new SortedList<>(filteredData);
		sortedData.comparatorProperty().bind(lstMembre.comparatorProperty());
		lstMembre.setItems(sortedData);
	}
	
	public void loadData(){
		
		idMembre.setCellValueFactory(new PropertyValueFactory<>("idMembre"));
		nomMembre.setCellValueFactory(new PropertyValueFactory<>("nomMembre"));
		prenomMembre.setCellValueFactory(new PropertyValueFactory<>("prenomMembre"));
		telMembre.setCellValueFactory(new PropertyValueFactory<>("telMembre"));
		adresseMembre.setCellValueFactory(new PropertyValueFactory<>("adresseMembre"));
		mailMembre.setCellValueFactory(new PropertyValueFactory<>("mailMembre"));
		
		try {
			data=m.getMembre();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.print("Erreur "+e);
		}
		
		lstMembre.setItems(null);
		lstMembre.setItems(data);
	
	}
	
	public void clearForm(){
		txtIdMembre.setText("");
		txtNom.setText("");
		txtPrenom.setText("");
		txtadresseMembre.setText("");
		txtPhone.setText("");
		txtMail.setText("");
	}
	
	@FXML
	private void addMembre(ActionEvent event){
		
		Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText(null);
        alert.setContentText("Voulez vous Enregistrer ou modifier un membre ?");
        ButtonType buttonEnregistrer = new ButtonType("Enregistrer");
        ButtonType buttonModifier = new ButtonType("Modifier");
        ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);
       
        alert.getButtonTypes().setAll(buttonEnregistrer, buttonModifier, buttonTypeCancel);
        Optional<ButtonType> result = alert.showAndWait();
        
        if (result.get() == buttonEnregistrer){
        	try {
				EnregistretMembre();
			} catch (Exception e) {
				// TODO: handle exception
				ExceptionEnregistrer();
			}
           
        }  else if (result.get() == buttonModifier) {
        	
        	try {
        		ModifierMembre(Integer.parseInt(txtIdMembre.getText()));
			} catch (Exception e) {
				// TODO: handle exception
				ExceptionEnregistrer();
			}
        	
        }else{
        	System.out.print("Button Cancel presser");
        }
	}

	@FXML
	public void tbListMembrePresed(MouseEvent event){
		if (lstMembre.getSelectionModel().getSelectedItem() != null){
			btnAddTacheMembre.setDisable(false);;
			btnClearMembre.setDisable(false);
			
			int id=lstMembre.getSelectionModel().getSelectedItem().getIdMembre();
			txtIdMembre.setText(String.valueOf(id));
			
			txtNom.setText(lstMembre.getSelectionModel().getSelectedItem().getNomMembre());
			txtPrenom.setText(lstMembre.getSelectionModel().getSelectedItem().getPrenomMembre());
			txtPhone.setText(lstMembre.getSelectionModel().getSelectedItem().getTelMembre());
			txtMail.setText(lstMembre.getSelectionModel().getSelectedItem().getMailMembre());
			txtadresseMembre.setText(lstMembre.getSelectionModel().getSelectedItem().getAdresseMembre());
		}else
		{
			btnAddTacheMembre.setDisable(true);;
			btnClearMembre.setDisable(true);
		}
		
	}
	
	@FXML
	public void addTacheMembre(ActionEvent event) throws IOException{
		
		//Stage primaryStage = (Stage)btnAddTacheMembre.getScene().getWindow();
		Stage secondStage=new Stage();
		FXMLLoader loader=new FXMLLoader();
		
		Parent root=loader.load(getClass().getResource("AddTacheToMembre.fxml").openStream());
		AddTacheToMembre dashboard=(AddTacheToMembre)loader.getController();
		Scene scene= new Scene(root);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		secondStage.setScene(scene);
		secondStage.show();
		dashboard.getUser(txtIdMembre.getText());
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
	
	public void EnregistretMembre(){
		int result=0;
		int verif=0;
		Membre m=null;
		Operation o=null;
		MembreController mc = new MembreController();
		Date dj=new Date();
		SimpleDateFormat df=new SimpleDateFormat("MMMM/dd/Y hh:mm a");
		
		try {
			verif=mc.VerifierEnfoMembre(txtNom.getText(),txtPrenom.getText(), txtPhone.getText(), txtadresseMembre.getText(), txtMail.getText());
			if(verif>0)
			{
				System.out.print("Membre exist");
			}else{
				m=new Membre( txtNom.getText(), txtPrenom.getText(), txtPhone.getText(),txtMail.getText(), txtadresseMembre.getText(),"actif");
				o=new Operation("Enregistrement Nouveau Membre", df.format(dj).toString(), 34764893);
				result= mc.EnregistrerJeune(m, o);
				if(result>0){
					System.out.print("Enregistrement effectuer");
					clearForm();
					loadData();
				}
				else{
					System.out.print("Echec !");
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
	}

	@FXML
	public void ViderForm(){
		clearForm();
	}
	
	public void ModifierMembre(int idMembre){
	
		int result=0;
		Membre m=null;
		Operation o=null;
		MembreController mc = new MembreController();
		Date dj=new Date();
		SimpleDateFormat df=new SimpleDateFormat("MMMM/dd/Y hh:mm a");
		
		try {
			
			m=new Membre( txtNom.getText(), txtPrenom.getText(), txtPhone.getText(),txtPhone.getText(), txtMail.getText(),"actif");
			o=new Operation("Modifier les information d'un membre", df.format(dj).toString(), 34764893);
			result= mc.ModifierMembre(m, o, idMembre);
			if(result>0){
				System.out.print("Modification effectuer !");
				clearForm();
				loadData();
			}
			else{
				System.out.print("Echec !");
			}
		} catch (Exception e) {
			// TODO: handle exception
			ExceptionEnregistrer();
		}
	}
	
	public void tbListMembre(MouseEvent event){
		
		if(lstMembre.getSelectionModel().getSelectedItem() != null){
			btnAddTacheMembre.setDisable(false);
			btnClearMembre.setDisable(false);
			
			int id=lstMembre.getSelectionModel().getSelectedItem().getIdMembre();
			txtIdMembre.setText(String.valueOf(id));
			txtNom.setText(lstMembre.getSelectionModel().getSelectedItem().getNomMembre());
			txtPrenom.setText(lstMembre.getSelectionModel().getSelectedItem().getPrenomMembre());
			txtadresseMembre.setText(lstMembre.getSelectionModel().getSelectedItem().getAdresseMembre());
			txtPhone.setText(lstMembre.getSelectionModel().getSelectedItem().getTelMembre());
			txtMail.setText(lstMembre.getSelectionModel().getSelectedItem().getMailMembre());
			
		}
		
	}
}
