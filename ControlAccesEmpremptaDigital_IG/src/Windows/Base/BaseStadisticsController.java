package Windows.Base;

import Controllers.StadisticsController;
import Singleton.Singleton;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.input.MouseEvent;

public abstract class BaseStadisticsController extends BaseController{

    protected StadisticsController stadisticsController = Singleton.GetStadisticsController();

    protected int maxBound;

    @FXML protected BarChart<String, Integer> barChart;
    @FXML protected CategoryAxis x;
    @FXML protected NumberAxis y;

    protected void SetBarChartStyle(){
        x.setStyle("-fx-background-color: white;");
        y.setStyle("-fx-background-color: white;");
        y.setLowerBound(0);
        y.setUpperBound(maxBound + 1);
        y.setTickUnit(1);
    }

    protected void SetBarChartBarsColors()
    {
        //set first bar color
        for(Node n:barChart.lookupAll(".default-color0.chart-bar")) {
            n.setStyle("-fx-bar-fill: #1ec974;");
        }
    }

    @Override
    public void OnBackButtonClicked(MouseEvent mouseEvent) {
        pagesController.goToScreen(mouseEvent, pagesController.page_StadisticsMenu);
    }
}
