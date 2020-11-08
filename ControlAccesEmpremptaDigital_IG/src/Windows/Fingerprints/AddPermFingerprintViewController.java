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
import com.digitalpersona.onetouch.verification.DPFPVerification;
import com.digitalpersona.onetouch.verification.DPFPVerificationResult;
import com.sun.javafx.fxml.builder.JavaFXImageBuilder;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.concurrent.LinkedBlockingQueue;
import java.io.ByteArrayInputStream;
import javax.imageio.ImageIO;

public class AddPermFingerprintViewController {
    FingerprintsController fingerprintsController = Singleton.GetFingerprintsController();
    PagesController pagesController = Singleton.GetPagesController();
    DPFPReader4500 reader = new DPFPReader4500();

    private DPFPFeatureSet dpfpFeatureSet;

    private boolean valid;

    @FXML private ImageView fingerPrintImage;
    @FXML private Button bttnCapture;
    @FXML private TextField textCode;

    public void InitData(FingerprintsController fpController)
    {
        bttnCapture.setDisable(false);
        valid = false;

        //this.fingerprintsController = fpController;
        //InitFingerprintReader();
        reader.InitReader();
        dpfpFeatureSet = null;/*
        DPFPTemplate template = reader.ReadFingerPrinters();
        Fingerprint fp = new Fingerprint();
        fp.SetRemainingUses(10);
        fp.SetTemplate(template);
        fp.SetName("Ramon");
        fingerprintsController.fingerPrintList.add(fp);*/
        fingerPrintImage.setOpacity(0.0);
    }

    public void OnSettingsButtonClicked(MouseEvent mouseEvent) {
        pagesController.goToSettingsScreenFrom(mouseEvent, pagesController.page_Settings, pagesController.page_FingerprintsMenu);
    }

    public void OnBackButtonClicked(MouseEvent mouseEvent) {
        pagesController.goToScreen(mouseEvent, pagesController.page_FingerprintsMenu);
    }


