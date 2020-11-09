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

import java.util.ArrayList;

public class EnableDisableCodesViewController extends BaseCodesController {

    public void InitData(){
        InitializeUserLabel();

        tableCodes.setEditable(true);
        tableCodes.setFixedCellSize(40.0);
        columnCode.setStyle( "-fx-alignment: CENTER;");
        columnCode.setStyle("-fx-cell-size: 50px");
        tableCodes.setStyle("-fx-font-size: 20px");
        columnCode.setCellValueFactory(new PropertyValueFactory<>("codeNum"));
        columnUses.setCellValueFactory(new PropertyValueFactory<>("remainingUses"));
        columnState.setCellValueFactory(new PropertyValueFactory<>("status"));
        columnCode.setResizable(false);
        columnUses.setResizable(false);
        columnState.setResizable(false);


        PopulateListView();
    }

    public void OnTableClick(MouseEvent mouseEvent) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Canviar d'estat el codi ' " + tableCodes.getSelectionModel().getSelectedItem().getCodeNum() + "' ?", ButtonType.YES, ButtonType.NO);
        alert.showAndWait();

        if (alert.getResult() == ButtonType.YES) {
            tableCodes.getSelectionModel().getSelectedItem().changeState();
            Code c = tableCodes.getSelectionModel().getSelectedItem();
            tableCodes.refresh();
            filesController.ClearFile(filesController.codesFilePath);
            codesController.SaveCodes();
            codesController.updateCode(c);
        }
    }
}
