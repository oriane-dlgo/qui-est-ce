package vue

import javafx.geometry.Insets
import javafx.geometry.Pos
import javafx.scene.control.Button
import javafx.scene.layout.VBox
import javafx.scene.text.Font
import javafx.scene.text.FontWeight

class MatchMaking : VBox() {

    val btnBox : VBox //center
    val btnNew : Button //vbox
    val btnJoin : Button //vbox

    init{
        btnNew = Button("Nouvelle Partie")
        btnNew.style = "-fx-background-color: #61888c; -fx-text-fill: white"      // Couleur du fond et de l'écriture
        btnNew.setOnMouseEntered {
            btnNew.style = "-fx-background-color: #4e6b6e; -fx-text-fill: black;" // Couleur du fond et de l'écriture lorsque la souris passe dessus
        }
        btnNew.setOnMouseExited {
            btnNew.style = "-fx-background-color: #61888c; -fx-text-fill: white;" // Couleur du fond et de l'écriture lorsque la souris n'est plus dessus
        }
        btnNew.font = Font.font("Courier New", FontWeight.BOLD, 22.0) // Change la police et la taille
        btnNew.padding = Insets(15.0)  // Modifie la taille du fond du bouton


        btnJoin = Button("Rejoindre une Partie")
        btnJoin.style = "-fx-background-color: #61888c; -fx-text-fill: white"      // Couleur du fond et de l'écriture
        btnJoin.setOnMouseEntered {
            btnJoin.style = "-fx-background-color: #4e6b6e; -fx-text-fill: black;" // Couleur du fond et de l'écriture lorsque la souris passe dessus
        }
        btnJoin.setOnMouseExited {
            btnJoin.style = "-fx-background-color: #61888c; -fx-text-fill: white;" // Couleur du fond et de l'écriture lorsque la souris n'est plus dessus
        }
        btnJoin.font = Font.font("Courier New", FontWeight.BOLD, 22.0) // Change la police et la taille
        btnJoin.padding = Insets(15.0)  // Modifie la taille du fond du bouton


        btnBox = VBox(30.0, btnNew, btnJoin)
        btnBox.alignment = Pos.CENTER
        VBox.setMargin (btnBox, Insets(100.0))  // Pousse la VBox des boutons vers le bas (de 100 px)
        this.children.add(btnBox)


    }
}