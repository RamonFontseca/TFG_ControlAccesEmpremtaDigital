package Controllers;

import Model.Fingerprint;

import java.net.FileNameMap;
import java.util.ArrayList;

public class FingerprintsController {
    public ArrayList<Fingerprint> fingerPrintList;

    public FingerprintsController()
    {
        fingerPrintList = new ArrayList<>();
    }

    public void InitData()
    {
        fingerPrintList = new ArrayList<>();
    }

    public void LoadFingerPrints()
    {
        // TODO read from file
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
}
