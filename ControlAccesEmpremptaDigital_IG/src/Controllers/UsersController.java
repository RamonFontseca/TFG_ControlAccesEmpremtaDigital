package Controllers;

import DataAcces.ConnectionDB;
import Model.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class UsersController {
    private ArrayList<User> usersList;
    private ConnectionDB conn;

    public UsersController(){
        usersList = new ArrayList<>();
        InitData();
    }

    private void InitData(){
        usersList = new ArrayList<User>();
        LoadUsers();
    }

    private void LoadUsers() {
        User u = new User("Admin", "1234");
        usersList.add(u);
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
}
