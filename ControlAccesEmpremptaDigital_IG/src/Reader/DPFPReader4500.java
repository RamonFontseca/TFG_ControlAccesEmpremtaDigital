package Reader;

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
import javafx.scene.control.Alert;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.LinkedBlockingQueue;

public class DPFPReader4500 {

    DPFPReaderDescription activeReader;
    DPFPReadersCollection readers;


    public DPFPReader4500() {
        activeReader = null;
        readers = null;
    }

    public void InitReader() {
        readers = GetAvailableReaders();
        activeReader = GetActiveReader();
    }

    public DPFPReadersCollection GetAvailableReaders()
    {
        return DPFPGlobal.getReadersFactory().getReaders();
    }

    public DPFPReaderDescription GetActiveReader()
    {
        if (readers != null && readers.size() > 0)
            return readers.get(0);
        else  return null;
    }

    public DPFPFeatureExtraction CreateFutureExtraction(){
        return DPFPGlobal.getFeatureExtractionFactory().createFeatureExtraction();
    }

    public DPFPEnrollment GetEnroll()
    {
        return DPFPGlobal.getEnrollmentFactory().createEnrollment();
    }

    public DPFPTemplate ReadFingerPrinters()
    {
        DPFPTemplate template = null;

        DPFPFeatureExtraction featureExtractor = CreateFutureExtraction();
        DPFPEnrollment enrollment = GetEnroll();
        if (enrollment == null & featureExtractor == null)
            return null;

        DPFPSample sample = null;

        while (enrollment.getFeaturesNeeded() > 0) {
            try {
                // Get Sample
                sample = getSample(activeReader.getSerialNumber(), String.format("Scan your finger (%d remaining)\n", enrollment.getFeaturesNeeded()));
                if (sample == null)
                    continue;

                // Get FeatureSet
                DPFPFeatureSet featureSet;
                try {
                    featureSet = featureExtractor.createFeatureSet(sample, DPFPDataPurpose.DATA_PURPOSE_ENROLLMENT);
                } catch (DPFPImageQualityException e) {
                    System.out.printf("Bad image quality: \"%s\". Try again. \n", e.getCaptureFeedback().toString());
                    continue;
                }

                // Add FeatureSet to Enrollment
                enrollment.addFeatures(featureSet);
                System.out.printf("Escaneja l'emprempta " + enrollment.getFeaturesNeeded() + " cops.");
            }
            catch (DPFPImageQualityException e) {
                System.out.printf("Failed to enroll the finger.\n");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        template= enrollment.getTemplate();

        return template;
    }

    public DPFPTemplate ConvertFeatureSetToTemplate(DPFPFeatureSet featureSet)
    {
        DPFPEnrollment enrollment = GetEnroll();
        try {
            enrollment.addFeatures(featureSet);
        } catch (DPFPImageQualityException e) {
            e.printStackTrace();
        }
        return enrollment.getTemplate();
    }

    public DPFPFeatureSet ReadFingerPrinters2()
    {
        DPFPTemplate template = null;
        DPFPFeatureSet featureSet = null;

        DPFPFeatureExtraction featureExtractor = CreateFutureExtraction();
        DPFPEnrollment enrollment = GetEnroll();
        if (enrollment == null & featureExtractor == null)
            return null;

        DPFPSample sample = null;

        while (enrollment.getFeaturesNeeded() > 0) {
            try {
                // Get Sample
                sample = getSample(activeReader.getSerialNumber(), String.format("Scan your finger (%d remaining)\n", enrollment.getFeaturesNeeded()));
                if (sample == null)
                    continue;

                // Get FeatureSet
                try {
                    featureSet = featureExtractor.createFeatureSet(sample, DPFPDataPurpose.DATA_PURPOSE_ENROLLMENT);
                } catch (DPFPImageQualityException e) {
                    System.out.printf("Bad image quality: \"%s\". Try again. \n", e.getCaptureFeedback().toString());
                    continue;
                }

                // Add FeatureSet to Enrollment
                enrollment.addFeatures(featureSet);

            }
            catch (DPFPImageQualityException e) {
                System.out.printf("Failed to enroll the finger.\n");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        template= enrollment.getTemplate();

        return featureSet;
    }


    public DPFPVerificationResult VerifyFingerprint(DPFPTemplate template, DPFPFeatureSet featureSet){
        // Template is the fingerprint saved to compare.
        // FeatureSet is the set of samples taken from the reader that wants to compare.
        DPFPVerification matcher = DPFPGlobal.getVerificationFactory().createVerification();
        matcher.setFARRequested(DPFPVerification.MEDIUM_SECURITY_FAR);

        return  matcher.verify(featureSet, template);
    }


    private DPFPSample getSample(String activeReader, String prompt) throws InterruptedException
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
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (sample == null)
                    continue;

                DPFPFeatureSet featureSet;
                try {
                    featureSet = featureExtractor.createFeatureSet(sample, DPFPDataPurpose.DATA_PURPOSE_ENROLLMENT);
                } catch (DPFPImageQualityException e) {
                    System.out.printf("Bad image quality: \"%s\". Try again. \n", e.getCaptureFeedback().toString());
                    continue;
                }

                try {
                    enrollment.addFeatures(featureSet);
                } catch (DPFPImageQualityException e) {
                    e.printStackTrace();
                }
            }
            DPFPTemplate template = enrollment.getTemplate();

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

    private void showAlertInfoMessage(String error) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(null);
        alert.setContentText(error);

        alert.show();
    }

    private Alert CreateAlertInfoMessage(String error)
    {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
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
