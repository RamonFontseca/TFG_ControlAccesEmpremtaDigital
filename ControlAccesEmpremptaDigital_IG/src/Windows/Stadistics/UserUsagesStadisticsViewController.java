package Windows.Stadistics;

import Controllers.PagesController;
import Controllers.StadisticsController;
import Model.CodeUsages;
import Model.UserUsages;
import Singleton.Singleton;
import Windows.Base.BaseStadisticsController;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.input.MouseEvent;


public class UserUsagesStadisticsViewController extends BaseStadisticsController {

    public void InitData() {
        InitializeUserLabel();

        XYChart.Series series1 = new XYChart.Series();
        series1.setName("USUARIS");
        var usersUsageList = stadisticsController.GetUsersUsages();
        maxBound = 0;
        for (UserUsages uu: usersUsageList) {
            series1.getData().add(new XYChart.Data(uu.getUserName(), uu.getUserUsages()));
            if (uu.getUserUsages() > maxBound) maxBound = uu.getUserUsages();
        }

        barChart.getData().addAll(series1);
        SetBarChartStyle();
        SetBarChartBarsColors();
    }

    @Override
    public void OnSettingsButtonClicked(MouseEvent mouseEvent) {
        pagesController.goToSettingsScreenFrom(mouseEvent, pagesController.page_Settings, pagesController.page_StadisicsUagesPerUser);
    }

    @Override
    protected void SetBarChartBarsColors()
    {
        //set first bar color
        for(Node n:barChart.lookupAll(".default-color0.chart-bar")) {
            n.setStyle("-fx-bar-fill: #1ec974;");
        }
    }
}
