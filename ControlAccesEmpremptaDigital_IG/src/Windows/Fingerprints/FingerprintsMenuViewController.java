package Windows.Fingerprints;

import Controllers.FingerprintsController;
import Controllers.PagesController;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import sample.Controller;

public class FingerprintsMenuViewController {
    PagesController pagesController = new PagesController();
    FingerprintsController fingerprintsController = new FingerprintsController();

    //@FXML private javafx.scene.control.Button bttnAddCodePerm;


    public FingerprintsMenuViewController() {
        //bttnAddCodePerm.setDisable(true);
    }

    public void OnSettingsButtonClicked(MouseEvent mouseEvent) {
        pagesController.goToScreen(mouseEvent, pagesController.page_Settings);
    }

    public void OnBackButtonClicked(MouseEvent mouseEvent) {
        pagesController.goToScreen(mouseEvent, pagesController.page_MainMenu);
    }

    public void OnAddPermFingerprintClicked(MouseEvent mouseEvent)
    {
        pagesController.goToFingerScreenWithController(mouseEvent, pagesController.page_AddPermFingerprint, fingerprintsController);

    }

    public void OnUpdateFingerprintClicked(MouseEvent mouseEvent) {
    }

    public void OnDeleteFingerprintClicked(MouseEvent mouseEvent) {
    }

    public void OnAddTempFingerprintClicked(MouseEvent mouseEvent) {
    }
}
