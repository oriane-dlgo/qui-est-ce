package vue

import javafx.scene.control.Label
import javafx.scene.layout.VBox

class Win : VBox() {
    val gagner : Label

    init{
        gagner = Label("Félicitation, vous avez réussi à deviner le personnage choisi" +
                "par votre adversaire.")
    }
}