package vue

import Controleurs.ControleurBoutonValiderPerso
import Controleurs.GameClock
import info.but1.sae2025.QuiEstCeClient
import info.but1.sae2025.data.ETAPE
import javafx.animation.Animation
import javafx.animation.KeyFrame
import javafx.animation.Timeline
import javafx.event.EventHandler
import javafx.geometry.Insets
import javafx.geometry.Pos
import javafx.scene.Node
import javafx.scene.control.Button
import javafx.scene.control.Label
import javafx.scene.control.TextField
import javafx.scene.image.Image
import javafx.scene.image.ImageView
import javafx.scene.layout.*
import javafx.scene.paint.Color
import javafx.scene.shape.Rectangle
import javafx.util.Duration
import modele.Client
import modele.Match
import ui.createSecondButton

class GameBoard(client : Client, mainView: MainView, match: Match) : BorderPane() {

    var globalContainer : HBox
    var match : Match
    var gridPanel : VBox
    var gridCharacter: GridPane
    var rightPanel : VBox
    var character : VBox
    var pictureContainer: StackPane
    var picture : Rectangle
    var labelChar : Label
    var viewContainer : VBox
    var btnAgain : Button = createSecondButton("Recommencer")
    val gameClock : GameClock
    val labelIdMatch : Label

    init {

        this.match = match
        this.gameClock = GameClock(client, match, this, mainView)

        globalContainer = HBox()

        // CENTER PANEL - GRID
        gridPanel = VBox()
        gridPanel.maxHeight = 500.0
        gridPanel.prefHeight = 500.0
        gridPanel.minWidth = 700.0
        gridPanel.prefWidth = 700.0
        gridPanel.style = "-fx-background-color: rgba(255, 255, 255, 0.9); -fx-background-radius: 10px; -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.4), 10, 0.2, 0, 4);"
        gridPanel.alignment = Pos.CENTER
        gridPanel.padding = Insets(20.0)

        gridCharacter = GridPane().apply {
            hgap = 5.0
            vgap = 5.0
        }
        //Contraintes des 6 colonnes, sur les 6 colonnes du grid, on va occuper tout l'espace dispo et comme ça c'est responsive
        repeat(6) {
            val col = ColumnConstraints().apply {
                percentWidth = 100.0 / 6 // divise l'espace en 6
                hgrow = Priority.ALWAYS
            }
            gridCharacter.columnConstraints.add(col)
        }
        //Contraintes des 4 lignes, idem que contrainte colonne mais avec les lignes
        repeat(4) {
            val row = RowConstraints().apply {
                percentHeight = 100.0 / 4 // divise l'espace en 4
                vgrow = Priority.ALWAYS
            }
            gridCharacter.rowConstraints.add(row)
        }
        this.match.updateGrid(gridCharacter, false)

        // RIGHT PANEL
        rightPanel = VBox()
        rightPanel.maxWidth = 250.0
        rightPanel.maxHeight = 500.0
        rightPanel.prefWidth = 250.0
        rightPanel.prefHeight = 500.0
        rightPanel.style = "-fx-background-color: rgba(255, 255, 255, 0.9); -fx-background-radius: 10px; -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.4), 10, 0.2, 0, 4);"
        rightPanel.alignment = Pos.CENTER
        rightPanel.padding = Insets(20.0)
        rightPanel.spacing = 0.0

        character = VBox()

        picture = Rectangle(100.0, 100.0).apply {
            fill = Color.WHITE
            stroke = Color.BLACK
        }
        pictureContainer = StackPane(picture)

        labelChar = Label()

        viewContainer = VBox(WaitingPlayer())
        viewContainer.maxHeight = 350.0
        viewContainer.prefHeight = 350.0

        labelIdMatch = Label("ID partie : ${ match.getMatchId().toString() }").apply {
            style = "-fx-font-family: \"Satisfy\";"
        }


        // ADD
        character.children.addAll(pictureContainer, labelChar)
        character.padding = Insets(18.0)
        character.alignment = Pos.CENTER

        rightPanel.children.addAll(character, viewContainer, labelIdMatch)

        gridPanel.children.add(gridCharacter)

        globalContainer.children.addAll(gridPanel, rightPanel)
        globalContainer.alignment = Pos.CENTER
        globalContainer.spacing = 20.0

        this.center = globalContainer

    }
    fun switchChildView(view : Pane){
        this.viewContainer.children.setAll(view)
    }
    fun switchEndView(view : VBox){
        this.gridPanel.children.setAll(view)
    }
    fun initBtnAgain(){
        this.viewContainer.children.clear()
        this.viewContainer.children.add(btnAgain)
        viewContainer. alignment = Pos.CENTER
        viewContainer.padding = Insets(50.0, 0.0, 0.0, 0.0)
    }
}