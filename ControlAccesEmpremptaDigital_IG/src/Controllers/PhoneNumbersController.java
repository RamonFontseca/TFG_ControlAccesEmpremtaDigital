package Controllers;

import Model.Code;
import Model.PhoneNumber;

import java.util.ArrayList;

public class PhoneNumbersController {
    private ArrayList<PhoneNumber> phoneNumbersList;

    public PhoneNumbersController()
    {
        phoneNumbersList = new ArrayList<>();
    }

    public boolean Add(PhoneNumber phoneNumber)
    {
        if (phoneNumber != null && phoneNumber.getPhoneNumber().length() > 0 && !phoneNumbersList.contains(phoneNumber)){

            phoneNumbersList.add(phoneNumber);
            return true;
        }
        return false;
    }

    public void Update(PhoneNumber phoneNumber)
    {
        if (phoneNumber != null && phoneNumber.getPhoneNumber().length() > 0 && phoneNumbersList.contains(phoneNumber)){
            int index = phoneNumbersList.indexOf(phoneNumber);
            if (index != -1)
            {
                phoneNumbersList.set(index, phoneNumber);
                phoneNumbersList.get(index).setEnableDescription(phoneNumber.IsEnabled());
            }
        }
    }

    public void Delete(PhoneNumber phoneNumber)
    {
        if (phoneNumber != null && phoneNumber.getPhoneNumber().length() > 0 && phoneNumbersList.contains(phoneNumber)){
            int index = phoneNumbersList.indexOf(phoneNumber);
            if (index != -1)
            {
                phoneNumbersList.remove(index);
            }
        }
    }

    public ArrayList<PhoneNumber> GetPhoneNumbers(){
        return phoneNumbersList;
    }

}
