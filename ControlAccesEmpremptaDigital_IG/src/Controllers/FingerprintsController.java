package Controllers;

import Model.Fingerprint;
import java.util.ArrayList;

public class FingerprintsController {
    public ArrayList<Fingerprint> fingerPrintList;

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
}
