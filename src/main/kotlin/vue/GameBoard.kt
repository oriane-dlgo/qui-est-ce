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

class GameBoard(client : Client, mainView: MainView, match: Match) : BorderPane() {

    var globalContainer : HBox
    var gridPanel : VBox
    var gridCharacter: GridPane
    var rightPanel : VBox
    var character : VBox
    var pictureContainer: StackPane
    var picture : Rectangle
    var labelChar : Label
    var viewContainer : Pane
    val match : Match
    val charSelOnGrid : MutableList<Int>
    //var labelLog : Label
    val gameClock : GameClock

    var index: Int
   // val zoneIdPerso : TextField

    init {
        this.charSelOnGrid = mutableListOf()
        globalContainer = HBox()

        // LOG A SUPRIMER
        // labelLog = Label(match.printState(ETAPE.CREEE))
        // this.bottom = labelLog


        // CENTER PANEL - GRID
        gridPanel = VBox()
        gridPanel.maxHeight = 500.0
        gridPanel.prefHeight = 500.0
        gridPanel.style = "-fx-background-color: rgba(255, 255, 255, 0.9); -fx-background-radius: 10px; -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.4), 10, 0.2, 0, 4);"
        gridPanel.alignment = Pos.CENTER //centre la VBox qui contient "Entrer votre nom et prénom :"
        gridPanel.padding = Insets(20.0)


        gridCharacter = GridPane()
        gridCharacter.hgap = 5.0
        gridCharacter.vgap = 5.0

        /*
        gridCharacter.style = "-fx-background-color: rgba(255, 255, 255, 0.9); -fx-background-radius: 10px;"
        gridCharacter.maxWidth = 600.0
        gridCharacter.maxHeight = 430.0
        gridCharacter.prefWidth = 650.0
        gridCharacter.prefHeight = 400.0
        gridCharacter.isGridLinesVisible = true



        gridCharacter = gridCharacter.apply {
            prefWidth = 100.0
            prefHeight = 100.0
            background = Background(
                BackgroundFill(Color.rgb(255, 255, 255, 0.9), CornerRadii(10.0), Insets.EMPTY)
            )
        }

         */

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


        this.match = match
        this.gameClock = GameClock(match, this, mainView)
        this.match.updateGrid(gridCharacter, false)

        // RIGHT PANEL
        rightPanel = VBox()
        rightPanel.maxWidth = 250.0
        rightPanel.maxHeight = 500.0
        rightPanel.prefWidth = 250.0
        rightPanel.prefHeight = 500.0
        rightPanel.style = "-fx-background-color: rgba(255, 255, 255, 0.9); -fx-background-radius: 10px; -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.4), 10, 0.2, 0, 4);"
        rightPanel.alignment = Pos.CENTER //centre la VBox qui contient "Entrer votre nom et prénom :"
        rightPanel.padding = Insets(10.0)
        rightPanel.spacing = 40.0


        character = VBox()

        picture = Rectangle(100.0, 100.0).apply {
            fill = Color.WHITE
            stroke = Color.BLACK
        }

        pictureContainer = StackPane(picture)
        pictureContainer.setStyle("-fx-border-color: white; -fx-border-width: 1;")


        //pictureContainer.maxWidth = Double.MAX_VALUE
        //pictureContainer.maxHeight = Double.MAX_VALUE

        labelChar = Label()

        viewContainer = VBox(WaitingPlayer())
        viewContainer.maxHeight = 350.0
        viewContainer.prefHeight = 350.0


        character.children.addAll(pictureContainer, labelChar)
        rightPanel.children.addAll(character, viewContainer)


        character.padding = Insets(18.0)




        gridPanel.children.add(gridCharacter)

        globalContainer.children.addAll(gridPanel, rightPanel)
        globalContainer.alignment = Pos.CENTER
        globalContainer.spacing = 20.0

        this.index = 0
        this.center = globalContainer

    }
/*
    fun setRightView(newVue: Node) {
        val nodesToRemove = dynamicView.children.filter {
            GridPane.getColumnIndex(it) == 0 && GridPane.getRowIndex(it) == 3
        }
        dynamicView.children.removeAll(nodesToRemove)
        dynamicView.add(newVue, 0, 3)
    }
*/


    fun switchChildView(view : Pane){
        this.viewContainer.children.setAll(view)
    }

}


/*
                val cell = Rectangle(80.0, 80.0).apply {
                    fill = Color.LIGHTGRAY
                    stroke = Color.BLACK
                }
 */
