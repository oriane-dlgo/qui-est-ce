package vue

import Controleurs.ControleurBoutonLogin
import javafx.geometry.Insets
import javafx.geometry.Pos
import javafx.scene.control.Button
import javafx.scene.control.Label
import javafx.scene.layout.BorderPane
import javafx.scene.layout.VBox
import javafx.scene.text.Font
import javafx.scene.text.FontWeight
import kotlinx.serialization.descriptors.PrimitiveKind
import modele.Client

class MatchMaking : BorderPane() {

    val bvnJoueur : Label
    val btnBox : VBox //center
    val btnNew : Button //vbox
    val btnJoin : Button //vbox

    init{
        bvnJoueur = Label("")
        bvnJoueur.font = Font.font("Courier New", FontWeight.BOLD, 30.0)
        bvnJoueur.style = "-fx-text-fill: white"
        bvnJoueur.padding = Insets(30.0)


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


        btnBox = VBox(60.0, btnNew, btnJoin)  // Espacement entre les boutons dans la VBox
        btnBox.alignment = Pos.CENTER

        this.top = bvnJoueur
        this.center = btnBox  // centre la VBox qui contient btnBox

    }
    fun setBienvenueMessage(nom: String, prenom: String) {
        bvnJoueur.text = "Bienvenue $nom $prenom"
    }
}