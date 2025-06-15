package vue

import javafx.scene.control.Button
import javafx.scene.control.Label
import javafx.scene.layout.HBox
import javafx.scene.layout.VBox
import modele.Match

class Answer(match: Match) : VBox (){

    val match : Match
    val question : Label
    val btnOui : Button
    val btnNon : Button
    val contain : HBox

    init{
        this.match = match
        question = Label(match.getQuestion())
        btnOui = Button("Oui")
        btnNon = Button("Non")
        contain = HBox()
        contain.children.addAll(btnOui, btnNon)
        this.children.addAll(question, contain)
    }




}