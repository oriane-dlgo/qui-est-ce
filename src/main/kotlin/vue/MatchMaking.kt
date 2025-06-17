package vue

import Controleurs.ControleurBoutonLogin
import info.but1.sae2025.data.IdentificationJoueur
import info.but1.sae2025.data.Joueur
import javafx.geometry.Insets
import javafx.geometry.Pos
import javafx.scene.control.Button
import javafx.scene.control.Label
import javafx.scene.layout.BorderPane
import javafx.scene.layout.StackPane
import javafx.scene.layout.VBox
import javafx.scene.text.Font
import javafx.scene.text.FontWeight
import kotlinx.serialization.descriptors.PrimitiveKind
import modele.Client
import ui.*

class MatchMaking : StackPane() {

    val backSquare : VBox
    val labelBvn : Label
    var player : Pair<String, String> //center
    val btnNew : Button //vbox
    val btnList : Button //vbox
    val btnReturn : Button

    init{

        player = Pair("" ,"")

        backSquare = VBox()
        backSquare.maxWidth = 500.0
        backSquare.maxHeight = 500.0
        backSquare.prefWidth = 500.0
        backSquare.prefHeight = 500.0
        backSquare.style = "-fx-background-color: rgba(255, 255, 255, 0.9); -fx-background-radius: 10px; -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.4), 10, 0.2, 0, 4);"
        backSquare.alignment = Pos.CENTER //centre la VBox qui contient "Entrer votre nom et prénom :"
        backSquare.padding = Insets(60.0)
        backSquare.spacing = 40.0

        labelBvn = createHomeLabel("Bienvenue ! ${player.first} ${player.first}")
        labelBvn.style = """
            -fx-font-size: 30px;
           
        """.trimIndent()

       // labelBvn.padding = Insets(30.0)


        btnNew = createMainButton("Nouvelle Partie")
        btnList = createMainButton("Liste des Parties")
        btnReturn = createMainButton("Retour")

        VBox.setMargin(btnReturn, Insets(30.0, 0.0, 0.0, 0.0))



        backSquare.children.addAll( labelBvn, btnNew, btnList, btnReturn)
        this.children.add(backSquare)

    }
    //fun setBienvenueMessage(nom: String, prenom: String) {
    //    bvnJoueur.text = "Bienvenue $nom $prenom"
    //}
}