package Controllers;

import Windows.Codes.AddTempCodeViewController;
import Windows.Codes.DeleteCodesViewController;
import Windows.Codes.EnableDisableCodesViewController;
import Windows.Codes.EnableDisableCodesViewController2;
import Windows.Settings.SettingsViewController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class PagesController {
    public String page_LogIn = "/Windows/LogIn/LogInView.fxml";
    public String page_MainMenu = "/Windows/MainMenu/MainMenuView.fxml";
    public String page_CodesMenu = "/Windows/Codes/CodesMenuView.fxml";
    public String page_Settings = "/Windows/Settings/SettingsView.fxml";
    public String page_FingerprintsMenu = "/Windows/Fingerprints/FingerprintsMenuView.fxml";
    public String page_AddPermCode = "/Windows/Codes/AddPermCodeView.fxml";
    public String page_AddTempCode = "/Windows/Codes/AddTempCodeView.fxml";
    public String page_EnableDisableCodes = "/Windows/Codes/EnableDisableCodesView.fxml";
    public String page_DeleteCode = "/Windows/Codes/DeleteCodesView.fxml";

    String previousWindow;

    public PagesController(){
        previousWindow = null;
    }

    public PagesController(String previousWindow){
        this.previousWindow = previousWindow;
    }

    public void setPreviousWindow(String previousWindow){
        this.previousWindow = previousWindow;
    }

    public void goToSettingsScreenFrom(MouseEvent mouseEvent, String destinationPage, String sourcePage){
        FXMLLoader loader =  new FXMLLoader(getClass().getResource(destinationPage));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        SettingsViewController settingsController = loader.getController();
        settingsController.initData(sourcePage);

        Scene scene = new Scene(root);
        Stage window = (Stage)((Node)mouseEvent.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }

    public void goToScreen(MouseEvent mouseEvent, String pageUrl) {
        Parent p = null;
        try {
            p = FXMLLoader.load(getClass().getResource(pageUrl));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene s = new Scene(p);


        Stage window = (Stage)((Node)mouseEvent.getSource()).getScene().getWindow();
        window.setScene(s);
        window.show();
    }

    public void goToAddTempScreenConstructing(MouseEvent mouseEvent, String destinationPage){
        FXMLLoader loader =  new FXMLLoader(getClass().getResource(destinationPage));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        AddTempCodeViewController tempCodeViewController = loader.getController();
        tempCodeViewController.InitData();

        Scene scene = new Scene(root);
        Stage window = (Stage)((Node)mouseEvent.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }



    public void goToBackScreen(MouseEvent mouseEvent){
        Parent p = null;
        try {
            p = FXMLLoader.load(getClass().getResource(this.previousWindow));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene s = new Scene(p);

        Stage window = (Stage)((Node)mouseEvent.getSource()).getScene().getWindow();
        window.setScene(s);
        window.show();
    }

    public void goToEnableDisableCodeScreenConstructing(MouseEvent mouseEvent, String destinationPage) {
        FXMLLoader loader =  new FXMLLoader(getClass().getResource(destinationPage));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        EnableDisableCodesViewController enableDisableCodesViewController = loader.getController();
        enableDisableCodesViewController.InitData();

        Scene scene = new Scene(root);
        Stage window = (Stage)((Node)mouseEvent.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }

    public void goToDeleteCodeScreenConstructing(MouseEvent mouseEvent, String page_deleteCode) {
        FXMLLoader loader =  new FXMLLoader(getClass().getResource(page_deleteCode));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        DeleteCodesViewController enableDisableCodesViewController = loader.getController();
        enableDisableCodesViewController.InitData();

        Scene scene = new Scene(root);
        Stage window = (Stage)((Node)mouseEvent.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }
}
