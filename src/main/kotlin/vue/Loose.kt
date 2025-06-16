package vue

import javafx.scene.control.Button
import javafx.scene.control.Label
import javafx.scene.layout.VBox
import javafx.scene.text.Font
import javafx.scene.text.FontWeight

class Loose(val round : Int): VBox() {
    val label : Label
    val btnAgain : Button

    init{

        label = Label("DEFAITE ! Votre adversaire a été plus rapide que vous" +
                "la partie a durée $round tours.")
        label.style = "-fx-text-fill: white"
        label.font = Font.font("Courier New", FontWeight.BOLD, 18.0)

        btnAgain = Button("Recommencer")
        this.children.addAll(label, btnAgain)
    }
}