package Controllers;

import Model.Language;
import Singleton.Singleton;

import java.io.File;
import java.util.Scanner;

public class NotificationsController {
    private boolean enabled;
    private String enabledDescription;

    public NotificationsController(){
        InitData();
    }

    private void InitData()
    {
        LoadNotificationsConfiguration();
    }

    private void LoadNotificationsConfiguration()
    {
        LoadNotificationsConfigurationFromFile();
    }

    private  void LoadNotificationsConfigurationFromFile()
    {
        try {
            File file = new File(Singleton.GetFilesController().notificationsFilePath);
            Singleton.GetFilesController();
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String data = scanner.nextLine();
                System.out.println(data);
                var a = data.split(",");
                String part1 = a[0];
                this.enabled = Boolean.parseBoolean(part1);
                setEnabledDescription(this.enabled);
            }
            scanner.close();
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public boolean GetEnabled()
    {
        return enabled;
    }

    public String GetEnabledDescription()
    {
        return enabledDescription;
    }

    public void setEnabled(boolean enabled){
        this.enabled = enabled;
    }

    public void setEnabledDescription(boolean enabled){
        if (enabled) enabledDescription = "ACTIVAT";
        else enabledDescription = "DESACTIVAT";
    }

    public void changeEnabled(){
        this.enabled = !this.enabled;
        setEnabledDescription(this.enabled);
    }



}
