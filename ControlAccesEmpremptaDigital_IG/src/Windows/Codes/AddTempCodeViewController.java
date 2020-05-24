package Windows.Codes;

import Controllers.CodesController;
import Controllers.PagesController;
import DataAcces.ConnectionDB;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class AddTempCodeViewController extends AddPermCodeViewController{

    private PagesController pagesController = new PagesController();
    private CodesController codesController = new CodesController();
    private int maxLenght = 10;

    @FXML private TextField textCode;
    @FXML private ComboBox cmbRemainingUses;


    public void InitData(){
        InitComboBox();
    }

    private void InitComboBox(){
        ObservableList<String> options = FXCollections.observableArrayList("5","10","15","20","25","50");
        cmbRemainingUses.setItems(options);
        cmbRemainingUses.setValue("5");
    }

    @Override
    protected int getRemainingUses(){
        return Integer.parseInt(cmbRemainingUses.getSelectionModel().getSelectedItem().toString());
    }

    public void OnSettingsButtonClicked(MouseEvent mouseEvent) {
        pagesController.goToSettingsScreenFrom(mouseEvent, pagesController.page_Settings, pagesController.page_AddTempCode);
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

    public void OnSaveButtonClicked(MouseEvent mouseEvent) {
        if (isFormatCodeValid(textCode.getText())){
            if (codesController.AddNewPermCode(textCode.getText(), getRemainingUses())){
                textCode.clear();
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information Dialog");
                alert.setHeaderText(null);
                alert.setContentText("Code added!");

                alert.showAndWait();
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


