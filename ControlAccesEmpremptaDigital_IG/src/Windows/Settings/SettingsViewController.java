package Windows.Settings;

import Controllers.PagesController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

public class SettingsViewController {

    PagesController pagesController = new PagesController();
    String previousWindow;

    public void initData(String sourceWindow) {

        this.previousWindow = sourceWindow;
    }

    public void OnBackButtonClicked(MouseEvent mouseEvent) {
        pagesController.goToScreen(mouseEvent, previousWindow);
    }

    public void OnLanguageButtonClicked(MouseEvent mouseEvent){ pagesController.goToConfigureLanguageScreenConstructing(mouseEvent, pagesController.page_ConfigureLanguage);}

    public void OnNotificationsButtonClicked(MouseEvent mouseEvent){ pagesController.goToConfigureNotificationsScreenConstructing(mouseEvent, pagesController.page_ConfigureNotifications);}
}
