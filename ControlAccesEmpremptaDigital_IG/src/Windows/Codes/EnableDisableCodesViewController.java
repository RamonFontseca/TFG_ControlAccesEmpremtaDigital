package Windows.Codes;

import Controllers.CodesController;
import Controllers.PagesController;
import Model.Code;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Orientation;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.input.MouseEvent;

import java.util.ArrayList;

public class EnableDisableCodesViewController {

    @FXML private ListView<String> listCodes;

    private PagesController pagesController;
    private CodesController codesController;

    public void InitData(){
        pagesController = new PagesController();
        codesController = new CodesController();
        listCodes.setFixedCellSize(50);

        PopulateListView();
    }

    private void PopulateListView() {
        ArrayList<Code> codsList = codesController.getCodes();
        ObservableList<String> cd = FXCollections.observableArrayList();
        for (Code code: codsList){
            cd.add(code.toString());
        }
        ObservableList<String> items =FXCollections.observableArrayList ("Single", "Double", "Suite", "Family App", "Double", "Suite", "Family App", "Double", "Suite", "Family App", "Double", "Suite", "Family App", "Double", "Suite", "Family App", "Double", "Suite", "Family App", "Double", "Suite", "Family App");
        listCodes.getItems().addAll(cd);
    }

    public void OnBackButtonClicked(MouseEvent mouseEvent) {
        pagesController.goToScreen(mouseEvent, pagesController.page_CodesMenu);
    }

    public void OnSettingsButtonClicked(MouseEvent mouseEvent) {
        pagesController.goToSettingsScreenFrom(mouseEvent,pagesController.page_Settings,pagesController.page_EnableDisableCodes);
    }

    public void OnSaveButtonClicked(MouseEvent mouseEvent) {
    }

}
