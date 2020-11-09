package Windows.Stadistics;

import Controllers.PagesController;
import Singleton.Singleton;
import Windows.Base.BaseController;
import javafx.scene.input.MouseEvent;

public class StadisticsMenuViewController extends BaseController {

    PagesController pagesController = Singleton.GetPagesController();

    public void OnBackButtonClicked(MouseEvent mouseEvent) {
        pagesController.goToScreen(mouseEvent, pagesController.page_MainMenu);
    }

    public void OnSettingsButtonClicked(MouseEvent mouseEvent) {
        pagesController.goToSettingsScreenFrom(mouseEvent, pagesController.page_Settings, pagesController.page_StadisticsMenu);
    }

    public void OnCodeStadisticsButtonClilcked(MouseEvent mouseEvent) {
        pagesController.goToCodeStadisticsScreenConstructing(mouseEvent, pagesController.page_StadisicsCodesUsages);
    }

    public void OnUserUsagesStadisticsButtonClicked(MouseEvent mouseEvent) {
        pagesController.goToUserStadisticsScreenConstructing(mouseEvent, pagesController.page_StadisicsUagesPerUser);
    }

}
