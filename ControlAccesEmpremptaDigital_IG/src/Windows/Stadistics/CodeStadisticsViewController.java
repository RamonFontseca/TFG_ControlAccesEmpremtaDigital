package Windows.Stadistics;

import Controllers.PagesController;
import Controllers.StadisticsController;
import Model.CodeUsages;
import Singleton.Singleton;
import Windows.Base.BaseStadisticsController;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.input.MouseEvent;

public class CodeStadisticsViewController extends BaseStadisticsController {


    public void InitData() {
        InitializeUserLabel();

        XYChart.Series series1 = new XYChart.Series();
        series1.setName("CODIS");
        var codesUsagesList = stadisticsController.GetCodesUsages();
        maxBound = 0;
        for (CodeUsages cu: codesUsagesList) {
            series1.getData().add(new XYChart.Data(cu.getCodeNumber(), cu.getCodeUsages()));
            if (cu.getCodeUsages() > maxBound) maxBound = cu.getCodeUsages();
        }

        barChart.getData().addAll(series1);
        SetBarChartStyle();
        SetBarChartBarsColors();

    }

    @Override
    public void OnSettingsButtonClicked(MouseEvent mouseEvent) {
        pagesController.goToSettingsScreenFrom(mouseEvent, pagesController.page_Settings, pagesController.page_StadisicsCodesUsages);
    }

    @Override
    protected void SetBarChartBarsColors()
    {
        //set first bar color
        for(Node n:barChart.lookupAll(".default-color0.chart-bar")) {
            n.setStyle("-fx-bar-fill: #1ec9ad;");
        }
    }
}
