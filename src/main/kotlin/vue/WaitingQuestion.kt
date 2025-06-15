package vue

import javafx.scene.control.Label
import javafx.scene.layout.VBox

class WaitingQuestion : VBox() {
    val sentence : Label

    init{
        sentence = Label("En attente de la question")
        this.children.add(sentence)
    }
}