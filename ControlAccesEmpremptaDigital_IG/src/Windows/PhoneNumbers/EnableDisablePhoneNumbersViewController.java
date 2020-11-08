package Windows.PhoneNumbers;

import Controllers.CodesController;
import Controllers.FilesController;
import Controllers.PagesController;
import Controllers.PhoneNumbersController;
import Model.Code;
import Model.PhoneNumber;
import Singleton.Singleton;
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

public class EnableDisablePhoneNumbersViewController {

    @FXML private TableView<PhoneNumber> tablePhoneNumbers;
    @FXML private TableColumn<PhoneNumber, String> columnPhoneNumber;
    @FXML private TableColumn<PhoneNumber, String> columnState;

    PagesController pagesController = new PagesController();
    PhoneNumbersController phoneNumbersController = Singleton.GetPhoneNumbersController();

    public void InitData(){
        pagesController = new PagesController();

        tablePhoneNumbers.setEditable(true);
        tablePhoneNumbers.setFixedCellSize(40.0);
        tablePhoneNumbers.setStyle( "-fx-alignment: CENTER;");
        tablePhoneNumbers.setStyle("-fx-cell-size: 50px");
        tablePhoneNumbers.setStyle("-fx-font-size: 20px");
        columnPhoneNumber.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        columnState.setCellValueFactory(new PropertyValueFactory<>("enabledDescription"));
        columnPhoneNumber.setResizable(false);
        columnState.setResizable(false);

        PopulateListView();
    }

    private void PopulateListView() {
        ArrayList<PhoneNumber> phoneNumbersList = phoneNumbersController.GetPhoneNumbers();
        ObservableList<PhoneNumber> obcode = FXCollections.observableArrayList();
        //obcode.addAll(codsList);
        tablePhoneNumbers.getItems().setAll(phoneNumbersList);
    }

    public void OnBackButtonClicked(MouseEvent mouseEvent) {
        pagesController.goToScreen(mouseEvent, pagesController.page_PhoneNumbersMenu);
    }

    public void OnSettingsButtonClicked(MouseEvent mouseEvent) {
        pagesController.goToSettingsScreenFrom(mouseEvent,pagesController.page_Settings,pagesController.page_EnableDisableCodes);
    }

    public void OnSaveButtonClicked(MouseEvent mouseEvent) {
    }

    public void OnTableClick(MouseEvent mouseEvent) {
        System.out.println(tablePhoneNumbers.getSelectionModel().getSelectedItem().getPhoneNumber());


        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Canviar d'estat el telèfon ' " + tablePhoneNumbers.getSelectionModel().getSelectedItem().getPhoneNumber() + "' ?", ButtonType.YES, ButtonType.NO);
        alert.showAndWait();

        if (alert.getResult() == ButtonType.YES) {
            tablePhoneNumbers.getSelectionModel().getSelectedItem().changeState();
            PhoneNumber phoneNumber = tablePhoneNumbers.getSelectionModel().getSelectedItem();
            tablePhoneNumbers.refresh();
            //filesController.ClearFile(filesController.codesFilePath);
            //phoneNumbersController.SaveCodes();
            phoneNumbersController.Update(phoneNumber);
        }
    }
}
