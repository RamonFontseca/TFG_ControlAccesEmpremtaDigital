package Windows.Codes;

import Controllers.CodesController;
import Controllers.FilesController;
import Controllers.PagesController;
import Model.Code;
import Singleton.Singleton;
import Windows.Base.BaseCodesController;
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

public class DeleteCodesViewController extends BaseCodesController {

    public void InitData(){
        InitializeUserLabel();

        tableCodes.setEditable(true);
        columnCode.setCellValueFactory(new PropertyValueFactory<>("codeNum"));
        columnUses.setCellValueFactory(new PropertyValueFactory<>("remainingUsesString"));
        columnState.setCellValueFactory(new PropertyValueFactory<>("status"));
        tableCodes.setStyle( "-fx-alignment: CENTER;");
        tableCodes.setStyle("-fx-cell-size: 50px");
        tableCodes.setStyle("-fx-font-size: 20px");

        PopulateListView();
    }

    public void OnTableClick(MouseEvent mouseEvent) {
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
