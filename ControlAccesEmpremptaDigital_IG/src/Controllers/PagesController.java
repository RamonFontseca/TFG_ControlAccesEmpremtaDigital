package Controllers;

import Singleton.Singleton;
import Windows.Codes.AddTempCodeViewController;
import Windows.Codes.DeleteCodesViewController;
import Windows.Codes.EnableDisableCodesViewController;
import Windows.Codes.EnableDisableCodesViewController2;
import Windows.Fingerprints.AddPermFingerprintViewController;
import Windows.Fingerprints.AddTempFingerprintViewController;
import Windows.Fingerprints.DeleteFingerprintsViewController;
import Windows.Fingerprints.EnableDisableFingerprintsViewController;
import Windows.PhoneNumbers.DeletePhoneNumbersViewController;
import Windows.PhoneNumbers.EnableDisablePhoneNumbersViewController;
import Windows.Settings.ConfigureLanguageViewController;
import Windows.Settings.ConfigureNotificationsViewController;
import Windows.Settings.SettingsViewController;
import Windows.Stadistics.CodeStadisticsViewController;
import Windows.Stadistics.UserUsagesStadisticsViewController;
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
    public String page_AddPermFingerprint = "/Windows/Fingerprints/AddPermFingerprintView.fxml";
    public String page_AddTempFingerprint = "/Windows/Fingerprints/AddTempFingerprintView.fxml";
    public String page_EnableDisableFingerprints = "/Windows/Fingerprints/EnableDisableFingerprintsView.fxml";
    public String page_DeleteFingerprint = "/Windows/Fingerprints/DeleteFingerprintsView.fxml";
    public String page_AddPhoneNumber = "/Windows/PhoneNumbers/AddPhoneNumberView.fxml";
    public String page_PhoneNumbersMenu = "/Windows/PhoneNumbers/PhoneNumbersMenuView.fxml";
    public String page_EnableDisablePhoneNumbers = "/Windows/PhoneNumbers/EnableDisablePhoneNumbersView.fxml";
    public String page_DeletePhoneNumbers =  "/Windows/PhoneNumbers/DeletePhoneNumbersView.fxml";
    public String page_ConfigureLanguage = "/Windows/Settings/ConfigureLanguageView.fxml";
    public String page_ConfigureNotifications = "/Windows/Settings/ConfigureNotificationsView.fxml";
    public String page_StadisticsMenu = "/Windows/Stadistics/StadisticsMenuView.fxml";
    public String page_StadisicsCodesUsages = "/Windows/Stadistics/CodeStadisticsView.fxml";
    public String page_StadisicsUagesPerUSer = "/Windows/Stadistics/UserUsagesStadisticsView.fxml";

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
        DeleteCodesViewController deleteCodesViewController = loader.getController();
        deleteCodesViewController.InitData();

        Scene scene = new Scene(root);
        Stage window = (Stage)((Node)mouseEvent.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }

    public void goToFingerScreenWithController(MouseEvent mouseEvent, String destinationPage, FingerprintsController fingerprintsController)
    {
        FXMLLoader loader =  new FXMLLoader(getClass().getResource(destinationPage));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        AddPermFingerprintViewController addPermFingerprintViewController = loader.getController();
        addPermFingerprintViewController.InitData(fingerprintsController);

        Scene scene = new Scene(root);
        Stage window = (Stage)((Node)mouseEvent.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }

    public void goToTempFingerScreenWithController(MouseEvent mouseEvent, String destinationPage, FingerprintsController fingerprintsController)
    {
        FXMLLoader loader =  new FXMLLoader(getClass().getResource(destinationPage));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        AddTempFingerprintViewController addTempFingerprintViewController = loader.getController();
        addTempFingerprintViewController.InitData(fingerprintsController);

        Scene scene = new Scene(root);
        Stage window = (Stage)((Node)mouseEvent.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }

    public void goToEnableDisableFingerprintsScreenConstructing(MouseEvent mouseEvent, String destinationPage)
    {
        FXMLLoader loader =  new FXMLLoader(getClass().getResource(destinationPage));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        EnableDisableFingerprintsViewController enableDisableFingerprintsViewController = loader.getController();
        enableDisableFingerprintsViewController.InitData();

        Scene scene = new Scene(root);
        Stage window = (Stage)((Node)mouseEvent.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }

    public void goToDeleteFingerprintsScreenConstructing(MouseEvent mouseEvent, String destinationPage)
    {
        FXMLLoader loader =  new FXMLLoader(getClass().getResource(destinationPage));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        DeleteFingerprintsViewController deleteFingerprintsViewController = loader.getController();
        deleteFingerprintsViewController.InitData();

        Scene scene = new Scene(root);
        Stage window = (Stage)((Node)mouseEvent.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }


    public void goToEnableDisablePhoneNumbersScreenConstructing(MouseEvent mouseEvent, String destinationPage) {
        FXMLLoader loader =  new FXMLLoader(getClass().getResource(destinationPage));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        EnableDisablePhoneNumbersViewController enableDisablePhoneNumbersViewController = loader.getController();
        enableDisablePhoneNumbersViewController.InitData();

        Scene scene = new Scene(root);
        Stage window = (Stage)((Node)mouseEvent.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }

    public void goToDeletePhoneNumbersScreenConstructing(MouseEvent mouseEvent, String page_deleteCode) {
        FXMLLoader loader =  new FXMLLoader(getClass().getResource(page_deleteCode));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        DeletePhoneNumbersViewController deletePhoneNumbersViewController = loader.getController();
        deletePhoneNumbersViewController.InitData();

        Scene scene = new Scene(root);
        Stage window = (Stage)((Node)mouseEvent.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }

    public void goToConfigureLanguageScreenConstructing(MouseEvent mouseEvent, String destinationPage) {
        FXMLLoader loader =  new FXMLLoader(getClass().getResource(destinationPage));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        ConfigureLanguageViewController configureLanguageViewController = loader.getController();
        configureLanguageViewController.InitData();

        Scene scene = new Scene(root);
        Stage window = (Stage)((Node)mouseEvent.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }

    public void goToConfigureNotificationsScreenConstructing(MouseEvent mouseEvent, String destinationPage) {
        FXMLLoader loader =  new FXMLLoader(getClass().getResource(destinationPage));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        ConfigureNotificationsViewController configureNotificationsViewController = loader.getController();
        configureNotificationsViewController.InitData();

        Scene scene = new Scene(root);
        Stage window = (Stage)((Node)mouseEvent.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }

    public void goToCodeStadisticsScreenConstructing(MouseEvent mouseEvent, String destinationPage)
    {
        FXMLLoader loader =  new FXMLLoader(getClass().getResource(destinationPage));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        CodeStadisticsViewController codeStadisticsViewController = loader.getController();
        codeStadisticsViewController.InitData();

        Scene scene = new Scene(root);
        Stage window = (Stage)((Node)mouseEvent.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }

    public void goToUserStadisticsScreenConstructing(MouseEvent mouseEvent, String destinationPage)
    {
        FXMLLoader loader =  new FXMLLoader(getClass().getResource(destinationPage));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        UserUsagesStadisticsViewController userUsagesStadisticsViewController = loader.getController();
        userUsagesStadisticsViewController.InitData();

        Scene scene = new Scene(root);
        Stage window = (Stage)((Node)mouseEvent.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }

    public void goToScreenBona(MouseEvent mouseEvent, String destinationPage, Singleton singleton)
    {
        FXMLLoader loader =  GetLoader(destinationPage);
        Parent root = GetParent(loader);

        /* controller = loader.getController();
        controller.SetSingleton(singleton);
        addPermFingerprintViewController.InitData(fingerprintsController);*/

        ChangeScene(mouseEvent, root);
    }

    private FXMLLoader GetLoader(String destinationPage)
    {
        return new FXMLLoader(getClass().getResource(destinationPage));
    }

    private Parent GetParent(FXMLLoader loader)
    {
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return root;
    }

    private void ChangeScene(MouseEvent mouseEvent, Parent root)
    {
        Scene scene = new Scene(root);
        Stage window = (Stage)((Node)mouseEvent.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }
}
