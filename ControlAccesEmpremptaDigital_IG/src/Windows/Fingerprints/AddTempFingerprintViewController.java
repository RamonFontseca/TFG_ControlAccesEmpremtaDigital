package Windows.Fingerprints;

import Controllers.FingerprintsController;
import Controllers.PagesController;
import Model.Fingerprint;
import Reader.DPFPReader4500;
import Singleton.Singleton;
import Windows.Base.BaseFingerprintsController;
import com.digitalpersona.onetouch.*;
import com.digitalpersona.onetouch.capture.DPFPCapture;
import com.digitalpersona.onetouch.capture.DPFPCapturePriority;
import com.digitalpersona.onetouch.capture.event.DPFPDataEvent;
import com.digitalpersona.onetouch.capture.event.DPFPDataListener;
import com.digitalpersona.onetouch.capture.event.DPFPReaderStatusAdapter;
import com.digitalpersona.onetouch.capture.event.DPFPReaderStatusEvent;
import com.digitalpersona.onetouch.processing.DPFPEnrollment;
import com.digitalpersona.onetouch.processing.DPFPFeatureExtraction;
import com.digitalpersona.onetouch.processing.DPFPImageQualityException;
import com.digitalpersona.onetouch.readers.DPFPReaderDescription;
import com.digitalpersona.onetouch.readers.DPFPReadersCollection;
import com.digitalpersona.onetouch.verification.DPFPVerificationResult;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.LinkedBlockingQueue;

public class AddTempFingerprintViewController extends BaseFingerprintsController {
    DPFPReader4500 reader = Singleton.GetDPFPReader4500();

    private DPFPFeatureSet dpfpFeatureSet;


    @FXML private ImageView fingerPrintImage;
    @FXML private Button bttnCapture;
    @FXML private Button buttonSave;
    @FXML private TextField textCode;
    @FXML private ComboBox cmbRemainingUses;

    public void InitData()
    {
        InitializeUserLabel();

        valid = false;

        reader.InitReader();
        dpfpFeatureSet = null;

        InitComboBox();

        HideFingerprintImage();
        SetSaveButtonEnable(false);
        SetCaptureButtonEnable(true);
        SetTextFieldEnable(true);
        SetRemainingUsesEnable(true);
    }

    private void InitComboBox(){
        ObservableList<String> options = FXCollections.observableArrayList("5","10","15","20","25","50");
        cmbRemainingUses.setItems(options);
        cmbRemainingUses.setValue("5");
    }
/*
    public void OnCaptureButtonClicked(MouseEvent mouseEvent) {

        if (!ValidateView()) return;

        if (reader == null || reader.GetActiveReader() == null) {
            System.out.println("Reader no disponible");
            showInformationMessage("No hi ha cap lector disponible");
            return;
        }
        if (fingerprintsController.fingerPrintList == null)
            fingerprintsController.fingerPrintList = new ArrayList<>();

        showInformationMessage("Escaneja la teva empremta un total de 5 cops al tancar aquest missatge.");
        // FINGERPRINT READ
        try {
            DPFPTemplate template = reader.ReadFingerPrinters();

            Fingerprint fp = new Fingerprint(textCode.getText(),getRemainingUses(),true,template);
            fp.SetTemplate(template);

        // VERITIFACTION
        //try {
            DPFPSample sample = reader.getSample(reader.GetActiveReader().getSerialNumber(), "Scan your finger\n");
            if (sample == null)
                throw new Exception();

            DPFPFeatureExtraction featureExtractor = DPFPGlobal.getFeatureExtractionFactory().createFeatureExtraction();
            DPFPFeatureSet featureSet = featureExtractor.createFeatureSet(sample, DPFPDataPurpose.DATA_PURPOSE_VERIFICATION);

            boolean finded = false;
            for(Fingerprint f: fingerprintsController.getFingerprints())
            {
                DPFPVerificationResult result = reader.VerifyFingerprint(f.GetTemplate(), featureSet);
                if (result.isVerified()){
                    System.out.println("L'empremta existeix");
                    finded = true;
                    break;
                }
            }

            // SHOW RESULT
            if (!finded) {
                System.out.println("L'empremta no s'ha trobat");
                //fingerprintsController.fingerPrintList.add(fp);
                if (fingerprintsController.Add(fp)) {

                    showInformationMessage("Fingerprint saved!");
                    bttnCapture.setDisable(true);
                    fingerPrintImage.setOpacity(1.0);
                    valid = true;
                }
                else showAlertErrorMessage("Error al guardar!");
            }
            else {
                showInformationMessage("Fingerprint already exists!");
                // Capture button enabled false
                valid = false;
            }

        } catch (Exception e) {
            System.out.printf("Failed to perform verification.");
            showAlertErrorMessage("Failed to perform verification.");
        }
    }*/

    @Override
    protected int getRemainingUses(){
        return Integer.parseInt(cmbRemainingUses.getSelectionModel().getSelectedItem().toString());
    }

    @Override
    protected DPFPReader4500 GetReader()
    {
        return reader;
    }

    @Override
    protected void SetSaveButtonEnable(boolean enable)
    {
        buttonSave.setDisable(!enable);
    }

    @Override
    protected void SetCaptureButtonEnable(boolean enable)
    {
        bttnCapture.setDisable(!enable);
    }

    @Override
    protected void SetTextFieldEnable(boolean enable)
    {
        textCode.setDisable(!enable);
    }

    @Override
    protected void ShowFingerprintImage()
    {
        fingerPrintImage.setOpacity(1.0);
    }

    @Override
    protected void HideFingerprintImage()
    {
        fingerPrintImage.setOpacity(0.0);
    }

    @Override
    protected void SetRemainingUsesEnable(boolean enable)
    {
        cmbRemainingUses.setDisable(!enable);
    }
}
