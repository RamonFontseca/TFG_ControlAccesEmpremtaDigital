package Windows.Base;

import Controllers.PhoneNumbersController;
import Model.PhoneNumber;
import Singleton.Singleton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;

import java.util.ArrayList;

public abstract class BasePhonesController extends BaseNumericController {

    protected PhoneNumbersController phoneNumbersController = Singleton.GetPhoneNumbersController();

    @FXML protected TableView<PhoneNumber> tablePhoneNumbers;
    @FXML protected TableColumn<PhoneNumber, String> columnPhoneNumber;
    @FXML protected TableColumn<PhoneNumber, String> columnState;

    @Override
    public void OnBackButtonClicked(MouseEvent mouseEvent) {
        pagesController.goToScreen(mouseEvent, pagesController.page_PhoneNumbersMenu);
    }

    @Override
    public void OnSettingsButtonClicked(MouseEvent mouseEvent) {
        pagesController.goToSettingsScreenFrom(mouseEvent,pagesController.page_Settings,pagesController.page_PhoneNumbersMenu);
    }

    @Override
    protected void PopulateListView() {
        ArrayList<PhoneNumber> phoneNumbersList = phoneNumbersController.GetPhoneNumbers();
        tablePhoneNumbers.getItems().setAll(phoneNumbersList);
    }
}
