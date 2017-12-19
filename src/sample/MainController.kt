package sample


import javafx.event.ActionEvent
import javafx.fxml.FXML
import javafx.scene.control.Button
import javafx.scene.control.TextArea
import javafx.stage.FileChooser
import javafx.stage.Stage
import javafx.stage.Window

import java.io.File
import java.io.FileReader

class MainController {


    var btn_new: Button? = null
    var btn_saveAs: Button? = null
    var btn_save: Button? = null
    var btn_open: Button? = null
    var txtArea: TextArea? = null

    private var stage: Stage? = null

    fun setStage(st: Stage) {
        this.stage = st
    }

    @FXML
    private fun newDoc_bnt_action(event: ActionEvent) {

        txtArea!!.text = ""
    }

    @FXML
    fun openDoc_btn_action(event: ActionEvent) {

        val opener = FileChooser()

        opener.initialDirectory = File(System.getProperty("user.home"))
        val file = opener.showOpenDialog(stage!!.owner)

        if (file != null) {
            val reader = FileReader(file)
        }


    }

    fun saveDoc_btn_action(event: ActionEvent) {

    }

    fun saveDocAS_btn_action(event: ActionEvent) {}
}