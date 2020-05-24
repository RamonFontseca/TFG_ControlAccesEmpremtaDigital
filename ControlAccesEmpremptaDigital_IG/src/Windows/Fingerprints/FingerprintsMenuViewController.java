package Windows.Fingerprints;

import Controllers.PagesController;
import javafx.scene.input.MouseEvent;
import sample.Controller;

public class FingerprintsMenuViewController {
    PagesController pagesController = new PagesController();

    public void OnSettingsButtonClicked(MouseEvent mouseEvent) {
        pagesController.goToScreen(mouseEvent, pagesController.page_Settings);
    }

    public void OnBackButtonClicked(MouseEvent mouseEvent) {
        pagesController.goToScreen(mouseEvent, pagesController.page_MainMenu);
    }
}
