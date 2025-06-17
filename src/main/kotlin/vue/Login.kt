package vue

import javafx.animation.FadeTransition
import javafx.animation.ParallelTransition
import javafx.animation.ScaleTransition
import javafx.animation.SequentialTransition
import javafx.geometry.Insets
import javafx.geometry.Pos
import javafx.scene.control.Button
import javafx.scene.control.Label
import javafx.scene.control.TextField
import javafx.scene.layout.HBox
import javafx.scene.layout.StackPane
import javafx.scene.layout.VBox
import javafx.util.Duration
import ui.*

class Login : StackPane() {

    val backSquare: VBox

    //val image: ImageView
    val labelTitle: Label
    val boxTextEntries: VBox
    val boxLastName: VBox
    val labelLastName: Label
    val textFieldLastName: TextField
    val boxName: VBox
    val labelName: Label
    val textFieldName: TextField
    val btnLogin: Button //hbox

    init {

        backSquare = VBox()
        backSquare.maxWidth = 500.0
        backSquare.maxHeight = 500.0
        backSquare.prefWidth = 500.0
        backSquare.prefHeight = 500.0
        backSquare.style =
            "-fx-background-color: rgba(255, 255, 255, 0.9); -fx-background-radius: 10px; -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.4), 10, 0.2, 0, 4);"
        backSquare.alignment = Pos.CENTER
        backSquare.padding = Insets(20.0)
        backSquare.spacing = 20.0



        labelTitle = createHomeLabel("C KI LUI ?")
        labelTitle.style = """
            -fx-font-family: 'Fascinate';
            -fx-font-size: 70px;
            
        """.trimIndent()

        boxTextEntries = VBox().apply {
            padding = Insets(0.0, 125.0, 40.0, 125.0)
            alignment = Pos.CENTER
            spacing = 20.0
        }

        boxLastName = VBox()
        labelLastName = createHomeLabel("Nom")
        textFieldLastName = createHomeTextField("Chirrac")

        boxName = VBox()
        labelName = createHomeLabel("Prenom")
        textFieldName = createHomeTextField("Jacques")

        btnLogin = createMainButton("Jouer")


        boxLastName.children.addAll(labelLastName, textFieldLastName)

        boxName.children.addAll(labelName, textFieldName)

        boxTextEntries.children.addAll(boxLastName, boxName)

        backSquare.children.addAll(labelTitle, boxTextEntries, btnLogin)

        children.add(backSquare)



    }
    fun startLogin() {
        val fade = FadeTransition(Duration.millis(500.0), backSquare).apply {
            fromValue = 0.0
            toValue = 1.0
        }

        val scale = ScaleTransition(Duration.millis(500.0), backSquare).apply {
            fromX = 0.8
            fromY = 0.8
            toX = 1.0
            toY = 1.0
        }

        val animation = ParallelTransition(fade, scale)
        animation.play()
    }
}

