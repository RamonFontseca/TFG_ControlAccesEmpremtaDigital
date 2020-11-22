package Windows.PhoneNumbers;

import Controllers.PagesController;
import Windows.Base.BaseController;
import javafx.scene.input.MouseEvent;

public class PhoneNumbersMenuViewController extends BaseController {

    PagesController pagesController = new PagesController();

    public void OnBackButtonClicked(MouseEvent mouseEvent) {
        pagesController.goToScreen(mouseEvent, pagesController.page_MainMenu);
    }

    public void OnAddPermClicked(MouseEvent mouseEvent) {
        pagesController.goToScreen(mouseEvent, pagesController.page_AddPhoneNumber);
    }

    public void OnSettingsButtonClicked(MouseEvent mouseEvent) {
        pagesController.goToSettingsScreenFrom(mouseEvent, pagesController.page_Settings, pagesController.page_PhoneNumbersMenu);
    }

    public void OnChangeStatusCodeButtonClicked(MouseEvent mouseEvent) {
        pagesController.goToEnableDisablePhoneNumbersScreenConstructing(mouseEvent, pagesController.page_EnableDisablePhoneNumbers);
    }

    public void OnDeleteCodesButtonClicked(MouseEvent mouseEvent) {
        pagesController.goToDeletePhoneNumbersScreenConstructing(mouseEvent, pagesController.page_DeletePhoneNumbers);
    }
}
