package Controllers;

import Model.Code;
import Model.Language;
import Model.PhoneNumber;
import Singleton.Singleton;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class PhoneNumbersController {
    private ArrayList<PhoneNumber> phoneNumbersList;

    public PhoneNumbersController()
    {
        phoneNumbersList = new ArrayList<>();
        InitData();
    }
    public void InitData()
    {
        LoadPhoneNumbers();
    }

    public void LoadPhoneNumbers()
    {
        LoadPhoneNumbersFromFile();
    }

    public void LoadPhoneNumbersFromFile()
    {
        try {
            File file = new File(Singleton.GetFilesController().phoneNumbersFilePath);
            Singleton.GetFilesController();
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String data = scanner.nextLine();
                System.out.println(data);
                var a = data.split(",");
                String part1 = a[0];
                String part2 = a[1];
                PhoneNumber pN = new PhoneNumber(part1, Boolean.parseBoolean(part2));
                phoneNumbersList.add(pN);
            }
            scanner.close();
        } catch (Exception e)
        {
            e.printStackTrace();
        }
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
