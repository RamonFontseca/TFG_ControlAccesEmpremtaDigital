package Windows.Fingerprints;

import Controllers.CodesController;
import Controllers.FilesController;
import Controllers.FingerprintsController;
import Controllers.PagesController;
import Model.Code;
import Model.Fingerprint;
import Singleton.Singleton;
import Windows.Base.BaseFingerprintsController;
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

public class EnableDisableFingerprintsViewController extends BaseFingerprintsController {

    @FXML private TableView<Fingerprint> tableCodes;
    @FXML private TableColumn<Fingerprint, String> columnCode;
    @FXML private TableColumn<Fingerprint, Integer> columnUses;
    @FXML private TableColumn<Fingerprint, String> columnState;

    public void InitData(){
        InitializeUserLabel();

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

    @Override
    protected void PopulateListView() {
        ArrayList<Fingerprint> fingerprintsList = fingerprintsController.getFingerprints();
        tableCodes.getItems().setAll(fingerprintsList);
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
