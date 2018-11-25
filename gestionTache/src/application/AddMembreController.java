package application;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import controller.MembreController;
import domaine.Membre;
import domaine.Operation;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class AddMembreController implements Initializable {
	
	/*camera variable*/
	@FXML JFXButton btnCloseAddGent;
	
	@FXML JFXTextField txtIdMembre;
	@FXML JFXTextField txtNom;
	@FXML JFXTextField txtPrenom;
	@FXML JFXTextField adresseMembre;
	@FXML JFXTextField txtPhone;
	@FXML JFXTextField txtMail;
	

	public static Stage primaryStage;
	
	public static Stage getPrimaryStage() {
		return primaryStage;
	}

	private double xOffset = 0;
	private double yOffset = 0;
	
	public void clearForm(){
		txtIdMembre.setText("");
		txtNom.setText("");
		txtPrenom.setText("");
		adresseMembre.setText("");
		txtPhone.setText("");
		txtMail.setText("");
	}
	
	public ListMembreController lstm;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
	}
	
	@FXML
	public void btnEditForm(ActionEvent event) throws IOException{
		AnchorPane parent=FXMLLoader.load(getClass().getResource("AddMembre.fxml"));
		
		parent.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                xOffset = event.getSceneX();
                yOffset = event.getSceneY();
            }
        });
        parent.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                primaryStage.setX(event.getScreenX() - xOffset);
                primaryStage.setY(event.getScreenY() - yOffset);
            }
        });
		
        parent.setOnDragDone((e)->{
        	primaryStage.setOpacity(10.0f);
        });
        
        parent.setOnMouseReleased((e)->{
        	primaryStage.setOpacity(1.0f);
        });
		Scene scene=new Scene(parent);
		//stage.initStyle(StageStyle.DECORATED);
		Stage stage=(Stage)((Node)event.getSource()).getScene().getWindow();
		stage.setScene(scene);
		stage.show();
		
	}
	
	

	@FXML
	private void addMembre(ActionEvent event){
		//JSONObject json=new JSONObject();
		int result=0;
		Membre m=null;
		Operation o=null;
		MembreController mc = new MembreController();
		Date dj=new Date();
		SimpleDateFormat df=new SimpleDateFormat("MMMM/dd/Y hh:mm a");
		
		
		
		try {
			
			m=new Membre( txtNom.getText(), txtPrenom.getText(), txtPhone.getText(),txtPhone.getText(), txtMail.getText(),"actif");
			o=new Operation("Enregistrement Nouveau Membre", df.format(dj).toString(), 34764893);
			
			
			
			result= mc.EnregistrerJeune(m, o);
			if(result>0){
				System.out.print("Enregistrement effectuer");
				clearForm();
			}
			else{
				System.out.print("Echec !");
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage()+"Insertion");
		}
	}
}
