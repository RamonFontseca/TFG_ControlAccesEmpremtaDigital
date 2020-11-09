package Controllers;

import Model.Fingerprint;
import Model.PhoneNumber;
import Singleton.Singleton;
import com.digitalpersona.onetouch.DPFPGlobal;

import java.io.File;
import java.net.FileNameMap;
import java.util.ArrayList;
import java.util.Scanner;

public class FingerprintsController {
    public ArrayList<Fingerprint> fingerPrintList;

    public FingerprintsController()
    {
        InitData();
    }

    public void InitData()
    {
        fingerPrintList = new ArrayList<>();
        LoadFingerPrints();
    }

    public void LoadFingerPrints()
    {
        LoadFingerPrintsFromFile();
    }

    private void LoadFingerPrintsFromFile()
    {
        try {
            File file = new File(Singleton.GetFilesController().fingerprintsFilePath);
            Singleton.GetFilesController();
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String data = scanner.nextLine();
                System.out.println(data);
                var a = data.split(",");
                String part1 = a[0];
                String part2 = a[1];
                String part3 = a[2];
                byte[] part4 = a[3].getBytes();
                var template = DPFPGlobal.getTemplateFactory().createTemplate();
                template.deserialize(part4);
                Fingerprint fingerprint = new Fingerprint(part1,Integer.parseInt(part2),Boolean.parseBoolean(part3),template);
                fingerPrintList.add(fingerprint);
            }
            scanner.close();
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public void SaveFingerprints()
    {
        // TODO write to file
    }

    public void SaveLastFingerprint()
    {
        // Todo write to file last fingerprint on array
    }

    public boolean Add(Fingerprint f)
    {
        if (f != null && !f.getName().isEmpty() && f.getName().length() > 0 && !fingerPrintList.contains(f)){
            fingerPrintList.add(f);
            return true;
        }
        return false;
    }

    public void Update(Fingerprint f)
    {
        if (f != null && !f.getName().isEmpty() && f.getName().length() > 0 && fingerPrintList.contains(f)){
            int index = fingerPrintList.indexOf(f);
            if (index != -1)
            {
                fingerPrintList.set(index, f);
            }
        }
    }

    public void Delete(Fingerprint f)
    {
        if (f != null && !f.getName().isEmpty() && f.getName().length() > 0 && fingerPrintList.contains(f)){
            int index = fingerPrintList.indexOf(f);
            if (index != -1)
            {
                fingerPrintList.remove(index);
            }
        }
    }

    public ArrayList<Fingerprint> getFingerprints()
    {
        return fingerPrintList;
    }

    public boolean ExistsUserName(String username)
    {
        for (Fingerprint fp: fingerPrintList) {
            if (fp.getName().equals(username)){
                return true;
            }
        }
        return false;
    }
}
