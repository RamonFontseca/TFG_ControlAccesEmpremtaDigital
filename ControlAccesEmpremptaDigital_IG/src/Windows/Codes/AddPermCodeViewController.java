package Windows.Codes;

import Controllers.CodesController;
import Controllers.PagesController;
import DataAcces.ConnectionDB;
import Singleton.Singleton;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class AddPermCodeViewController {

    PagesController pagesController = new PagesController();
    CodesController codesController = new CodesController();

    @FXML private TextField textCode;

    private int maxLenght = 10;

    public void OnSettingsButtonClicked(MouseEvent mouseEvent) {
        pagesController.goToSettingsScreenFrom(mouseEvent, pagesController.page_Settings, pagesController.page_AddPermCode);
    }

    public void OnBackButtonClicked(MouseEvent mouseEvent) {
        pagesController.goToScreen(mouseEvent, pagesController.page_CodesMenu);
    }

    public void OnButton0Click(MouseEvent mouseEvent) {
        if (textCode.getLength() < maxLenght)
            textCode.appendText("0");
    }

    public void OnButton1Click(MouseEvent mouseEvent) {
        if (textCode.getLength() < maxLenght)
            textCode.appendText("1");
    }

    public void OnButton2Click(MouseEvent mouseEvent) {
        if (textCode.getLength() < maxLenght)
            textCode.appendText("2");
    }

    public void OnButton3Click(MouseEvent mouseEvent) {
        if (textCode.getLength() < maxLenght)
            textCode.appendText("3");
    }

    public void OnButton5Click(MouseEvent mouseEvent) {
        if (textCode.getLength() < maxLenght)
            textCode.appendText("5");
    }

    public void OnButton4Click(MouseEvent mouseEvent) {
        if (textCode.getLength() < maxLenght)
            textCode.appendText("4");
    }

    public void OnButton6Click(MouseEvent mouseEvent) {
        if (textCode.getLength() < maxLenght)
            textCode.appendText("6");
    }

    public void OnButton8Click(MouseEvent mouseEvent) {
        if (textCode.getLength() < maxLenght)
            textCode.appendText("8");
    }

    public void OnButton7Click(MouseEvent mouseEvent) {
        if (textCode.getLength() < maxLenght)
            textCode.appendText("7");
    }

    public void OnButton9Click(MouseEvent mouseEvent) {
        if (textCode.getLength() < maxLenght)
            textCode.appendText("9");
        ConnectionDB connectionDB = new ConnectionDB();
    }

    public void OnEraseButtonClicked(MouseEvent mouseEvent) {
        if (textCode.getLength() > 0)
            textCode.setText(textCode.getText(0, textCode.getLength()-1));
    }

    protected int getRemainingUses(){
        return -1;
    }

    public void OnSaveButtonClicked(MouseEvent mouseEvent) {
        if (isFormatCodeValid(textCode.getText())){
            if (codesController.AddCode(textCode.getText(), getRemainingUses())){
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
        }
    }

    private boolean isFormatCodeValid(String text) {
        return (text.length() <= 10 && text.length() >=4);
    }

    private void showAlertErrorMessage(String error) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(error);

        alert.showAndWait();
    }

}
