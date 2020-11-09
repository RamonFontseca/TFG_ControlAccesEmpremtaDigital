package Windows.Base;

import Controllers.FilesController;
import Controllers.FingerprintsController;
import Encrypter.Encrypter;
import Model.Fingerprint;
import Reader.DPFPReader4500;
import Singleton.Singleton;
import com.digitalpersona.onetouch.*;
import com.digitalpersona.onetouch.processing.DPFPFeatureExtraction;
import com.digitalpersona.onetouch.processing.DPFPImageQualityException;
import com.digitalpersona.onetouch.verification.DPFPVerificationResult;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import javax.print.attribute.standard.MediaSize;
import java.nio.charset.Charset;
import java.util.ArrayList;

public abstract class BaseFingerprintsController extends BaseController{

    protected FingerprintsController fingerprintsController = Singleton.GetFingerprintsController();
    protected boolean valid = false;

    @FXML private TextField textCode;

    @FXML protected TableView<Fingerprint> tableCodes;
    @FXML protected TableColumn<Fingerprint, String> columnCode;
    @FXML protected TableColumn<Fingerprint, Integer> columnUses;
    @FXML protected TableColumn<Fingerprint, String> columnState;

    // Methods
    @Override
    public void OnSettingsButtonClicked(MouseEvent mouseEvent) {
        pagesController.goToSettingsScreenFrom(mouseEvent, pagesController.page_Settings, pagesController.page_FingerprintsMenu);
    }

    @Override
    public void OnBackButtonClicked(MouseEvent mouseEvent) {
        pagesController.goToScreen(mouseEvent, pagesController.page_FingerprintsMenu);
    }

    @Override
    public void OnSaveButtonClicked(MouseEvent mouseEvent) {
        if (valid)
            pagesController.goToScreen(mouseEvent, pagesController.page_FingerprintsMenu);
    }

    protected boolean ValidateView()
    {
        if (textCode.getText() == null || textCode.getText().isEmpty() || textCode.getText().isBlank())
        {
            showInformationMessage("El camp 'NOM' és obligatori");
            return false;
        }

        if (fingerprintsController.ExistsUserName(textCode.getText())){
            showAlertErrorMessage("Ja existeix una empremta amb aquest nom");
            return false;
        }

        return true;
    }

    protected int getRemainingUses()
    {
        return -1;
    }

    protected DPFPReader4500 GetReader()
    {
        return null;
    }

    protected void SetSaveButtonEnable(boolean enable)
    {
    }

    protected void SetCaptureButtonEnable(boolean enable)
    {
    }

    protected void SetTextFieldEnable(boolean enable)
    {

    }

    protected void SetRemainingUsesEnable(boolean enable)
    {

    }

    protected void HideFingerprintImage()
    {
    }

    protected void ShowFingerprintImage()
    {
    }

    public void OnCaptureButtonClicked(MouseEvent mouseEvent) {

        if (!ValidateView()) return;

        if (fingerprintsController.fingerPrintList == null)
            fingerprintsController.fingerPrintList = new ArrayList<>();

        DPFPReader4500 reader = GetReader();
        if (reader == null || reader.GetActiveReader() == null) {
            showInformationMessage("No hi ha cap lector disponible");
            return;
        }

        showInformationMessage("Escaneja la teva empremta un total de 5 cops al tancar aquest missatge.");
        try {
            // FINGERPRINT READ
            DPFPTemplate template = reader.ReadFingerPrinters();

            // Fingerprint creation
            Fingerprint fingerprint = new Fingerprint(textCode.getText(),getRemainingUses(),true,template);
            fingerprint.SetTemplate(template);

            // VERITIFACTION
            boolean fingerprintFound = verifyFingerprint(reader);
            // SAVE AND SHOW MESSAGE
            saveFingerprint(fingerprintFound, fingerprint);
        } catch (Exception e) {
            showAlertErrorMessage("No s'ha pogut escanejar l'empremta.");
        }
    }

    private boolean verifyFingerprint(DPFPReader4500 reader) throws DPFPImageQualityException, InterruptedException
    {
        DPFPSample sample = reader.getSample(reader.GetActiveReader().getSerialNumber(), "Scan your finger\n");
        if (sample == null)
            return false;

        DPFPFeatureExtraction featureExtractor = DPFPGlobal.getFeatureExtractionFactory().createFeatureExtraction();
        DPFPFeatureSet featureSet = featureExtractor.createFeatureSet(sample, DPFPDataPurpose.DATA_PURPOSE_VERIFICATION);

        for(Fingerprint f: fingerprintsController.getFingerprints())
        {
            DPFPVerificationResult result = reader.VerifyFingerprint(f.GetTemplate(), featureSet);
            if (result.isVerified()){
                System.out.println("L'empremta existeix");
                return true;
            }
        }
        return false;
    }

    private boolean saveFingerprint(boolean finded, Fingerprint fp)
    {
        if (fp == null) return false;
        if (!finded) {
            if (fingerprintsController.Add(fp)) {
                valid = true;
                showInformationMessage("Fingerprint saved!");
                SetCaptureButtonEnable(false);
                SetTextFieldEnable(false);
                SetSaveButtonEnable(true);
                SetRemainingUsesEnable(false);
                ShowFingerprintImage();
                return true;
            }
            else showAlertErrorMessage("Error al guardar!");
        }
        else {
            showAlertErrorMessage("Fingerprint already exists!");
            valid = false;
        }
        return false;
    }
}
