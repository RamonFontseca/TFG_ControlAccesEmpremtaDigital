package Windows.Codes;

import Controllers.CodesController;
import Controllers.FilesController;
import Controllers.PagesController;
import Model.Code;
import Singleton.Singleton;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Orientation;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseEvent;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;

public class DeleteCodesViewController {

    @FXML private TableView<Code> tableCodes;
    @FXML private TableColumn<Code, String> columnCode;
    @FXML private TableColumn<Code, Integer> columnUses;
    @FXML private TableColumn<Code, String> columnState;

    private PagesController pagesController;
    CodesController codesController = Singleton.GetCodesController();
    private FilesController filesController;

    public void InitData(){
        pagesController = new PagesController();

        filesController = new FilesController();

        tableCodes.setEditable(true);
        columnCode.setCellValueFactory(new PropertyValueFactory<>("codeNum"));
        columnUses.setCellValueFactory(new PropertyValueFactory<>("remainingUses"));
        columnState.setCellValueFactory(new PropertyValueFactory<>("status"));
        tableCodes.setStyle( "-fx-alignment: CENTER;");
        tableCodes.setStyle("-fx-cell-size: 50px");
        tableCodes.setStyle("-fx-font-size: 20px");

        PopulateListView();
    }

    private void PopulateListView() {
        ArrayList<Code> codsList = codesController.getCodes();
        ObservableList<Code> obcode = FXCollections.observableArrayList();
        //obcode.addAll(codsList);
        tableCodes.getItems().setAll(codsList);
    }

    public void OnBackButtonClicked(MouseEvent mouseEvent) {
        pagesController.goToScreen(mouseEvent, pagesController.page_CodesMenu);
    }

    public void OnSettingsButtonClicked(MouseEvent mouseEvent) {
        pagesController.goToSettingsScreenFrom(mouseEvent,pagesController.page_Settings,pagesController.page_EnableDisableCodes);
    }

    public void OnSaveButtonClicked(MouseEvent mouseEvent) {
    }

    public void OnTableClick(MouseEvent mouseEvent) {
        System.out.println(tableCodes.getSelectionModel().getSelectedItem().getCodeNum());


        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Esborrar el codi ' " + tableCodes.getSelectionModel().getSelectedItem().getCodeNum() + "' ?", ButtonType.YES, ButtonType.NO);
        alert.showAndWait();

        if (alert.getResult() == ButtonType.YES) {
            Code c = tableCodes.getSelectionModel().getSelectedItem();
            Code selectedItem = tableCodes.getSelectionModel().getSelectedItem();
            tableCodes.getItems().remove(selectedItem);
            codesController.deleteCode(c);
            filesController.ClearFile(filesController.codesFilePath);
            codesController.SaveCodes();
            codesController.deleteCode(c);
            tableCodes.refresh();
        }
    }
}
