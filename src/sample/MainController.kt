package sample

import com.sun.javafx.tk.Toolkit
import javafx.event.ActionEvent
import javafx.fxml.FXML
import javafx.geometry.Insets
import javafx.scene.control.*
import javafx.scene.image.Image
import javafx.scene.layout.*
import javafx.stage.FileChooser
import javafx.stage.Stage

import java.io.File
import java.io.FileReader
import java.io.PrintWriter
import javax.swing.filechooser.FileSystemView
import javax.imageio.ImageIO
import javafx.embed.swing.SwingFXUtils
import javafx.geometry.Pos
import javafx.scene.text.Font
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type.Int
import javafx.collections.ObservableList
import javafx.collections.FXCollections
import java.util.ArrayList
import java.awt.GraphicsEnvironment




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
    @FXML
    var vbox_FileHistory: VBox? = null
    @FXML
    var search_txt: TextField? = null
    @FXML
    var replace_txt: TextField? = null
    @FXML
    var colorPic: ColorPicker? = null
    @FXML
    var cmb_size: ComboBox<Int>? = null
    @FXML
    var cmb_font: ComboBox<String>? = null


    val opener = FileChooser()
    var savePath: String? = null

    var recentDocs: ArrayList<String?>? = null


    private var stage: Stage? = null

    fun setStage(st: Stage) {
        this.stage = st
    }

    private fun wipeData() {
        savePath = null
        txtArea!!.text = ""
    }

    @FXML
    private fun newDoc_bnt_action(event: ActionEvent) {

        if (txtArea!!.text != "") {
            val alert = Alert(Alert.AlertType.CONFIRMATION)
            alert.title = "Сохранить"
            alert.headerText = null
            alert.contentText = "Хотите сохранить текущий документ?"
            alert.showAndWait()

            if (alert.result == ButtonType.OK) {
                saveDoc_btn_action(event)

            }
            addDoc()
        }
        wipeData()

    }

    fun rewriteRecentDocFile() {
        val file = File("Doc.txt")
        if (file.exists()) {
            val fileReader = FileReader(file)
            val fileInfo = fileReader.readLines()

            recentDocs = ArrayList()
            recentDocs?.addAll(fileInfo)
        } else {
            file.createNewFile()
            rewriteRecentDocFile()
        }
    }

    fun addDoc() {
        rewriteRecentDocFile()
        recentDocs?.remove(savePath)
        recentDocs?.add(0, savePath)
        var str = recentDocs?.joinToString("\n")

        saveDoc("Doc.txt", str)

        vbox_FileHistory!!.children.clear()

        showRecent()
    }

    private fun showRecent() {
        for (a in recentDocs!!.iterator()) {
            var pane = StackPane()
            pane.prefWidth = 100.0
            pane.alignment = Pos.TOP_CENTER
            var paneIner = StackPane()
            val sourceimage = File("DocPicture.png")
            val img = SwingFXUtils.toFXImage(ImageIO.read(sourceimage), null)
            paneIner.setOnMouseClicked { _ -> recentFile_click(a) }
            paneIner.prefHeight = 80.0
            paneIner.background = Background(
                    BackgroundImage(
                            img as Image,
                            BackgroundRepeat.NO_REPEAT,
                            BackgroundRepeat.NO_REPEAT,
                            BackgroundPosition.CENTER,
                            BackgroundSize(55.0, 80.0, false, false, false, false)
                    ))
            pane.padding = Insets(8.0, 0.0, 8.0, 0.0)
            val lbl = Label()
            lbl.text = a?.substring(a.indexOfLast { x -> x == File.separatorChar } + 1, a.length)
            paneIner.children.add(lbl)
            pane.children.add(paneIner)
            vbox_FileHistory!!.children.add(pane)
        }
    }

    fun recentFile_click(path: String?) {
        val file = File(path)
        if (file.exists()) {
            openDoc(path)
        } else {
            val alert = Alert(Alert.AlertType.INFORMATION)
            alert.title = "Файл больше не существует"
            alert.headerText = null
            alert.contentText = "Данный файл больше не существует"
            alert.showAndWait()
            recentDocs?.remove(path)
            vbox_FileHistory!!.children.clear()
            showRecent()
            var str = recentDocs?.joinToString("\n")

            saveDoc("Doc.txt", str)
        }

    }

    public fun showRecentFiles() {
        rewriteRecentDocFile()
        showRecent()
    }

    @FXML
    fun openDoc_btn_action(event: ActionEvent) {
        if (opener.initialDirectory == null)
            opener.initialDirectory = FileSystemView.getFileSystemView().homeDirectory
        val file = opener.showOpenDialog(stage!!.owner)

        if (file != null) {
            opener.initialDirectory = file.parentFile
            openDoc(file.path)
            addDoc()
        }
    }

    fun openDoc(path: String?) {
        val reader = FileReader(path)
        var text = reader.readText()
        txtArea?.text = text
        savePath = path
        reader.close()
    }

    fun saveDoc_btn_action(event: ActionEvent) {
        if (savePath == null) {
            saveDocAS_btn_action(event)
        } else {
            saveDoc(savePath, txtArea?.text)
            addDoc()
        }
    }

    fun saveDocAS_btn_action(event: ActionEvent) {
        val file = opener.showSaveDialog(stage!!.owner)
        if (file != null) {
            savePath = file.path
            addDoc()
            saveDoc(savePath, txtArea?.text)
        }
    }

    fun saveDoc(path: String?, text: String?) {
        val writer = PrintWriter(path)
        writer.flush()
        writer.print(text)
        writer.close()
    }

    fun replace_click(event: ActionEvent) {
        var find = search_txt?.text as String
        var replace = replace_txt?.text as String

        var str = txtArea?.text

        txtArea?.text = str?.replace(find, replace, true)

    }

    fun changeColor(event: ActionEvent) {
        var color = colorPic?.value
        txtArea?.style = "-fx-text-fill: rgb(" + color!!.red * 255 + ", " + color!!.green * 255 + ", " + color!!.blue * 255 + ")"
    }

    fun fontChange(event: ActionEvent) {
        var font = cmb_font!!.value
        txtArea?.style = "-fx-font-family: "+ font
    }

    fun sizeChange(event: ActionEvent) {
        var size = cmb_size!!.value
        txtArea?.style = "-fx-font-size: " + size
    }

    fun fillCmb() {
        for (a in 1..40)
        {
            cmb_size!!.items.add(a)
        }
        cmb_size!!.value = 15
        val fon = Font.getFontNames()
        cmb_font!!.items.addAll(fon)
        cmb_font!!.value = "Times New Roman"
    }


}