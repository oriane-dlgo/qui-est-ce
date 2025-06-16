package vue

import javafx.scene.control.Button
import javafx.scene.control.Label
import javafx.scene.layout.VBox
import javafx.scene.text.Font
import javafx.scene.text.FontWeight

class WaitingPlayer : VBox() {

    val label : Label

    init{
        this.label = Label("Waiting opponent")
        label.style = "-fx-text-fill: white"
        label.font = Font.font("Courier New", FontWeight.BOLD, 18.0)
        this.children.add(label)

    }


}