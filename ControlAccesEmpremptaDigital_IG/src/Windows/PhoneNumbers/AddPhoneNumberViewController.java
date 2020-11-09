package Windows.PhoneNumbers;

import Controllers.CodesController;
import Controllers.PagesController;
import Controllers.PhoneNumbersController;
import DataAcces.ConnectionDB;
import Model.PhoneNumber;
import Singleton.Singleton;
import Windows.Base.BaseController;
import Windows.Base.BaseNumericController;
import Windows.Base.BasePhonesController;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class AddPhoneNumberViewController extends BasePhonesController {


    private int maxLenght = 12;

    @Override
    public void OnSettingsButtonClicked(MouseEvent mouseEvent) {
        pagesController.goToSettingsScreenFrom(mouseEvent, pagesController.page_Settings, pagesController.page_AddPhoneNumber);
    }

    @Override
    public void OnSaveButtonClicked(MouseEvent mouseEvent) {
        String textPN = textCode.getText().replaceAll(" ","").trim();
        if (textPN.length() < 6 || textPN.length() > 10){
            showAlertErrorMessage("El format del número de telèfon incorrecte. \nLlargada mínima: 6. Llargada màxima:10");
            return;
        }

        var s = Singleton.GetPhoneNumbersController();
        PhoneNumber pn = new PhoneNumber(textCode.getText().replaceAll(" ","").trim(),true);

        if (phoneNumbersController.Add(pn))
        {
            textCode.clear();
            showInformationMessage("Telèfon afegit!");

            pagesController.goToScreen(mouseEvent, pagesController.page_PhoneNumbersMenu);
        }
        else showAlertErrorMessage("El telèfon ja existeix!");


    }
}
