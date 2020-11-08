package Windows.Settings;

import Controllers.LanguagesController;
import Controllers.PagesController;
import Model.Language;
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

public class ConfigureLanguageViewController {

    PagesController pagesController = Singleton.GetPagesController();
    LanguagesController languagesController = Singleton.GetLanguagesController();

    @FXML private TableView<Language> tableLanguages;
    @FXML private TableColumn<Language, String> columnLanguage;
    @FXML private TableColumn<Language, String> columnState;

    public void InitData(){
        pagesController = new PagesController();

        tableLanguages.setEditable(true);
        tableLanguages.setFixedCellSize(40.0);
        tableLanguages.setStyle( "-fx-alignment: CENTER;");
        tableLanguages.setStyle("-fx-cell-size: 50px");
        tableLanguages.setStyle("-fx-font-size: 20px");
        columnLanguage.setCellValueFactory(new PropertyValueFactory<>("description"));
        columnState.setCellValueFactory(new PropertyValueFactory<>("enabledDescription"));
        columnLanguage.setResizable(false);
        columnState.setResizable(false);

        PopulateListView();
    }

    private void PopulateListView() {
        ArrayList<Language> languagesList = languagesController.GetLanguages();
        //obcode.addAll(codsList);
        tableLanguages.getItems().setAll(languagesList);
    }

    public void OnBackButtonClicked(MouseEvent mouseEvent) {
        pagesController.goToSettingsScreenFrom(mouseEvent,pagesController.page_Settings, pagesController.page_MainMenu);
    }

    public void OnSettingsButtonClicked(MouseEvent mouseEvent) {
        pagesController.goToSettingsScreenFrom(mouseEvent,pagesController.page_Settings,pagesController.page_ConfigureLanguage);
    }

    public void OnSaveButtonClicked(MouseEvent mouseEvent) {
    }

    public void OnTableClick(MouseEvent mouseEvent) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Activar el llenguatge ' " + tableLanguages.getSelectionModel().getSelectedItem().getDescription() + "' ?", ButtonType.YES, ButtonType.NO);
        alert.showAndWait();

        Language language = tableLanguages.getSelectionModel().getSelectedItem();
        if (language.IsEnabled()){
            return;
        }

        if (alert.getResult() == ButtonType.YES) {
            //filesController.ClearFile(filesController.codesFilePath);
            //phoneNumbersController.SaveCodes();
            languagesController.Update(language);
            languagesController.UpdateStateFalseLessLanguage(language);
            tableLanguages.refresh();
        }
    }
}
