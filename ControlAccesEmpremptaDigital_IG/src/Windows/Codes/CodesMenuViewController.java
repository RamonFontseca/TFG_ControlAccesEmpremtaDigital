package Windows.Codes;

import Controllers.PagesController;
import javafx.scene.input.MouseEvent;

public class CodesMenuViewController {

    PagesController pagesController = new PagesController();

    public void OnBackButtonClicked(MouseEvent mouseEvent) {
        pagesController.goToScreen(mouseEvent, pagesController.page_MainMenu);
    }

    public void OnAddPermClicked(MouseEvent mouseEvent) {
        pagesController.goToScreen(mouseEvent, pagesController.page_AddPermCode);
    }

    public void OnAddTempClicked(MouseEvent mouseEvent) {
        pagesController.goToAddTempScreenConstructing(mouseEvent, pagesController.page_AddTempCode);
    }

    public void OnSettingsButtonClicked(MouseEvent mouseEvent) {
        pagesController.goToSettingsScreenFrom(mouseEvent, pagesController.page_Settings, pagesController.page_CodesMenu);
    }

    public void OnChangeStatusCodeButtonClicked(MouseEvent mouseEvent) {
        pagesController.goToEnableDisableCodeScreenConstructing(mouseEvent, pagesController.page_EnableDisableCodes);
    }

    public void OnDeleteCodesButtonClicked(MouseEvent mouseEvent) {
        pagesController.goToDeleteCodeScreenConstructing(mouseEvent, pagesController.page_DeleteCode);
    }
}
