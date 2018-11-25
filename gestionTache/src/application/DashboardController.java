package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


public class DashboardController implements Initializable {


	@FXML
	private Pane pane;
	AnchorPane homePane;
	
	public String idUser;
	
	@FXML public Label nomUser;

	@FXML Stage stage;;
	
	@FXML JFXButton btnListMembre;
	
	
	public void initialize(URL url, ResourceBundle rb) {
		// TODO Auto-generated method stub
		//activeFormMembre();
		
		try {
			this.createPage(homePane, "Main_flow.fxml");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.print(e);
		}
		
	}
	
	public void initialize(String user) {
		// TODO Auto-generated method stub
		try {
			this.createPage(homePane, "Main_flow.fxml");
			nomUser.setText(user);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void getUser(String user) {
		// TODO Auto-generated method stub
		nomUser.setText(user);
	}
	
	public void setNode(Node node){
		pane.getChildren().clear();
		pane.getChildren().add((Node)node);
		node.setLayoutX(0);
		node.setLayoutY(7);
	}
	
	public void createPage(AnchorPane home,String loc) throws IOException{
		home=FXMLLoader.load(getClass().getResource(loc));
		setNode(home);
	}
	
	@FXML
	void btnDashboardAction(ActionEvent event) throws IOException{
		this.createPage(homePane, "Main_flow.fxml");
		
	}
	
	@FXML
	void btnListMembre(ActionEvent event) throws IOException{
		this.createPage(homePane, "ListMembre.fxml");
	}
	
	@FXML
	void btnListTache(ActionEvent event) throws IOException{
		 this.createPage(homePane, "ListTache.fxml");
	 }

	 
	@FXML
	void btnDisconnectAction(ActionEvent event){
		 //Main.getPrimaryStage();
		 //System.out.print("J'ai clicker");
		 
		 try {
				((Node)event.getSource()).getScene().getWindow().hide();
				
				Stage primaryStage=new Stage();
				FXMLLoader loader=new FXMLLoader();
				primaryStage.initStyle(StageStyle.UNDECORATED);
				Parent root;
				root = loader.load(getClass().getResource("/application/Login.fxml").openStream());
				Scene scene= new Scene(root);
				//System.exit(1);
				scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
				primaryStage.setScene(scene);
				primaryStage.show();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 //System.exit(0);
	 }
	

}
