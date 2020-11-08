package Model;

public class Language {
    private String code;
    private String description;
    private boolean isEnabled;
    private String enabledDescription;

    public Language()
    {
    }

    public Language(String code, String description)
    {
        this.code = code;
        this.description = description;
        this.isEnabled = true;
        setEnabledDescription(isEnabled);
    }

    public Language(String code, String description, boolean isEnabled)
    {
        this.code = code;
        this.description = description;
        this.isEnabled = isEnabled;
        setEnabledDescription(isEnabled);
    }

    public String getDescription()
    {
        return  this.description;
    }

    public String getEnabledDescription()
    {
        return this.enabledDescription;
    }

    public boolean IsEnabled()
    {
        return isEnabled;
    }

    public void setIsEnabled(boolean enabled)
    {
        this.isEnabled = enabled;
    }

    public void setEnabledDescription(boolean isEnabled)
    {
        if(isEnabled) this.enabledDescription = "HABILITAT";
        else this.enabledDescription = "DESHABILITAT";
    }


    public void changeState()
    {
        this.isEnabled = !this.isEnabled;
    }

    @Override
    public boolean equals(Object object)
    {
        if (object == null) return false;
        if (object.getClass() != this.getClass()) return false;

        final Language language= (Language) object;
        if (this.description.equals(language.description) &&
            this.code.equals(language.code) &&
            this.isEnabled == language.isEnabled ){
            return  true;
        }
        return  false;
    }
}
