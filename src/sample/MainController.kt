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
import java.io.FileWriter
import java.io.PrintWriter
import java.nio.file.FileSystem
import javax.swing.filechooser.FileSystemView

class MainController {

    @FXML
    var btn_new: Button? = null
    @FXML
    var btn_saveAs: Button? = null
    @FXML
    var btn_save: Button? = null
    @FXML
    var btn_open: Button? = null
    @FXML
    var txtArea: TextArea? = null


    val opener = FileChooser()
    var savePath:String?=null


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
        if (opener.initialDirectory == null)
            opener.initialDirectory = FileSystemView.getFileSystemView().homeDirectory
        val file = opener.showOpenDialog(stage!!.owner)

        if (file != null) {
            opener.initialDirectory = file.parentFile
            val reader = FileReader(file)
            var text = reader.readText()
            txtArea?.text = text
            savePath = file.path
            reader.close();
        }
    }

    fun saveDoc_btn_action(event: ActionEvent) {
        if (savePath != null)
        {
           savedoc(savePath)
        }
    }

    fun saveDocAS_btn_action(event: ActionEvent) {
        val file = opener.showSaveDialog(stage!!.owner)
        if(file != null)
        {
            savedoc(file.path)
        }
    }

    fun savedoc(path:String?)
    {
        val writer = PrintWriter(path)
        writer.flush()
        writer.print(txtArea?.text)
        writer.close()
    }
}