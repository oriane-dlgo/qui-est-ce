package vue

import javafx.scene.control.Button
import javafx.scene.control.Label
import javafx.scene.layout.VBox

class WaitingPlayer : VBox() {

    val label : Label

    init{
        this.label = Label("Waiting oppopent")
        this.children.add(label)
    }


}