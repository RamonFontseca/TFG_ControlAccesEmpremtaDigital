package DataAcces;

import Model.Code;
import Model.User;

import java.sql.*;
import java.util.ArrayList;

public class ConnectionDB {
    private Connection conn;

    public ConnectionDB(){
        conn = null;
    }

    private Statement connectToDB() throws SQLException {
        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ControlAcces","root","123456789abc");
        return conn.createStatement();
    }


    public ArrayList<User> getUsers() throws SQLException {
        Statement statement = connectToDB();
        ResultSet result = statement.executeQuery("select * from Users");
        ArrayList<User> usersList = new ArrayList<>();
        while (result.next()) {
            String username = result.getString("Name");
            String password = result.getString("Password");

            User u = new User(username, password);
            usersList.add(u);
        }
        return usersList;
    }

    public ArrayList<Code> getCodes() throws SQLException {
        Statement statement = connectToDB();
        ResultSet result = statement.executeQuery("select * from Codes");
        ArrayList<Code> codesList = new ArrayList<>();
        while (result.next()) {
            String codeNum = result.getString("CodeNum");
            int remainingUses = result.getInt("RemainingUses");
            int IsEnabled = result.getInt("IsEnabled");

            Code c = new Code(codeNum, remainingUses, IsEnabled);
            codesList.add(c);
        }
        return codesList;
    }

    public void AddCode(Code c) throws SQLException {
        Statement statement = connectToDB();
        statement.executeUpdate("insert into Codes(CodeNum, RemainingUses, IsEnabled) values ('"+c.getCodeNum()+"','"+c.getRemainingUses()+"','1');");

        System.out.println(statement.toString());
    }
}
