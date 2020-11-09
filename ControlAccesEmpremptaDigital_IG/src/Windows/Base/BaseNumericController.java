package Windows.Base;

import DataAcces.ConnectionDB;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public abstract class BaseNumericController extends BaseController{

    // Members
    @FXML
    protected TextField textCode;
    protected int maxLenght = 10;

    // Methods
    public void OnButton0Click(MouseEvent mouseEvent) {
        if (textCode.getLength() < maxLenght)
            textCode.appendText("0");
    }

    public void OnButton1Click(MouseEvent mouseEvent) {
        if (textCode.getLength() < maxLenght)
            textCode.appendText("1");
    }

    public void OnButton2Click(MouseEvent mouseEvent) {
        if (textCode.getLength() < maxLenght)
            textCode.appendText("2");
    }

    public void OnButton3Click(MouseEvent mouseEvent) {
        if (textCode.getLength() < maxLenght)
            textCode.appendText("3");
    }

    public void OnButton5Click(MouseEvent mouseEvent) {
        if (textCode.getLength() < maxLenght)
            textCode.appendText("5");
    }

    public void OnButton4Click(MouseEvent mouseEvent) {
        if (textCode.getLength() < maxLenght)
            textCode.appendText("4");
    }

    public void OnButton6Click(MouseEvent mouseEvent) {
        if (textCode.getLength() < maxLenght)
            textCode.appendText("6");
    }

    public void OnButton7Click(MouseEvent mouseEvent) {
        if (textCode.getLength() < maxLenght)
            textCode.appendText("7");
    }

    public void OnButton8Click(MouseEvent mouseEvent) {
        if (textCode.getLength() < maxLenght)
            textCode.appendText("8");
    }

    public void OnButton9Click(MouseEvent mouseEvent) {
        if (textCode.getLength() < maxLenght)
            textCode.appendText("9");
        ConnectionDB connectionDB = new ConnectionDB();
    }

    public void OnEraseButtonClicked(MouseEvent mouseEvent) {
        if (textCode.getLength() > 0)
            textCode.setText(textCode.getText(0, textCode.getLength()-1));
    }
}