    public void InitFingerprintReader()
    {
        DPFPReadersCollection readers = DPFPGlobal.getReadersFactory().getReaders();
        if (readers == null || readers.size() == 0) {
            System.out.printf("There are no readers available.\n");
            //return;
        }
        System.out.printf("Available readers:\n");
        for (DPFPReaderDescription readerDescription : readers) {
            System.out.println(readerDescription.getSerialNumber());
            System.out.println(readerDescription.getProductName());
        }
        String activeReader = "";
        if (readers.size() > 0){
            activeReader = readers.get(0).getSerialNumber();
        }
        if (!activeReader.equals("")) {
            System.out.println("Reader active:" + activeReader + "\n");
            System.out.printf("Performing fingerprint enrollment...\n");

            DPFPFeatureExtraction featureExtractor = DPFPGlobal.getFeatureExtractionFactory().createFeatureExtraction();
            DPFPEnrollment enrollment = DPFPGlobal.getEnrollmentFactory().createEnrollment();
            DPFPSample sample = null;
            while (enrollment.getFeaturesNeeded() > 0)
            {
                try {
                    sample = getSample(activeReader,
                        String.format("Scan your finger (%d remaining)\n", enrollment.getFeaturesNeeded()));

                    if (sample == null)
                        continue;

                    DPFPFeatureSet featureSet;
                    try {
                        featureSet = featureExtractor.createFeatureSet(sample, DPFPDataPurpose.DATA_PURPOSE_ENROLLMENT);
                    } catch (DPFPImageQualityException e) {
                        System.out.printf("Bad image quality: \"%s\". Try again. \n", e.getCaptureFeedback().toString());
                        continue;
                    }

                    enrollment.addFeatures(featureSet);

                } catch (DPFPImageQualityException e) {
                    System.out.printf("Failed to enroll the finger.\n");
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

            }
            DPFPTemplate template = enrollment.getTemplate();
            var image = convertSampleToBitmap(sample);
            //InputStream in = new ByteArrayInputStream(image);
            JLabel picutre = new JLabel();

            picutre.setIcon(new ImageIcon(
                    image.getScaledInstance(240, 280, Image.SCALE_DEFAULT)));


            /*BufferedImage ibi = null;
            try {
                ibi = ImageIO.read(in);
            } catch (IOException e) {
                e.printStackTrace();
            }
            var a  = new ImageIcon(image);
            WritableImage fImage = null;
            Image joquese = SwingFXUtils.toFXImage(ibi, fImage);
            fingerPrintImage.setImage(joquese);*/

            /*var bImage = SwingFXUtils.toFXImage(createImageFromBytes(image),null);
            BufferedImage bImage = SwingFXUtils.fromFXImage(a.getImage(), null);
            ByteArrayOutputStream s = new ByteArrayOutputStream();
            ImageIO.write(bImage, "png", s);
            byte[] res  = s.toByteArray();
            s.close(); //especially if you are using a different output stream.*/
        }
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
        /*try {
            DPFPSample sample = getSample(reader.GetActiveReader().getSerialNumber(), "Scan your finger\n");
            if (sample == null)
                throw new Exception();

            DPFPFeatureExtraction featureExtractor = DPFPGlobal.getFeatureExtractionFactory().createFeatureExtraction();
            DPFPFeatureSet featureSet = featureExtractor.createFeatureSet(sample, DPFPDataPurpose.DATA_PURPOSE_VERIFICATION);

            boolean finded = false;
            for(Fingerprint fp: fingerprintsController.fingerPrintList)
            {
                DPFPVerificationResult result = reader.VerifyFingerprint(fp.GetTemplate(), featureSet);
                if (result.isVerified()){
                    System.out.println("L'empremta existeix");
                    finded = true;
                    break;
                }
            }

            if (!finded) System.out.println("L'empremta no s'ha trobat");

        } catch (Exception e) {
            System.out.printf("Failed to perform verification.");
        }*/
        if (valid)
        {
            pagesController.goToScreen(mouseEvent, pagesController.page_FingerprintsMenu);
        }
    }

    public void OnCaptureButtonClicked(MouseEvent mouseEvent) {

       /*
        LA IDEA PER FER-HO BÉ ES LA SEGUENT:
        FER SEGUIT EL CAPTURAR (4 TIMES) I EL VERIFY (1 TIME)
        SI L'EMPREMTA JA EXISTEIX -> NO FER RES I NO GUARDAR
        SI L'EMPREMTA NO EXISTEIX -> GUARDAR L'EMPREMTA LLEGIDA PER PRIMER COP (4 TIMES)

        */
        if (reader == null || reader.GetActiveReader() == null) {
            System.out.println("Reader no disponible");
            showAlertInfoMessage("No hi ha cap lector disponible");
            return;
        }
        if (fingerprintsController.fingerPrintList == null)
            fingerprintsController.fingerPrintList = new ArrayList<>();

        // FINGERPRINT READ
        try {
            DPFPTemplate template = reader.ReadFingerPrinters();
            /*dpfpFeatureSet = reader.ReadFingerPrinters2();
            DPFPTemplate template = reader.ConvertFeatureSetToTemplate(dpfpFeatureSet);*/

            Fingerprint fp = new Fingerprint();
            fp.SetRemainingUses(10);
            fp.SetTemplate(template);
            if (textCode.getText() != null && textCode.getText() != "") {
                fp.SetName(textCode.getText());
            } else fp.SetName("NomIndefinit");

        // VERITIFACTION
        //try {
            DPFPSample sample = getSample(reader.GetActiveReader().getSerialNumber(), "Scan your finger\n");
            if (sample == null)
                throw new Exception();

            DPFPFeatureExtraction featureExtractor = DPFPGlobal.getFeatureExtractionFactory().createFeatureExtraction();
            DPFPFeatureSet featureSet = featureExtractor.createFeatureSet(sample, DPFPDataPurpose.DATA_PURPOSE_VERIFICATION);

            boolean finded = false;
            for(Fingerprint f: fingerprintsController.fingerPrintList)
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
                fingerprintsController.fingerPrintList.add(fp);

                showAlertInfoMessage("Fingerprint saved!");
                bttnCapture.setDisable(true);
                fingerPrintImage.setOpacity(1.0);
                valid = true;
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
}
