package Model;

public class UserUsages {

    private String userName;
    private int userUsages;

    public UserUsages(){
    }

    public UserUsages(String userName){
        this.userName = userName;
        this.userUsages = 0;
    }

    public UserUsages(String userName, int userUsages){
        this.userName = userName;
        this.userUsages = userUsages;
    }

    public String getUserName()
    {
        return userName;
    }

    public int getUserUsages()
    {
        return userUsages;
    }
}
