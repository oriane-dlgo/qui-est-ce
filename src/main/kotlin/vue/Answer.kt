package vue

import javafx.scene.control.Button
import javafx.scene.control.Label
import javafx.scene.layout.HBox
import javafx.scene.layout.VBox
import javafx.scene.text.Font
import javafx.scene.text.FontWeight
import modele.Match
import ui.createSmallButton

class Answer(question : String) : VBox (){

    var question : Label
    val btnOui : Button
    val btnNon : Button
    val contain : HBox

    init{
        this.question = Label(question)
        btnOui = createSmallButton("Oui")

        btnNon = createSmallButton("Non")

        contain = HBox()
        contain.children.addAll(btnOui, btnNon)
        this.children.addAll(this.question, contain)
    }
}