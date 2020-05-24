package Windows.MainMenu;

import Controllers.PagesController;
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

public class MainMenuViewController {

    @FXML private Label username;

    PagesController pagesController = new PagesController();

    public void initData(String username) {
        this.username.setText(username);

    }

    public void OnLogOutButtonClicked(MouseEvent mouseEvent) {
        pagesController.goToScreen(mouseEvent, pagesController.page_LogIn);
    }

    /*private void goToScreen(MouseEvent mouseEvent, String pageUrl) {
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
     */


    public void OnManageCodesButtonClick(MouseEvent mouseEvent) {
        pagesController.goToScreen(mouseEvent, pagesController.page_CodesMenu);
    }

    public void OnManageNotificationsButtonClicked(MouseEvent mouseEvent) {
        pagesController.goToScreen(mouseEvent, pagesController.page_CodesMenu);
    }


    public void OnSettingsButtonClicked(MouseEvent mouseEvent) {
        pagesController.goToSettingsScreenFrom(mouseEvent, pagesController.page_Settings, pagesController.page_MainMenu);
    }

    public void OnManageFingerprintsButtonClicked(MouseEvent mouseEvent) {
        pagesController.goToScreen(mouseEvent, pagesController.page_FingerprintsMenu);
    }
}
