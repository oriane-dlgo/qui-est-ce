package vue

import javafx.geometry.Insets
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

    var labelQuestion : Label
    val btnOui : Button
    val btnNon : Button
    val btnBox : VBox

    init{
        labelQuestion = createHomeLabel(question)
        labelQuestion.isWrapText = true


        btnOui = createSecondButton("Oui")
        btnNon = createSecondButton("Non")

        btnBox = VBox(10.0,btnOui,btnNon)
        btnBox.alignment = Pos.CENTER
        btnBox.padding = Insets(15.0,0.0,0.0,0.0)

        this.children.addAll(labelQuestion, btnBox)
        this.alignment = Pos.CENTER
        this.padding = Insets(20.0,0.0,0.0,0.0)
        this.spacing = 20.0
    }
}