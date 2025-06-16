package vue

import javafx.scene.control.Button
import javafx.scene.layout.VBox
import javafx.scene.text.Font
import javafx.scene.text.FontWeight

class Question : VBox() {
    val question : Button


    init{
        question = Button("Poser une question")
        question.style = "-fx-background-color: #61888c; -fx-text-fill: white"      // Couleur du fond et de l'écriture
        question.setOnMouseEntered {
            question.style = "-fx-background-color: #4e6b6e; -fx-text-fill: black;" // Couleur du fond et de l'écriture lorsque la souris passe dessus
        }
        question.setOnMouseExited {
            question.style = "-fx-background-color: #61888c; -fx-text-fill: white;" // Couleur du fond et de l'écriture lorsque la souris n'est plus dessus
        }
        question.font = Font.font("Courier New", FontWeight.BOLD, 20.0) // Change la police et la taille



        this.children.addAll(question)


    }


}