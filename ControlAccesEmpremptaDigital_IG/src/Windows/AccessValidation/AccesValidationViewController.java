package Windows.AccessValidation;

import Controllers.CodesController;
import Controllers.FingerprintsController;
import Controllers.PagesController;
import Model.Fingerprint;
import Model.PhoneNumber;
import Reader.DPFPReader4500;
import Singleton.Singleton;
import Windows.Base.BaseFingerprintsController;
import Windows.Base.BaseNumericController;
import Windows.Base.BasePhonesController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

public class AccesValidationViewController extends BaseNumericController {

    CodesController codesController = Singleton.GetCodesController();
    FingerprintsController fingerprintsController = Singleton.GetFingerprintsController();

    @FXML private ImageView bttnFingerprint;
    @FXML private Button bttnEnterFingerprint;
    //private Image fingerprintNormal = new Image("../../../logos/fingerprint.png");
    //private Image fingerprintRed = new Image("../../../logos/fingerprint-red.png");

    @Override
    public void OnLogOutButtonClicked(MouseEvent mouseEvent)
    {
        pagesController.goToScreen(mouseEvent, pagesController.page_LogIn);
    }

    public void OnEnterButtonClicked(MouseEvent mouseEvent)
    {
        String code = textCode.getText().trim();
        if (codesController.ValidateCode(code))
        {
            showInformationMessage("ACCÉS PERMÈS!");
        }
        else showAlertErrorMessage("ACCÉS DENEGAT!");
        textCode.clear();
    }

    public void OnEnterFingerprintButtonClicked(MouseEvent mouseEvent)
    {

        SetButtonScanStyle("SCANNING");
        showFlashAlert("Escaneja l'empremta");
        var result = fingerprintsController.ValidateFingerprint();
        if (result.getValue())
        {
            showInformationMessage("ACCÉS PERMÈS!\n ENDEVANT " + result.getKey());
        }
        else showAlertErrorMessage("ACCÉS DENEGAT!");
        SetButtonScanStyle("NORMAL");

    }

    private void SetButtonScanStyle(String option)
    {
        String path = "";
        var aux = bttnFingerprint.getImage();
        if (option.equals("NORMAL"))
            path = new FXMLLoader(getClass().getResource("/logos/fingerprint.png")).getLocation().toString();

        else if (option.equals("SCANNING"))
            path = new FXMLLoader(getClass().getResource("/logos/fingerprint-red.png")).getLocation().toString();

        if (path.isEmpty()) bttnFingerprint.setImage(aux);
        else bttnFingerprint.setImage(new Image(path));
    }


    protected void showFlashAlert(String message)
    {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();

    }
}
