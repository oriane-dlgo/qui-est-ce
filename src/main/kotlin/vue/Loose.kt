package vue

import javafx.scene.control.Button
import javafx.scene.control.Label
import javafx.scene.layout.VBox
import javafx.scene.text.Font
import javafx.scene.text.FontWeight
import ui.createTextLabel
import ui.createTitleLabel

class Loose(val round : Int): VBox() {
    val label : Label
    val label2 : Label
    val btnAgain : Button

    init{

        label = createTitleLabel("DEFAITE ! ")
        label2 = createTextLabel("Votre adversaire a été plus rapide que vous" +
                "la partie a durée $round tours.")
        label.style = "-fx-text-fill: white"
        label.font = Font.font("Courier New", FontWeight.BOLD, 18.0)

        btnAgain = Button("Recommencer")
        this.children.addAll(label, label2, btnAgain)
    }
}