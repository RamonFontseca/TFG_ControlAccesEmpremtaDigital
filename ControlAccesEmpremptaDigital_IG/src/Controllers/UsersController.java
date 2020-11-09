package Controllers;

import DataAcces.ConnectionDB;
import Encrypter.Encrypter;
import Model.PhoneNumber;
import Model.User;
import Singleton.Singleton;

import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

public class UsersController {
    private ArrayList<User> usersList;
    private String activeUserName;

    private ConnectionDB conn;

    public UsersController(){
        usersList = new ArrayList<>();
        activeUserName = "admin";
        InitData();
    }

    private void InitData(){
        usersList = new ArrayList<User>();
        LoadUsers();
    }

    private void LoadUsers() {
        LoadUsersFromFile();
    }

    private void LoadUsersFromFile()
    {
        try {
            File file = new File(Singleton.GetFilesController().usersFilePath);
            Singleton.GetFilesController();
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String data = scanner.nextLine();
                System.out.println(data);
                var a = data.split(",");
                String part1 = a[0];
                String part2 = a[1];
                String passwordDecrypted = Encrypter.decrypt(part2);
                User u = new User(part1, passwordDecrypted);

                usersList.add(u);
            }
            scanner.close();
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public boolean isValidUser(String name, String password) throws SQLException {
        User u = new User(name, password);
        int i = 0;
        boolean trobat = false;
        while(i < usersList.size() && !trobat){
            if (usersList.get(i).equals(u)){
                trobat = true;
            }
            i++;
        }
        return trobat;
    }

    public String getActiveUserName()
    {
        return activeUserName;
    }

    public void setActiveUserName(String activeUserName)
    {
        this.activeUserName = activeUserName;
    }
}
