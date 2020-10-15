package Controllers;

import DataAcces.ConnectionDB;
import Model.Code;
import Model.User;
import javafx.beans.Observable;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.OutputStream;
import java.nio.file.StandardOpenOption;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class CodesController {
    private ArrayList<Code> codesList;
    //private ConnectionDB conn;

    private FilesController filesController = new FilesController();

    public CodesController(){
        codesList = new ArrayList<>();
        InitData();
    }

    private void InitData(){
        /*
        conn = new ConnectionDB();
        try {
            codesList = conn.getCodes();
        } catch (SQLException e) {
            e.printStackTrace();
        }*/
        LoadCodes();

    }

    private void LoadCodes()
    {
        // TODO
        LoadCodesFromFile();
    }

    private void LoadCodesFromFile()
    {
        try {
            File file = new File(filesController.codesFilePath);
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String data = scanner.nextLine();
                System.out.println(data);
                var a = data.split(",");
                String part1 = a[0];
                String part2 = a[1];
                String part3 = a[2];
                Code c = new Code(part1, Integer.parseInt(part2), Boolean.parseBoolean(part3));
                codesList.add(c);
            }
            scanner.close();
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public  void SaveCodes()
    {
        // Wirte codes to file
        // Format: codeNum, remainingUses, isEnabled.
        for (var code : codesList) {
            String data = String.format("%s,%d,%s\n", code.getCodeNum(), code.getRemainingUses(), String.valueOf(code.getIsEnabled()));
            filesController.WriteToFile(filesController.codesFilePath, data);
        }

    }

    public void SaveLastCode()
    {
        Code c = codesList.get(codesList.size()-1);
        String data = String.format("%s,%d,%s\n", c.getCodeNum(), c.getRemainingUses(), String.valueOf(c.getIsEnabled()));
        filesController.WriteToFile(filesController.codesFilePath, data);
    }

    private int GetCodeIndex(Code c)
    {
        int reuslt = -1, i = 0;
        boolean finded = false;

        while (!finded && i < codesList.size())
        {
            Code code = codesList.get(i);
            if (code.equalsCode(c))
            {
                reuslt = i;
                finded = true;
            }
            i++;
        }
        return reuslt;
    }

    public boolean isExistingCode(String codeNum, int remainginUses) {
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

    public ArrayList<Code> getCodes(){
        return codesList;
        /*
        try {
            return conn.getCodes();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;*/
    }

    public boolean AddCode(String codeNum, int remainingUses){
        boolean added = false;
        if (!isExistingCode(codeNum, remainingUses))
        {
            Code c = new Code(codeNum, remainingUses, true);
            codesList.add(c);
            added = true;
        }
        return added;
        /*
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
        return false;*/
    }

    public void updateCode(Code c) {
        int index = GetCodeIndex(c);
        if (index == -1) return;

        var code = codesList.get(index);
        code.setCodeNum(c.getCodeNum());
        code.setRemainingUses(c.getRemainingUses());
        code.setEnabled(c.getIsEnabled());
        codesList.set(index,c);
        /*
        try {
            if (isExistingCode(c.getCodeNum(), c.getRemainingUses())){
                conn.UpdateCode(c);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }*/
    }

    public void deleteCode(Code c) {
        int index = GetCodeIndex(c);
        if (index == -1) return;

        codesList.remove(index);
        /*
        try {
            if (isExistingCode(c.getCodeNum(), c.getRemainingUses())){
                conn.DeleteCode(c);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }*/
    }
}
