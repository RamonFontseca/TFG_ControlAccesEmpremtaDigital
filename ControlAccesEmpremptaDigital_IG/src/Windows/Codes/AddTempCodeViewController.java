package Windows.Codes;

import Controllers.CodesController;
import Controllers.PagesController;
import DataAcces.ConnectionDB;
import Singleton.Singleton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class AddTempCodeViewController extends AddPermCodeViewController{

    @FXML private ComboBox cmbRemainingUses;

    public void InitData(){
        InitializeUserLabel();
        InitComboBox();
    }

    private void InitComboBox(){
        ObservableList<String> options = FXCollections.observableArrayList("5","10","15","20","25","50");
        cmbRemainingUses.setItems(options);
        cmbRemainingUses.setValue("5");
    }

    @Override
    protected int getRemainingUses(){
        return Integer.parseInt(cmbRemainingUses.getSelectionModel().getSelectedItem().toString());
    }
}


