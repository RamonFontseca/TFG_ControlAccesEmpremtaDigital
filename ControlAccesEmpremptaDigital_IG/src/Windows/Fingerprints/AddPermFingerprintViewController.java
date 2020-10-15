package Windows.Fingerprints;

import Controllers.FingerprintsController;
import Controllers.PagesController;
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
import com.sun.javafx.fxml.builder.JavaFXImageBuilder;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.LinkedBlockingQueue;
import java.io.ByteArrayInputStream;
import javax.imageio.ImageIO;

public class AddPermFingerprintViewController {
    FingerprintsController fingerprintsController = new FingerprintsController();
    PagesController pagesController = new PagesController();

    @FXML private ImageView fingerPrintImage;

    public void InitData(FingerprintsController fp)
    {
        this.fingerprintsController = fp;
        InitFingerprintReader();
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
    }

    public void OnSettingsButtonClicked(MouseEvent mouseEvent) {
    }

    public void OnBackButtonClicked(MouseEvent mouseEvent) {
        pagesController.goToScreen(mouseEvent, pagesController.page_FingerprintsMenu);
    }
}
