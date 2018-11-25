package application;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;

import databaseConnection.ConnectionController;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class LoginController {
	
	@FXML
	private AnchorPane rootPane;
	@FXML
	private Pane pane_area;
	@FXML
	private JFXButton btnConnect;
	@FXML JFXTextField txtNomUser;
	@FXML JFXPasswordField txtPassUser;
	
	@FXML Label lblIsConnected;
	
	Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
	
	public ConnectionController connectioController=new ConnectionController();
	
	public void initialize(URL url, ResourceBundle rb) {
		if(connectioController.isDbConnected()){
			System.out.println("Connected");
		}else{
			System.out.println("Disconnected");
		}
	}
	
	public void Login(ActionEvent event){
		
		if(txtNomUser.getText()!=null && txtPassUser.getText()!=null){
		
			try {
				
				if(connectioController.isLogin(txtNomUser.getText(), txtPassUser.getText())){
				
				Stage primaryStage = (Stage)btnConnect.getScene().getWindow();
				
				Stage secondStage=new Stage();
				FXMLLoader loader=new FXMLLoader();
				
				Parent root=loader.load(getClass().getResource("Main.fxml").openStream());
				
				DashboardController dashboard=(DashboardController)loader.getController();
				dashboard.getUser(txtNomUser.getText());
				//secondjedeonStage.initStyle(StageStyle.UNDECORATED);
				Scene scene= new Scene(root);
				
				secondStage.initStyle(StageStyle.UNDECORATED);
				
		        //give the windows size
		        secondStage.setX(primaryScreenBounds.getMinX());
		        secondStage.setY(primaryScreenBounds.getMinY());
		        secondStage.setWidth(primaryScreenBounds.getWidth());
		        secondStage.setHeight(primaryScreenBounds.getHeight());
				
		        //
				scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
				secondStage.setScene(scene);
				
				secondStage.setOnCloseRequest(e -> primaryStage.show());
				
				secondStage.show();
				primaryStage.hide();
				
				
				}else{
					
					Alert alert = new Alert(Alert.AlertType.ERROR, "Nom Utilisateur ou mot de passe incorrect");
			        alert.setTitle("Erreur");
			        alert.setHeaderText("");
			        alert.showAndWait();
					//System.out.println("User and mail not correct");
					
				}
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("User and mail not correct"+e);
			}
			
		}else{
			
			 Alert alert = new Alert(Alert.AlertType.WARNING,"Veuillez saisir votre nom d'utilisateur et votre mot de passe");
		     alert.setTitle("Avertissement");
		     alert.setHeaderText("");
		     alert.showAndWait();
		}
		
	}
	
	@FXML
	public void closeButton(MouseEvent event){
		//close stage on icon click
		Platform.exit();
	}

	

}
