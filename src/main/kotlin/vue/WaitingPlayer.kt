package vue

import javafx.geometry.Insets
import javafx.geometry.Pos
import javafx.scene.control.Button
import javafx.scene.control.Label
import javafx.scene.layout.VBox
import javafx.scene.text.Font
import javafx.scene.text.FontWeight
import ui.createTextLabel

class WaitingPlayer : VBox() {

    val label : Label

    init{

        this.label = createTextLabel("Waiting opponent")
        this.children.add(label)
        this.alignment = Pos.CENTER

        this.padding = Insets(10.0)


    }

    fun setMessage(message: String){
        label.text = message
    }
}