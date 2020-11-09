package Windows.Base;

import Controllers.FilesController;
import Controllers.PagesController;
import Controllers.UsersController;
import Singleton.Singleton;
import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.util.Duration;

public abstract class BaseController {
    protected PagesController pagesController = Singleton.GetPagesController();
    protected FilesController filesController = Singleton.GetFilesController();
    protected UsersController usersController = Singleton.GetUsersController();

    @FXML protected Label username;

    protected void InitializeUserLabel()
    {
        username.setText(usersController.getActiveUserName());
    }

    public void OnLogOutButtonClicked(MouseEvent mouseEvent) {
        Alert alert = CreateLogoutAlert();
        var result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.YES)
            pagesController.goToScreen(mouseEvent, pagesController.page_LogIn);
    }

    public void OnSettingsButtonClicked(MouseEvent mouseEvent) {
        pagesController.goToSettingsScreenFrom(mouseEvent, pagesController.page_Settings, pagesController.page_MainMenu);
    }

    public void OnBackButtonClicked(MouseEvent mouseEvent) {
        pagesController.goToScreen(mouseEvent, pagesController.page_MainMenu);
    }

    public void OnSaveButtonClicked(MouseEvent mouseEvent)
    {

    }

    protected void PopulateListView()
    {
    }

    protected void showAlertErrorMessage(String error) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(error);

        alert.showAndWait();
    }

    protected void showInformationMessage(String message)
    {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText(message);

        alert.showAndWait();
    }

    protected Alert CreateLogoutAlert()
    {
        Alert alert = new Alert(Alert.AlertType.INFORMATION,"Vols sortir de la sessió?", ButtonType.YES, ButtonType.NO);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);

        return alert;
    }
}
