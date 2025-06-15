package vue

import javafx.scene.control.Button
import javafx.scene.control.Label
import javafx.scene.layout.VBox

class Echec : VBox() {
    val dommage : Label
    val retry : Button

    init{
        dommage = Label("Dommage, le personnage que vous avez" +
                " sélectionné n'est pas celui choisi par votre adversaire.")

        retry = Button("Retentez votre chance")
    }
}