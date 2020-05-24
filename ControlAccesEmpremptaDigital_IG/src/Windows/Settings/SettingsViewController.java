package Windows.Settings;

import Controllers.PagesController;
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
}
