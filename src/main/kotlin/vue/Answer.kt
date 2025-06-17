package vue

import javafx.geometry.Pos
import javafx.scene.control.Button
import javafx.scene.control.Label
import javafx.scene.layout.HBox
import javafx.scene.layout.VBox
import javafx.scene.text.Font
import javafx.scene.text.FontWeight
import modele.Match
import ui.createHomeLabel
import ui.createSecondButton
import ui.createSmallButton

class Answer(question : String) : VBox (){

    var question : Label
    val btnOui : Button
    val btnNon : Button
    val contain : VBox

    init{
        this.question = createHomeLabel(question)
        btnOui = createSecondButton("Oui")

        btnNon = createSecondButton("Non")

        contain = VBox()
        contain.children.addAll(btnOui, btnNon)
        this.children.addAll(this.question, contain)
        contain.alignment = Pos.CENTER
        this.alignment = Pos.CENTER
    }
}