package Controllers;

import DataAcces.ConnectionDB;
import Model.Code;
import Model.User;
import javafx.beans.Observable;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;

import java.sql.SQLException;
import java.util.ArrayList;

public class CodesController {
    private ArrayList<Code> codesList;
    private ConnectionDB conn;

    public CodesController(){
        codesList = new ArrayList<>();
        InitData();
    }

    private void InitData(){
        conn = new ConnectionDB();
        try {
            codesList = conn.getCodes();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean isExistingCode(String codeNum, int remainginUses) throws SQLException {
        Code c = new Code(codeNum, remainginUses);
        int i = 0;
        boolean trobat = false;
        while(i < codesList.size() && !trobat){
            if (codesList.get(i).equalsCode(c)){
                trobat = true;
            }
            i++;
        }
        return trobat;
    }

    public boolean AddNewPermCode(String codeNum, int remainingUses){
        try {
            if (!isExistingCode(codeNum, remainingUses)){
                Code c = new Code(codeNum, remainingUses);
                conn.AddCode(c);

                return  true;
            }
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public ArrayList<Code> getCodes(){
        try {
            return conn.getCodes();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    public void updateCode(Code c) {
        try {
            if (isExistingCode(c.getCodeNum(), c.getRemainingUses())){
                conn.UpdateCode(c);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteCode(Code c) {
        try {
            if (isExistingCode(c.getCodeNum(), c.getRemainingUses())){
                conn.DeleteCode(c);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
