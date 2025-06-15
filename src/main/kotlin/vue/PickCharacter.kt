package vue

import javafx.scene.control.Button
import javafx.scene.layout.VBox

class PickCharacter : VBox() {
    val btnValid : Button

    init{
        btnValid = Button("Valider")
        this.children.add(btnValid)
    }
}