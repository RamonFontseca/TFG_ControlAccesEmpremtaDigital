package Windows.Fingerprints;

import Controllers.CodesController;
import Controllers.FilesController;
import Controllers.FingerprintsController;
import Controllers.PagesController;
import Model.Code;
import Model.Fingerprint;
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

public class EnableDisableFingerprintsViewController {

    @FXML private TableView<Fingerprint> tableCodes;
    @FXML private TableColumn<Fingerprint, String> columnCode;
    @FXML private TableColumn<Fingerprint, Integer> columnUses;
    @FXML private TableColumn<Fingerprint, String> columnState;

    private PagesController pagesController = Singleton.GetPagesController();
    private FingerprintsController fingerprintsController = Singleton.GetFingerprintsController();
    private FilesController filesController = Singleton.GetFilesController();

    public void InitData(){
        tableCodes.setEditable(true);
        tableCodes.setFixedCellSize(40.0);
        columnCode.setStyle( "-fx-alignment: CENTER;");
        columnCode.setStyle("-fx-cell-size: 50px");
        tableCodes.setStyle("-fx-font-size: 20px");
        columnCode.setCellValueFactory(new PropertyValueFactory<>("name"));
        columnUses.setCellValueFactory(new PropertyValueFactory<>("remainingUses"));
        columnState.setCellValueFactory(new PropertyValueFactory<>("status"));
        columnCode.setResizable(false);
        columnUses.setResizable(false);
        columnState.setResizable(false);


        PopulateListView();
    }

    private void PopulateListView() {
        ArrayList<Fingerprint> fingerprintsList = fingerprintsController.getFingerprints();
        //obcode.addAll(codsList);
        tableCodes.getItems().setAll(fingerprintsList);
    }

    public void OnBackButtonClicked(MouseEvent mouseEvent) {
        pagesController.goToScreen(mouseEvent, pagesController.page_FingerprintsMenu);
    }

    public void OnSettingsButtonClicked(MouseEvent mouseEvent) {
        pagesController.goToSettingsScreenFrom(mouseEvent,pagesController.page_Settings,pagesController.page_EnableDisableFingerprints);
    }

    public void OnSaveButtonClicked(MouseEvent mouseEvent) {
    }

    public void OnTableClick(MouseEvent mouseEvent) {
        System.out.println(tableCodes.getSelectionModel().getSelectedItem().getName());


        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Canviar d'estat el codi ' " + tableCodes.getSelectionModel().getSelectedItem().getName() + "' ?", ButtonType.YES, ButtonType.NO);
        alert.showAndWait();

        if (alert.getResult() == ButtonType.YES) {
            Fingerprint f = tableCodes.getSelectionModel().getSelectedItem();
            filesController.ClearFile(filesController.codesFilePath);
            //fingerprintsController.SaveCodes();
            f.changeState();
            fingerprintsController.Update(f);
            tableCodes.refresh();
        }
    }
}
