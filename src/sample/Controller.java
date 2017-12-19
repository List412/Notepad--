package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class Controller {


    public Button btn_new;
    public Button btn_saveAs;
    public Button btn_save;
    public Button btn_open;
    public TextArea txtArea;

    private Stage stage;

    public void setStage(Stage st)
    {
        this.stage = st;
    }

    @FXML
    private void newDoc_bnt_action(ActionEvent event) {

        txtArea.setText("");
    }

    @FXML
    public void openDoc_btn_action(ActionEvent event) throws FileNotFoundException {

        FileChooser opener = new FileChooser();

        opener.setInitialDirectory(new File(System.getProperty("user.home")));
        File file = opener.showOpenDialog(stage.getOwner());

        if(file!=null)
        {
            FileReader reader = new FileReader(file);
        }


    }

    public void saveDoc_btn_action(ActionEvent event) {

    }

    public void saveDocAS_btn_action(ActionEvent event) {
    }
}
