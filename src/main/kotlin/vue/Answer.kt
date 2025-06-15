package vue

import javafx.scene.control.Button
import javafx.scene.layout.VBox

class Answer : VBox() {
    val answer : Button

    init{
        answer = Button("Repondre")
        this.children.addAll(answer)
    }


}