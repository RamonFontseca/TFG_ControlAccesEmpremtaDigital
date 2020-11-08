package Model;

public class CodeUsages {
    private  String codeNumber;
    private int codeUsages;

    public CodeUsages(){
    }

    public CodeUsages(String codeNumber){
        this.codeNumber = codeNumber;
        this.codeUsages = 0;
    }

    public CodeUsages(String codeNumber, int codeUsages){
        this.codeNumber = codeNumber;
        this.codeUsages = codeUsages;
    }

    public String getCodeNumber()
    {
        return codeNumber;
    }

    public int getCodeUsages()
    {
        return codeUsages;
    }
}
