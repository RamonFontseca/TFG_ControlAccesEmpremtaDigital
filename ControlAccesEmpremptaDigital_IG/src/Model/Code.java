package Model;

public class Code {
    private String codeNum;
    private int remainingUses;
    private boolean isEnabled;
    private String status;
    private String remainingUsesString;

    public Code() {
        this.codeNum = null;
        this.remainingUses = 0;
        this.isEnabled = false;
    }

    public Code(String codeNum, int remainingUses) {
        this.codeNum = codeNum;
        this.remainingUses = remainingUses;
        this.isEnabled = true;
    }

    public Code(String codeNum, int remainingUses, boolean IsEnabled) {
        this.codeNum = codeNum;
        this.remainingUses = remainingUses;
        this.isEnabled = IsEnabled;
    }

    public String getCodeNum(){
        return codeNum;
    }

    public int getRemainingUses(){
        return remainingUses;
    }

    public String getRemainingUsesString()
    {
        if (this.remainingUses == -1) return "PERMANENT";
        else return Integer.toString(this.remainingUses);
    }

    //public int getStatus(){ return isEnabled;}
    public String getStatus(){
        if (this.IsCodeEnabled()) return "HABILITAT";
        else return "DESHABILITAT";
    }

    public boolean getIsEnabled()
    {
        return this.isEnabled;
    }


    public boolean IsCodeEnabled(){ return (this.isEnabled); }

    public boolean IsPermCode(){
        return this.remainingUses == -1;
    }

    public void setCodeNum(String codeNum)
    {
        this.codeNum = codeNum;
    }

    public void setRemainingUses(int remainingUses){
        if (this.remainingUses != -1)
            this.remainingUses = remainingUses;
    }

    public void setEnabled(boolean enabled)
    {
        this.isEnabled = enabled;
    }

    public void changeState(){
        if (this.IsCodeEnabled()) this.isEnabled = false;
        else this.isEnabled = true;
    }

    public boolean equalsCode(Code c) {
        return (this.codeNum.equals(c.codeNum));
    }

    @Override
    public String toString() {
        return String.format(getCodeNum() + "       " + getRemainingUses() + "      " + getStatus());
    }
}
