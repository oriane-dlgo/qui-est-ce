package vue

import javafx.scene.control.Button
import javafx.scene.control.Label
import javafx.scene.layout.HBox
import javafx.scene.layout.VBox
import javafx.scene.text.Font
import javafx.scene.text.FontWeight
import modele.Match

class Answer(question : String) : VBox (){

    var question : Label
    val btnOui : Button
    val btnNon : Button
    val contain : HBox

    init{
        this.question = Label(question)
        btnOui = Button("Oui")
        btnOui.style = "-fx-background-color: #61888c; -fx-text-fill: white"      // Couleur du fond et de l'écriture
        btnOui.setOnMouseEntered {
            btnOui.style = "-fx-background-color: #4e6b6e; -fx-text-fill: black;" // Couleur du fond et de l'écriture lorsque la souris passe dessus
        }
        btnOui.setOnMouseExited {
            btnOui.style = "-fx-background-color: #61888c; -fx-text-fill: white;" // Couleur du fond et de l'écriture lorsque la souris n'est plus dessus
        }
        btnOui.font = Font.font("Courier New", FontWeight.BOLD, 10.0) // Change la police et la taille

        btnNon = Button("Non")
        btnNon.style = "-fx-background-color: #61888c; -fx-text-fill: white"      // Couleur du fond et de l'écriture
        btnNon.setOnMouseEntered {
            btnNon.style = "-fx-background-color: #4e6b6e; -fx-text-fill: black;" // Couleur du fond et de l'écriture lorsque la souris passe dessus
        }
        btnNon.setOnMouseExited {
            btnNon.style = "-fx-background-color: #61888c; -fx-text-fill: white;" // Couleur du fond et de l'écriture lorsque la souris n'est plus dessus
        }
        btnNon.font = Font.font("Courier New", FontWeight.BOLD, 10.0) // Change la police et la taille

        contain = HBox()
        contain.children.addAll(btnOui, btnNon)
        this.children.addAll(this.question, contain)
    }
}