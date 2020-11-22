package Controllers;

import Model.Fingerprint;
import Model.PhoneNumber;
import Reader.DPFPReader4500;
import Singleton.Singleton;
import Windows.Base.BaseFingerprintsController;
import com.digitalpersona.onetouch.DPFPDataPurpose;
import com.digitalpersona.onetouch.DPFPFeatureSet;
import com.digitalpersona.onetouch.DPFPGlobal;
import com.digitalpersona.onetouch.DPFPSample;
import com.digitalpersona.onetouch.processing.DPFPFeatureExtraction;
import com.digitalpersona.onetouch.processing.DPFPImageQualityException;
import com.digitalpersona.onetouch.verification.DPFPVerificationResult;
import javafx.util.Pair;

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


    public Pair<String, Boolean> ValidateFingerprint() {
        DPFPReader4500 reader = Singleton.GetDPFPReader4500();
        reader.InitReader();

        try{
            return verifyFingerprint(reader);
        }
        catch (Exception ex)
        {
            return new Pair("",false);
        }
    }

    protected Pair<String, Boolean> verifyFingerprint(DPFPReader4500 reader) throws DPFPImageQualityException, InterruptedException
    {
        DPFPSample sample = reader.getSample(reader.GetActiveReader().getSerialNumber(), "Scan your finger\n");
        if (sample == null)
            return new Pair("",false);

        DPFPFeatureExtraction featureExtractor = DPFPGlobal.getFeatureExtractionFactory().createFeatureExtraction();
        DPFPFeatureSet featureSet = featureExtractor.createFeatureSet(sample, DPFPDataPurpose.DATA_PURPOSE_VERIFICATION);

        for(Fingerprint f: getFingerprints())
        {
            DPFPVerificationResult result = reader.VerifyFingerprint(f.GetTemplate(), featureSet);
            if (result.isVerified()){
                System.out.println("L'empremta existeix");
                f.SetRemainingUses(f.getRemainingUses() - 1);
                if (f.getEnabled()){
                    return new Pair(f.getName(),true);
                }
                return new Pair("",false);
            }
        }
        return new Pair("",false);
    }
}
