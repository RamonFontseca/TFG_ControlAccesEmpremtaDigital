package Model;

import Controllers.PagesController;
import Controllers.PhoneNumbersController;
import Singleton.Singleton;

public class PhoneNumber {
    private String phoneNumber;
    private boolean isEnabled;
    private String enabledDescription;

    public PhoneNumber()
    {
        phoneNumber = null;
        isEnabled = false;
        setEnableDescription(isEnabled);
    }

    public PhoneNumber(String phoneNumber, boolean isEnabled)
    {
        this.phoneNumber = phoneNumber;
        this.isEnabled = isEnabled;
        setEnableDescription(isEnabled);
    }

    public String getPhoneNumber()
    {
        return phoneNumber;
    }

    public boolean getIsEnabled()
    {
        return isEnabled;
    }

    public void setEnableDescription(boolean isEnabled)
    {
        if(isEnabled) this.enabledDescription = "HABILITAT";
        else this.enabledDescription = "DESHABILITAT";
    }

    public String getEnabledDescription()
    {
        return this.enabledDescription;
    }

    public void changeState()
    {
        this.isEnabled = !this.isEnabled;
    }

    public boolean IsEnabled()
    {
        return isEnabled;
    }

    @Override
    public boolean equals(Object object)
    {
        if (object == null) return false;
        if (object.getClass() != this.getClass()) return false;

        final PhoneNumber pn = (PhoneNumber) object;
        return (this.phoneNumber.equals(pn.phoneNumber));
    }
}
