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
        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ControlAcces","root","admin");
        conn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;database=ControlAcces", "admin", "admin");
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
            String codeNum = result.getString("codeNum");
            int remainingUses = result.getInt("RemainingUses");
            boolean IsEnabled = result.getBoolean("IsEnabled");

            Code c = new Code(codeNum, remainingUses, IsEnabled);
            codesList.add(c);
        }
        return codesList;
    }

    public void AddCode(Code c) throws SQLException {/*
        Statement statement = connectToDB();
        statement.executeUpdate("insert into Codes(CodeNum, RemainingUses, IsEnabled) values ('"+c.getCodeNum()+"','"+c.getRemainingUses()+"','true');");

        System.out.println(statement.toString());*/
    }

    public void UpdateCode(Code c) throws SQLException {/*
        Statement statement = connectToDB();
        statement.executeUpdate(" update Codes set CodeNum = '" + c.getCodeNum() + "', RemainingUses = "+c.getRemainingUses()+", IsEnabled = "+c.getIsEnabledInteger() +" where CodeNum = '"+c.getCodeNum()+"' ");

        System.out.println(statement.toString());*/
    }

    public void DeleteCode(Code c) throws SQLException {/*
        Statement statement = connectToDB();
        statement.executeUpdate("delete from Codes where CodeNum = '"+c.getCodeNum()+"'");

        System.out.println(statement.toString());*/
    }
}
