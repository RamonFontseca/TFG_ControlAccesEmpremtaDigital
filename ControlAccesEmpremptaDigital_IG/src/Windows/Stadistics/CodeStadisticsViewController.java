package Windows.Stadistics;

import Controllers.PagesController;
import Controllers.StadisticsController;
import Model.CodeUsages;
import Singleton.Singleton;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.input.MouseEvent;

public class CodeStadisticsViewController {

    PagesController pagesController = Singleton.GetPagesController();
    StadisticsController stadisticsController = Singleton.GetStadisticsController();

    private int maximBound;

    @FXML private BarChart<String, Integer> barChartCodes;
    @FXML private CategoryAxis x;
    @FXML private NumberAxis y;

    public void InitData() {
        XYChart.Series series1 = new XYChart.Series();
        series1.setName("CODIS");
        var codesUsagesList = stadisticsController.GetCodesUsages();
        maximBound = 0;
        for (CodeUsages cu: codesUsagesList) {
            series1.getData().add(new XYChart.Data(cu.getCodeNumber(), cu.getCodeUsages()));
            if (cu.getCodeUsages() > maximBound) maximBound = cu.getCodeUsages();
        }
/*
        XYChart.Series series2 = new XYChart.Series();
        series2.setName("EMPREMTES");
        series2.getData().add(new XYChart.Data("HOLA1", 10));
        series2.getData().add(new XYChart.Data("HOLA2", 11));
        series2.getData().add(new XYChart.Data("HOLA3", 12));
        series2.getData().add(new XYChart.Data("HOLA4", 13));
        series2.getData().add(new XYChart.Data("HOLA5", 14));*/

        barChartCodes.getData().addAll(series1);
        SetBarChartStyle();
        SetBarChartBarsColors();

    }

    public void OnBackButtonClicked(MouseEvent mouseEvent) {
        pagesController.goToScreen(mouseEvent, pagesController.page_StadisticsMenu);
    }

    public void OnSettingsButtonClicked(MouseEvent mouseEvent) {
        pagesController.goToSettingsScreenFrom(mouseEvent, pagesController.page_Settings, pagesController.page_StadisicsCodesUsages);
    }

    private void SetBarChartStyle(){
        x.setStyle("-fx-background-color: white;");
        y.setStyle("-fx-background-color: white;");
        y.setLowerBound(0);
        y.setUpperBound(maximBound + 1);
        y.setTickUnit(1);
    }

    private void SetBarChartBarsColors()
    {
        //set first bar color
        for(Node n:barChartCodes.lookupAll(".default-color0.chart-bar")) {
            n.setStyle("-fx-bar-fill: #1ec9ad;");
        }
    }


}
