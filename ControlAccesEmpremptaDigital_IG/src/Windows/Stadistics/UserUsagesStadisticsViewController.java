package Windows.Stadistics;

import Controllers.PagesController;
import Controllers.StadisticsController;
import Model.CodeUsages;
import Model.UserUsages;
import Singleton.Singleton;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.input.MouseEvent;


public class UserUsagesStadisticsViewController {

    private static PagesController pagesController = Singleton.GetPagesController();
    private static StadisticsController stadisticsController = Singleton.GetStadisticsController();

    private static int maxBound;

    @FXML private BarChart<String, Integer> barChartUsers;
    @FXML private CategoryAxis x;
    @FXML private NumberAxis y;

    public void InitData() {
        XYChart.Series series1 = new XYChart.Series();
        series1.setName("USUARIS");
        var usersUsageList = stadisticsController.GetUsersUsages();
        maxBound = 0;
        for (UserUsages uu: usersUsageList) {
            series1.getData().add(new XYChart.Data(uu.getUserName(), uu.getUserUsages()));
            if (uu.getUserUsages() > maxBound) maxBound = uu.getUserUsages();
        }

        barChartUsers.getData().addAll(series1);
        SetBarChartStyle();
        SetBarChartBarsColors();
    }

    public void OnBackButtonClicked(MouseEvent mouseEvent) {
        pagesController.goToScreen(mouseEvent, pagesController.page_StadisticsMenu);
    }

    public void OnSettingsButtonClicked(MouseEvent mouseEvent) {
        pagesController.goToSettingsScreenFrom(mouseEvent, pagesController.page_Settings, pagesController.page_StadisicsUagesPerUSer);
    }

    private void SetBarChartStyle(){
        x.setStyle("-fx-background-color: white;");
        y.setStyle("-fx-background-color: white;");
        y.setLowerBound(0);
        y.setUpperBound(maxBound + 1);
        y.setTickUnit(1);
    }

    private void SetBarChartBarsColors()
    {
        //set first bar color
        for(Node n:barChartUsers.lookupAll(".default-color0.chart-bar")) {
            n.setStyle("-fx-bar-fill: #1ec974;");
        }
    }
}
