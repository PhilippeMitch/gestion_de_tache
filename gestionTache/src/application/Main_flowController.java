package application;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import controller.MembreController;
import controller.TacheController;
import domaine.Membre;
import domaine.Tache;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Main_flowController implements Initializable {

	/*Table liste membre*/
	@FXML
	private TableView<Membre> lstmembre;
	@FXML
	private TableColumn<Membre,String> idMembre;
	@FXML
	private TableColumn<Membre,String> nomMembre;
	@FXML
	private TableColumn<Membre,String> prenomMembre;
	@FXML
	private TableColumn<Membre,String> telMembre;
	@FXML
	private TableColumn<Membre,String> mailMembre;
	@FXML
	private TableColumn<Membre,String> adresseMembre;
	
	private ObservableList<Membre> data;
	MembreController m=new MembreController();
	
	/**************************************************/
	
	@FXML Label lblNombreMembre;
	@FXML AnchorPane rootPane;
	
	@FXML JFXButton btnNouvelleTacheMembre;
	
	/***************************************************/
	
	@FXML
	private TableView<Tache> lstTache;
	@FXML
	private TableColumn<Tache,String> idTache;
	@FXML
	private TableColumn<Tache,String> nomTache;
	@FXML
	private TableColumn<Tache,String> descriptionTache;
	@FXML
	private TableColumn<Tache,String> etatTache;
	
	private ObservableList<Tache> tache;
	TacheController t=new TacheController();

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
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
		
		lstmembre.setItems(null);
		lstmembre.setItems(data);
	}

	public void getUser(String text) {
		// TODO Auto-generated method stub
		lblNombreMembre.setText(text);
	}
	
	public void lstTache(int idMembre){
		idTache.setCellValueFactory(new PropertyValueFactory<>("idTache"));
		nomTache.setCellValueFactory(new PropertyValueFactory<>("nomTache"));
		descriptionTache.setCellValueFactory(new PropertyValueFactory<>("descriptionTache"));
		etatTache.setCellValueFactory(new PropertyValueFactory<>("etatTache"));
		
		try {
			tache=t.getTacheMembre(idMembre);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.print("Erreur "+e);
		}
		
		lstTache.setItems(null);
		lstTache.setItems(tache);
	}

	@FXML
	public void btnAjouterNouvelleTacheMembre(ActionEvent event) throws IOException{
		if(lstmembre.getSelectionModel().getSelectedItem() != null){
			//int idM=lstmembre.getSelectionModel().getSelectedItem().getIdMembre();
			 AnchorPane root = FXMLLoader.load(getClass().getResource("AddTacheMembre.fxml"));
			 	Stage stage=new Stage();
	            stage.setTitle("PIN-Generator");
	            stage.setScene(new Scene(root, 600, 400));
	            stage.show();
		}
	}
	
	@FXML
	public void lstListMembreClick(MouseEvent event){
		if(lstmembre.getSelectionModel().getSelectedItem() != null){
		
			int idM=lstmembre.getSelectionModel().getSelectedItem().getIdMembre();
			lstTache(idM);
			
			System.out.print("Click");
		}
	}
	
	
}
