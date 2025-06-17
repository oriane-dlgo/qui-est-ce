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

    var player : Pair<String, String> //center

    val backSquare : StackPane
    val content : VBox
    val labelBvn : Label
    val btnNew : Button //vbox
    val btnList : Button //vbox
    var containerReturnBtn : StackPane
    val btnReturn : Button

    init{

        player = Pair("" ,"")

        backSquare = StackPane()
        backSquare.maxWidth = 500.0
        backSquare.maxHeight = 500.0
        backSquare.prefWidth = 500.0
        backSquare.prefHeight = 500.0
        backSquare.style = "-fx-background-color: rgba(255, 255, 255, 0.9); -fx-background-radius: 10px; -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.4), 10, 0.2, 0, 4);"
        backSquare.padding = Insets(20.0)


        content = VBox().apply {
            alignment = Pos.CENTER
            spacing = 40.0
            padding = Insets(0.0, 0.0, 50.0, 0.0)
        }


        labelBvn = createHomeLabel("Bienvenue ! ${player.first} ${player.first}").apply {
            style = """ -fx-font-size: 30px; """
        }

       // labelBvn.padding = Insets(30.0)


        btnNew = createMainButton("Nouvelle Partie")
        btnList = createMainButton("Liste des Parties")

        containerReturnBtn = StackPane()
        btnReturn = createBackButton()

        containerReturnBtn.children.add(btnReturn)


        content.children.addAll( labelBvn, btnNew, btnList)
        backSquare.children.addAll(content, btnReturn)
        StackPane.setAlignment(btnReturn, Pos.TOP_LEFT)

        this.children.addAll(backSquare)

    }
    fun setBienvenueMessage( prenom: String) {
        labelBvn.text = "Bienvenue $prenom !"
    }
}