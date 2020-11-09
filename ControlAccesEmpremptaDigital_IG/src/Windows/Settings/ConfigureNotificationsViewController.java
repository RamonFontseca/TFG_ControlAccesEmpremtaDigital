package Windows.Settings;

import Controllers.NotificationsController;
import Controllers.PagesController;
import Singleton.Singleton;
import Windows.Base.BaseController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

public class ConfigureNotificationsViewController extends BaseController {

    PagesController pagesController = Singleton.GetPagesController();
    NotificationsController notificationsController;


    @FXML private Button bttnSendNotificationsState;
    @FXML private Text textNotifState;

    public void InitData()
    {
        InitializeUserLabel();

        notificationsController = Singleton.GetNotificationsController();
        textNotifState.setText(notificationsController.GetEnabledDescription());
        ChangeColor();
        textNotifState.setTextAlignment(TextAlignment.RIGHT);
    }

    @Override
    public void OnBackButtonClicked(MouseEvent mouseEvent) {
        pagesController.goToSettingsScreenFrom(mouseEvent,pagesController.page_Settings, pagesController.page_MainMenu);
    }

    private void ChangeStateAndColor(){
        if (notificationsController.GetEnabled()){
            notificationsController.changeEnabled();
            bttnSendNotificationsState.setStyle("-fx-background-color: #bd1a1a;");
        }
        else{
            notificationsController.changeEnabled();
            bttnSendNotificationsState.setStyle("-fx-background-color: green;");
        }
    }

    private void ChangeColor(){
        if (notificationsController.GetEnabled()){
            bttnSendNotificationsState.setStyle("-fx-background-color: green;");
        }
        else{
            bttnSendNotificationsState.setStyle("-fx-background-color: #bd1a1a;");
        }
    }

    public void OnSendNotifClicked(MouseEvent mouseEvent)
    {
        ChangeStateAndColor();
        textNotifState.setText(notificationsController.GetEnabledDescription());
    }
}
