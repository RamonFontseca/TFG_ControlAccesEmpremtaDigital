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
        pagesController.goToSettingsScreenFrom(mouseEvent, pagesController.page_Settings, pagesController.page_FingerprintsMenu);
    }

    public void OnBackButtonClicked(MouseEvent mouseEvent) {
        pagesController.goToScreen(mouseEvent, pagesController.page_MainMenu);
    }

    public void OnAddPermFingerprintClicked(MouseEvent mouseEvent)
    {
        pagesController.goToFingerScreenWithController(mouseEvent, pagesController.page_AddPermFingerprint, fingerprintsController);
    }

    public void OnAddTempFingerprintClicked(MouseEvent mouseEvent)
    {
        pagesController.goToTempFingerScreenWithController(mouseEvent, pagesController.page_AddTempFingerprint, fingerprintsController);
    }

    public void OnUpdateFingerprintClicked(MouseEvent mouseEvent) {
        pagesController.goToEnableDisableFingerprintsScreenConstructing(mouseEvent, pagesController.page_EnableDisableFingerprints);
    }

    public void OnDeleteFingerprintClicked(MouseEvent mouseEvent) {
        pagesController.goToDeleteFingerprintsScreenConstructing(mouseEvent, pagesController.page_DeleteFingerprint);
    }

}
