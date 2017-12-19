package sample

import javafx.application.Application
import javafx.fxml.FXMLLoader
import javafx.scene.Parent
import javafx.scene.Scene
import javafx.stage.Stage

class Main : Application() {

    override fun start(primaryStage: Stage) {
        val fxmlLoader = FXMLLoader( javaClass.getResource("sample.fxml"));
        val root = fxmlLoader.load<Parent>()


        (fxmlLoader.getController() as MainController).setStage(primaryStage)
        primaryStage.title = "Hello World"
        primaryStage.scene = Scene(root, 600.0, 400.0)
        primaryStage.show()
    }


    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
           // Application.launch(*args)
            launch(Main::class.java)
        }
    }

}
