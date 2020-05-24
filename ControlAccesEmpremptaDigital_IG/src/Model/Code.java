package Model;

public class Code {
    private String codeNum;
    private int remainingUses;
    private int isEnabled;

    public Code() {
        this.codeNum = null;
        this.remainingUses = 0;
        this.isEnabled = 0;
    }

    public Code(String codeNum, int remainingUses) {
        this.codeNum = codeNum;
        this.remainingUses = remainingUses;
        this.isEnabled = 1;
    }

    public Code(String codeNum, int remainingUses, int IsEnabled) {
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

    public String getStatus(){
        if (this.IsCodeEnabled()) return "HABILITAT";
        else return "DESHABILITAT";
    }

    public boolean IsCodeEnabled(){ return (this.isEnabled == 1); }

    public void setRemainingUses(int remainingUses){
        this.remainingUses = remainingUses;
    }

    public boolean equalsCode(Code c) {
        return (this.codeNum.equals(c.codeNum));
    }

    @Override
    public String toString() {
        return String.format(getCodeNum() + "       " + getRemainingUses() + "      " + getStatus());
    }
}
