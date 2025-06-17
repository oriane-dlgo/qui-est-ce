package vue

import javafx.geometry.Insets
import javafx.geometry.Pos
import javafx.scene.control.Button
import javafx.scene.control.Label
import javafx.scene.layout.VBox
import javafx.scene.text.Font
import javafx.scene.text.FontWeight
import ui.createMainButton
import ui.createSecondButton
import ui.createTextLabel

class PickCharacter : VBox() {
    val btnValid : Button
    val label : Label

    init{

        btnValid = createMainButton("Valider")
        label = createTextLabel("Choisissez votre personnage")
        this.children.addAll(label, btnValid)

        this.alignment = Pos.CENTER
        this.spacing = 40.0
        this.padding = Insets(15.0)

    }
}