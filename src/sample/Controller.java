package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import jdk.nashorn.internal.ir.annotations.Ignore;

public class Controller {


    public Button btn_new;
    public Button btn_saveAs;
    public Button btn_save;
    public Button btn_open;
    public TextArea txtArea;

    @FXML
    private void newDoc_bnt_action(ActionEvent event) {

        txtArea.setText("");
    }

    @FXML
    public void openDoc_btn_action(ActionEvent event) {



    }

    public void saveDoc_btn_action(ActionEvent event) {

    }

    public void saveDocAS_btn_action(ActionEvent event) {
    }
}
