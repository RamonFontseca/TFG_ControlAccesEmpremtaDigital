package Windows.Fingerprints;

import Controllers.FingerprintsController;
import Controllers.PagesController;
import Model.Fingerprint;
import Reader.DPFPReader4500;
import Singleton.Singleton;
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

public class AddTempFingerprintViewController {
    FingerprintsController fingerprintsController = Singleton.GetFingerprintsController();
    PagesController pagesController = Singleton.GetPagesController();
    DPFPReader4500 reader = new DPFPReader4500();

    private DPFPFeatureSet dpfpFeatureSet;

    private boolean valid;

    @FXML private ImageView fingerPrintImage;
    @FXML private Button bttnCapture;
    @FXML private TextField textCode;
    @FXML private ComboBox cmbRemainingUses;

    public void InitData(FingerprintsController fpController)
    {
        bttnCapture.setDisable(false);
        valid = false;

        //this.fingerprintsController = fpController;
        //InitFingerprintReader();
        reader.InitReader();
        dpfpFeatureSet = null;
        fingerPrintImage.setOpacity(0.0);

        InitComboBox();
    }

    private void InitComboBox(){
        ObservableList<String> options = FXCollections.observableArrayList("5","10","15","20","25","50");
        cmbRemainingUses.setItems(options);
        cmbRemainingUses.setValue("5");
    }

    public void OnSettingsButtonClicked(MouseEvent mouseEvent) {
        pagesController.goToSettingsScreenFrom(mouseEvent, pagesController.page_Settings, pagesController.page_FingerprintsMenu);
    }

    public void OnBackButtonClicked(MouseEvent mouseEvent) {
        pagesController.goToScreen(mouseEvent, pagesController.page_FingerprintsMenu);
    }

    private BufferedImage createImageFromBytes(byte[] imageData) {
        ByteArrayInputStream bais = new ByteArrayInputStream(imageData);
        try {
            return ImageIO.read(bais);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    protected Image convertSampleToBitmap(DPFPSample sample) {
        return DPFPGlobal.getSampleConversionFactory().createImage(sample);
        //return DPFPGlobal.getSampleConversionFactory().convertToANSI381(sample);
    }

    private DPFPSample getSample(String activeReader, String prompt)
            throws InterruptedException
    {
        final LinkedBlockingQueue<DPFPSample> samples = new LinkedBlockingQueue<DPFPSample>();
        DPFPCapture capture = DPFPGlobal.getCaptureFactory().createCapture();
        capture.setReaderSerialNumber(activeReader);
        capture.setPriority(DPFPCapturePriority.CAPTURE_PRIORITY_LOW);
        capture.addDataListener(new DPFPDataListener()
        {
            public void dataAcquired(DPFPDataEvent e) {
                if (e != null && e.getSample() != null) {
                    try {
                        samples.put(e.getSample());
                    } catch (InterruptedException e1) {
                        e1.printStackTrace();
                    }
                }
            }
        });
        capture.addReaderStatusListener(new DPFPReaderStatusAdapter()
        {
            int lastStatus = DPFPReaderStatusEvent.READER_CONNECTED;
            public void readerConnected(DPFPReaderStatusEvent e) {
                if (lastStatus != e.getReaderStatus())
                    System.out.println("Reader is connected");
                lastStatus = e.getReaderStatus();
            }
            public void readerDisconnected(DPFPReaderStatusEvent e) {
                if (lastStatus != e.getReaderStatus())
                    System.out.println("Reader is disconnected");
                lastStatus = e.getReaderStatus();
            }

        });
        try {
            capture.startCapture();
            System.out.print(prompt);
            return samples.take();
        } catch (RuntimeException e) {
            System.out.printf("Failed to start capture. Check that reader is not used by another application.\n");
            throw e;
        } finally {
            capture.stopCapture();
        }
    }

    public void OnSaveButtonClicked(MouseEvent mouseEvent) {
        if (valid)
        {
            pagesController.goToScreen(mouseEvent, pagesController.page_FingerprintsMenu);
        }
    }

    public void OnCaptureButtonClicked(MouseEvent mouseEvent) {

        if (!ValidateView()) return;

        if (reader == null || reader.GetActiveReader() == null) {
            System.out.println("Reader no disponible");
            showAlertInfoMessage("No hi ha cap lector disponible");
            return;
        }
        if (fingerprintsController.fingerPrintList == null)
            fingerprintsController.fingerPrintList = new ArrayList<>();

        showAlertInfoMessage("Escaneja la teva empremta un total de 5 cops al tancar aquest missatge.");
        // FINGERPRINT READ
        try {
            DPFPTemplate template = reader.ReadFingerPrinters();

            Fingerprint fp = new Fingerprint(textCode.getText(),getRemainingUses(),true,template);
            fp.SetTemplate(template);

        // VERITIFACTION
        //try {
            DPFPSample sample = getSample(reader.GetActiveReader().getSerialNumber(), "Scan your finger\n");
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

                    showAlertInfoMessage("Fingerprint saved!");
                    bttnCapture.setDisable(true);
                    fingerPrintImage.setOpacity(1.0);
                    valid = true;
                }
                else showAlertErrorMessage("Error al guardar!");
            }
            else {
                showAlertInfoMessage("Fingerprint already exists!");
                // Capture button enabled false
                valid = false;
            }

        } catch (Exception e) {
            System.out.printf("Failed to perform verification.");
            showAlertErrorMessage("Failed to perform verification.");
        }
    }

    private int getRemainingUses(){
        return Integer.parseInt(cmbRemainingUses.getSelectionModel().getSelectedItem().toString());
    }

    private boolean ValidateView()
    {
        if (textCode.getText() == null || textCode.getText().isEmpty() || textCode.getText().isBlank())
        {
            showAlertInfoMessage("El camp 'NOM' és obligatori");
            return false;
        }
        return true;
    }

    private void showAlertErrorMessage(String error) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(error);

        alert.showAndWait();
    }

    private void showAlertInfoMessage(String error) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(null);
        alert.setContentText(error);

        alert.showAndWait();
    }

    private Alert CreateAlertInfoMessage(String error)
    {
        Alert alert = new Alert(Alert.AlertType.INFORMATION,error ,ButtonType.OK);
        alert.setTitle("Information");
        //alert.setHeaderText(null);
        alert.setContentText(error);

        return alert;
    }

    private void SetAlertContent(Alert alert, String error)
    {
        alert.setContentText(error);
    }
}
