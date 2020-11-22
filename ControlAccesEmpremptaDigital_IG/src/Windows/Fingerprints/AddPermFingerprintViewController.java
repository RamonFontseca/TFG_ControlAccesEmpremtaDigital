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

public class AddPermFingerprintViewController extends BaseFingerprintsController {

    DPFPReader4500 reader = Singleton.GetDPFPReader4500();

    private DPFPFeatureSet dpfpFeatureSet;

    @FXML private ImageView fingerPrintImage;
    @FXML private Button bttnCapture;
    @FXML private Button buttonSave;
    @FXML private TextField textCode;

    public void InitData()
    {
        InitializeUserLabel();

        valid = false;

        reader.InitReader();
        dpfpFeatureSet = null;

        HideFingerprintImage();
        SetSaveButtonEnable(false);
        SetCaptureButtonEnable(true);
        SetTextFieldEnable(true);
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

}
