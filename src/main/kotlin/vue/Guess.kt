package vue

import javafx.scene.control.Button
import javafx.scene.layout.VBox

class Guess : VBox() {
    val question : Button
    val proposition : Button

    init{
        question = Button("Poser une question")
        proposition = Button("Proposer une solution")
        this.children.addAll(question, proposition)
    }


}