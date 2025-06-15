package vue

import javafx.scene.control.Button
import javafx.scene.control.Label
import javafx.scene.layout.HBox
import javafx.scene.layout.VBox
import modele.Match

class Answer(question : String) : VBox (){

    var question : Label
    val btnOui : Button
    val btnNon : Button
    val contain : HBox
    val answer : Button // A VIRER ANSWER

    init{
        this.question = Label(question)
        answer = Button("[DEV] Answer") // A VIRER ANSWER
        btnOui = Button("Oui")
        btnNon = Button("Non")
        contain = HBox()
        contain.children.addAll(btnOui, btnNon, answer) // A VIRER ANSWER
        this.children.addAll(this.question, contain)
    }


}