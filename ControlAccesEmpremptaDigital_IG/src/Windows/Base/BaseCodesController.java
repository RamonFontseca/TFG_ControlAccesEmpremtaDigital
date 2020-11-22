package Windows.Base;

import Controllers.CodesController;
import DataAcces.ConnectionDB;
import Model.Code;
import Singleton.Singleton;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.util.ArrayList;

public abstract class BaseCodesController extends BaseNumericController {

    protected CodesController codesController = Singleton.GetCodesController();

    // Members
    @FXML protected TableView<Code> tableCodes;
    @FXML protected TableColumn<Code, String> columnCode;
    @FXML protected TableColumn<Code, String> columnUses;
    @FXML protected TableColumn<Code, String> columnState;

    // Methods
    @Override
    public void OnBackButtonClicked(MouseEvent mouseEvent) {
        pagesController.goToScreen(mouseEvent, pagesController.page_CodesMenu);
    }

    @Override
    public void OnSettingsButtonClicked(MouseEvent mouseEvent) {
        pagesController.goToSettingsScreenFrom(mouseEvent,pagesController.page_Settings,pagesController.page_CodesMenu);
    }

    public void OnSaveButtonClicked(MouseEvent mouseEvent) {
        if (isFormatCodeValid(textCode.getText())){
            if (codesController.AddCode(textCode.getText(), getRemainingUses())){
                textCode.clear();
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information Dialog");
                alert.setHeaderText(null);
                alert.setContentText("Codi guardat!");

                alert.showAndWait();
                //codesController.SaveCodes();
                codesController.SaveLastCode();

                pagesController.goToScreen(mouseEvent, pagesController.page_CodesMenu);
            }
            else{
                showAlertErrorMessage("El codi ja existeix!");
            }
        }
        else{
            showAlertErrorMessage("Format no vàlid. El codi ha de ser de 4 digits!");
        }
    }

    protected boolean isFormatCodeValid(String text) {
        //return (text.length() <= 10 && text.length() >=4);
        return (text.length() == 4);
    }

    protected int getRemainingUses(){
        return -1;
    }

    @Override
    protected void PopulateListView() {
        ArrayList<Code> codsList = codesController.getCodes();
        tableCodes.getItems().setAll(codsList);
    }
}
