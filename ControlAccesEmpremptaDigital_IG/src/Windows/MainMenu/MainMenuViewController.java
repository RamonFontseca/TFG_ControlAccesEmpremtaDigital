package Windows.MainMenu;

import Controllers.CodesController;
import Controllers.PagesController;
import Encrypter.Encrypter;
import Model.Code;
import Singleton.Singleton;
import Windows.Base.BaseController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class MainMenuViewController extends BaseController {

    @FXML private Label username;

    public void initData(String username) {
        InitializeUserLabel();
    }

    public void OnManageCodesButtonClick(MouseEvent mouseEvent) {
        pagesController.goToScreen(mouseEvent, pagesController.page_CodesMenu);
    }

    public void OnManageNotificationsButtonClicked(MouseEvent mouseEvent) {
        pagesController.goToScreen(mouseEvent, pagesController.page_PhoneNumbersMenu);
    }

    public void OnManageStadisticsButtonClicked(MouseEvent mouseEvent)
    {
        pagesController.goToScreen(mouseEvent, pagesController.page_StadisticsMenu);
    }

    public void OnManageFingerprintsButtonClicked(MouseEvent mouseEvent) {
        pagesController.goToScreen(mouseEvent, pagesController.page_FingerprintsMenu);
    }
}
