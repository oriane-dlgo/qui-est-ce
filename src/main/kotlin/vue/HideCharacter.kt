package vue

import javafx.scene.control.Button
import javafx.scene.control.Label
import javafx.scene.layout.VBox
import javafx.scene.text.Font
import javafx.scene.text.FontWeight

class HideCharacter(val answer : String) : VBox() {

    val labelResponse : Label
    val btnHide : Button
    val btnOk : Button
    val proposition : Button



    init{
        labelResponse = Label("La réponse à la question est : $answer ")
        btnHide = Button("Cacher des personnages")
        btnHide.style = "-fx-background-color: #61888c; -fx-text-fill: white"      // Couleur du fond et de l'écriture
        btnHide.setOnMouseEntered {
            btnHide.style = "-fx-background-color: #4e6b6e; -fx-text-fill: black;" // Couleur du fond et de l'écriture lorsque la souris passe dessus
        }
        btnHide.setOnMouseExited {
            btnHide.style = "-fx-background-color: #61888c; -fx-text-fill: white;" // Couleur du fond et de l'écriture lorsque la souris n'est plus dessus
        }
        btnHide.font = Font.font("Courier New", FontWeight.BOLD, 12.0) // Change la police et la taille

        btnOk = Button("Compris")
        btnOk.style = "-fx-background-color: #61888c; -fx-text-fill: white"      // Couleur du fond et de l'écriture
        btnOk.setOnMouseEntered {
            btnOk.style = "-fx-background-color: #4e6b6e; -fx-text-fill: black;" // Couleur du fond et de l'écriture lorsque la souris passe dessus
        }
        btnOk.setOnMouseExited {
            btnOk.style = "-fx-background-color: #61888c; -fx-text-fill: white;" // Couleur du fond et de l'écriture lorsque la souris n'est plus dessus
        }
        btnOk.font = Font.font("Courier New", FontWeight.BOLD, 12.0) // Change la police et la

        proposition = Button("Proposer une solution")
        proposition.style = "-fx-background-color: #61888c; -fx-text-fill: white"      // Couleur du fond et de l'écriture
        proposition.setOnMouseEntered {
            proposition.style = "-fx-background-color: #4e6b6e; -fx-text-fill: black;" // Couleur du fond et de l'écriture lorsque la souris passe dessus
        }
        proposition.setOnMouseExited {
            proposition.style = "-fx-background-color: #61888c; -fx-text-fill: white;" // Couleur du fond et de l'écriture lorsque la souris n'est plus dessus
        }
        proposition.font = Font.font("Courier New", FontWeight.BOLD, 20.0) // Change la police et la taille

        this.children.addAll(labelResponse, btnHide, btnOk, proposition)
    }


}