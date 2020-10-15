package Windows.Codes;

import Controllers.CodesController;
import Controllers.FilesController;
import Controllers.PagesController;
import Model.Code;
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

public class EnableDisableCodesViewController {

    @FXML private TableView<Code> tableCodes;
    @FXML private TableColumn<Code, String> columnCode;
    @FXML private TableColumn<Code, Integer> columnUses;
    @FXML private TableColumn<Code, String> columnState;

    private PagesController pagesController;
    private CodesController codesController;
    private FilesController filesController;

    public void InitData(){
        pagesController = new PagesController();
        codesController = new CodesController();
        filesController = new FilesController();

        tableCodes.setEditable(true);
        tableCodes.setFixedCellSize(40.0);
        columnCode.setStyle( "-fx-alignment: CENTER;");
        columnCode.setCellValueFactory(new PropertyValueFactory<>("codeNum"));
        columnUses.setCellValueFactory(new PropertyValueFactory<>("remainingUses"));
        columnState.setCellValueFactory(new PropertyValueFactory<>("IsEnabled"));
        columnCode.setResizable(false);
        columnUses.setResizable(false);
        columnState.setResizable(false);


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
