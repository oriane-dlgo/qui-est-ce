package vue

import javafx.geometry.Insets
import javafx.geometry.Pos
import javafx.scene.control.Button
import javafx.scene.layout.VBox
import javafx.scene.text.Font
import javafx.scene.text.FontWeight

class PickCharacter : VBox() {
    val btnValid : Button

    init{

        btnValid = Button("Valider")
        btnValid.style = "-fx-background-color: #61888c; -fx-text-fill: white"      // Couleur du fond et de l'écriture
        btnValid.setOnMouseEntered {
            btnValid.style = "-fx-background-color: #4e6b6e; -fx-text-fill: black;" // Couleur du fond et de l'écriture lorsque la souris passe dessus
        }
        btnValid.setOnMouseExited {
            btnValid.style = "-fx-background-color: #61888c; -fx-text-fill: white;" // Couleur du fond et de l'écriture lorsque la souris n'est plus dessus
        }
        btnValid.font = Font.font("Courier New", FontWeight.BOLD, 20.0) // Change la police et la taille

        this.children.add(btnValid)
        this.alignment = Pos.CENTER
        this.spacing = 10.0
        this.padding = Insets(25.0)

    }
}