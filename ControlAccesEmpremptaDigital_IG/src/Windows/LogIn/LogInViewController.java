package Windows.LogIn;


import Controllers.PagesController;
import Controllers.UsersController;
import Singleton.Singleton;
import Windows.MainMenu.MainMenuViewController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class LogInViewController {
    @FXML private TextField fieldUser;
    @FXML private PasswordField fieldPass;
    @FXML private Button buttonEnter;

    PagesController pagesController = Singleton.GetPagesController();
    UsersController usersController = Singleton.GetUsersController();

    public void OnLogInButtonClicked(MouseEvent mouseEvent) {
        //Treure es perque es logegi rapid
        //fieldUser.setText("admin");
        //fieldPass.setText("1234");
        if (areValidCredentials(fieldUser.getText().toString(), fieldPass.getText())) {
            usersController.setActiveUserName(fieldUser.getText());
            changeScene(mouseEvent);
        }
        else{
            fieldUser.clear();
            fieldPass.clear();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error");
            alert.setContentText("Usuari o contrasenya incorrectes!");

            alert.showAndWait();
        }
    }

    public void OnAccesButtonClicked(MouseEvent mouseEvent)
    {
        pagesController.goToScreen(mouseEvent, pagesController.page_AccessValidation);
    }

    private boolean areValidCredentials(String username, String password) {
        try {
            return (usersController.isValidUser(username, password));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    private void changeScene(MouseEvent mouseEvent) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(pagesController.page_MainMenu));
        Parent p = null;
        try {
            p = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene s = new Scene(p);
        MainMenuViewController mainMenuController = loader.getController();
        mainMenuController.initData(fieldUser.getText());

        Stage window = (Stage)((Node)mouseEvent.getSource()).getScene().getWindow();
        window.setScene(s);
        window.show();

    }

}
