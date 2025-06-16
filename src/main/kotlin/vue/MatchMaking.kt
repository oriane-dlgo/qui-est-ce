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
import ui.createMainButton

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


        btnNew = createMainButton("Nouvelle Partie")

        btnNew.padding = Insets(15.0)  // Modifie la taille du fond du bouton

        btnJoin = createMainButton("Rejoindre une Partie")
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