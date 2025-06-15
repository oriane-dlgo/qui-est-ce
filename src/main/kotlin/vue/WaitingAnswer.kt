package vue

import javafx.scene.control.Label
import javafx.scene.layout.VBox

class WaitingAnswer : VBox() {
    val sentence : Label

    init{
        sentence = Label("En attente de la réponse")
        this.children.add(sentence)
    }
}