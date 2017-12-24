package sample

import javafx.application.Application
import javafx.fxml.FXMLLoader
import javafx.scene.Parent
import javafx.scene.Scene
import javafx.stage.Stage

class Main : Application() {

    override fun start(primaryStage: Stage) {
        val fxmlLoader = FXMLLoader( javaClass.getResource("notepad.fxml"))
        val root = fxmlLoader.load<Parent>()
        val cont = fxmlLoader.getController() as MainController
        cont.setStage(primaryStage)
        cont.showRecentFiles()
        cont.fillCmb()
        primaryStage.title = "Hello World"
        primaryStage.scene = Scene(root, 800.0, 500.0)
        primaryStage.show()
    }


    companion object {
        @JvmStatic fun main(args: Array<String>) {
            launch(Main::class.java)
        }
    }

}
