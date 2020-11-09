package Windows.PhoneNumbers;

import Controllers.CodesController;
import Controllers.FilesController;
import Controllers.PagesController;
import Controllers.PhoneNumbersController;
import Model.Code;
import Model.PhoneNumber;
import Singleton.Singleton;
import Windows.Base.BaseNumericController;
import Windows.Base.BasePhonesController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.util.ArrayList;

public class DeletePhoneNumbersViewController extends BasePhonesController {

    public void InitData(){
        InitializeUserLabel();

        tablePhoneNumbers.setEditable(true);
        tablePhoneNumbers.setStyle( "-fx-alignment: CENTER;");
        tablePhoneNumbers.setStyle("-fx-cell-size: 50px");
        tablePhoneNumbers.setStyle("-fx-font-size: 20px");
        columnPhoneNumber.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        columnState.setCellValueFactory(new PropertyValueFactory<>("enabledDescription"));
        columnPhoneNumber.setResizable(false);
        columnState.setResizable(false);

        PopulateListView();
    }


    public void OnTableClick(MouseEvent mouseEvent) {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Esborrar el telèfon ' " + tablePhoneNumbers.getSelectionModel().getSelectedItem().getPhoneNumber() + "' ?", ButtonType.YES, ButtonType.NO);
        alert.showAndWait();

        if (alert.getResult() == ButtonType.YES) {
            PhoneNumber phoneNumber = tablePhoneNumbers.getSelectionModel().getSelectedItem();
            if (phoneNumber != null) {
                tablePhoneNumbers.getItems().remove(phoneNumber);
                phoneNumbersController.Delete(phoneNumber);
                tablePhoneNumbers.refresh();
            }
        }
    }
}
