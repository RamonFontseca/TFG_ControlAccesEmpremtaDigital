package Windows.PhoneNumbers;

import Controllers.CodesController;
import Controllers.PagesController;
import Controllers.PhoneNumbersController;
import DataAcces.ConnectionDB;
import Model.PhoneNumber;
import Singleton.Singleton;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class AddPhoneNumberViewController {

    PagesController pagesController = new PagesController();
    PhoneNumbersController phoneNumbersController = Singleton.GetPhoneNumbersController();

    @FXML
    private TextField textPhoneNumber;

    private int maxLenght = 10;

    public void OnSettingsButtonClicked(MouseEvent mouseEvent) {
        pagesController.goToSettingsScreenFrom(mouseEvent, pagesController.page_Settings, pagesController.page_AddPhoneNumber);
    }

    public void OnBackButtonClicked(MouseEvent mouseEvent) {
        pagesController.goToScreen(mouseEvent, pagesController.page_PhoneNumbersMenu);
    }

    public void OnButton0Click(MouseEvent mouseEvent) {
        if (textPhoneNumber.getLength() < maxLenght)
            textPhoneNumber.appendText("0");
    }

    public void OnButton1Click(MouseEvent mouseEvent) {
        if (textPhoneNumber.getLength() < maxLenght)
            textPhoneNumber.appendText("1");
    }

    public void OnButton2Click(MouseEvent mouseEvent) {
        if (textPhoneNumber.getLength() < maxLenght)
            textPhoneNumber.appendText("2");
    }

    public void OnButton3Click(MouseEvent mouseEvent) {
        if (textPhoneNumber.getLength() < maxLenght)
            textPhoneNumber.appendText("3");
    }

    public void OnButton5Click(MouseEvent mouseEvent) {
        if (textPhoneNumber.getLength() < maxLenght)
            textPhoneNumber.appendText("5");
    }

    public void OnButton4Click(MouseEvent mouseEvent) {
        if (textPhoneNumber.getLength() < maxLenght)
            textPhoneNumber.appendText("4");
    }

    public void OnButton6Click(MouseEvent mouseEvent) {
        if (textPhoneNumber.getLength() < maxLenght)
            textPhoneNumber.appendText("6");
    }

    public void OnButton8Click(MouseEvent mouseEvent) {
        if (textPhoneNumber.getLength() < maxLenght)
            textPhoneNumber.appendText("8");
    }

    public void OnButton7Click(MouseEvent mouseEvent) {
        if (textPhoneNumber.getLength() < maxLenght)
            textPhoneNumber.appendText("7");
    }

    public void OnButton9Click(MouseEvent mouseEvent) {
        if (textPhoneNumber.getLength() < maxLenght)
            textPhoneNumber.appendText("9");
        ConnectionDB connectionDB = new ConnectionDB();
    }

    public void OnEraseButtonClicked(MouseEvent mouseEvent) {
        if (textPhoneNumber.getLength() > 0)
            textPhoneNumber.setText(textPhoneNumber.getText(0, textPhoneNumber.getLength()-1));
    }

    public void OnSaveButtonClicked(MouseEvent mouseEvent) {
        String textPN = textPhoneNumber.getText().replaceAll(" ","").trim();
        if (textPN.length() < 6 || textPN.length() > 10){
            showAlertErrorMessage("El format del número de telèfon incorrecte. \nLlargada mínima: 6. Llargada màxima:10");
            return;
        }

        var s = Singleton.GetPhoneNumbersController();
        PhoneNumber pn = new PhoneNumber(textPhoneNumber.getText().replaceAll(" ","").trim(),true);

        if (phoneNumbersController.Add(pn))
        {
            textPhoneNumber.clear();
            showInformationMessage("Telèfon afegit!");

            pagesController.goToScreen(mouseEvent, pagesController.page_PhoneNumbersMenu);
        }
        else{
            showAlertErrorMessage("El telèfon ja existeix!");
        }

       /* if (textPhoneNumber.getLength() > 0)
            /*if (codesController.AddCode(textCode.getText(), getRemainingUses())){
                textCode.clear();
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information Dialog");
                alert.setHeaderText(null);
                alert.setContentText("Code added!");

                alert.showAndWait();
                //codesController.SaveCodes();
                codesController.SaveLastCode();

                pagesController.goToScreen(mouseEvent, pagesController.page_CodesMenu);
            }
            else{
                showAlertErrorMessage("Code alreaady exists!");
            }
        }
        else{
            showAlertErrorMessage("Code format not valid. Min lenght 4 .. Max lenght 10");
        }*/
    }

    private void showAlertErrorMessage(String error) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(error);

        alert.showAndWait();
    }

    private void showInformationMessage(String message)
    {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText(message);

        alert.showAndWait();
    }
}
