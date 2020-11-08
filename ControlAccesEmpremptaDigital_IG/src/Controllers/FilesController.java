package Controllers;

import com.sun.javafx.iio.ios.IosDescriptor;

import java.io.*;

public class FilesController {

    public String codesFilePath = "src/Files/codes.txt";
    public String usersFilePath = "src/Files/users.txt";
    public String languagesFilePath = "src/Files/languages.txt";
    public String notificationsFilePath = "src/Files/notificationsConfiguration.txt";
    public String stadisticsCodesFilePath = "src/Files/stadisticsCodes.txt";
    public String stadisticsUsersFilePath = "src/Files/stadisticsUsers.txt";
    public String phoneNumbersFilePath = "src/Files/phoneNumbers.txt";

    public FilesController()
    {

    }

    public boolean WriteToFile(String filePath, String data)  {
        File file = new File(filePath);
        FileWriter fr = null;
        try
        {
            fr = new FileWriter(file, true);
            fr.append(data);
        } catch (IOException e){
            e.printStackTrace();
            return false;
        }
        finally {
            try {
                fr.close();
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
        }
        return  true;
    }

    public void ClearFile(String filePath)
    {
        PrintWriter writer = null;
        try {
            writer = new PrintWriter(filePath);
            writer.print("");
            writer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
