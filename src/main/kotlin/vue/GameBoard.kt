package vue

import Controleurs.ControleurBoutonValiderPerso
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
import modele.Match

class GameBoard(match: Match) : BorderPane() {
    var gridCharacter: GridPane
    val info: GridPane
    var photoContainer: StackPane
    var viewContainer : Pane
    val match : Match
    val charSelOnGrid : MutableList<Int>
    var labelLog : Label

    var index: Int
    val zoneIdPerso : TextField

    init {
        this.match = match
        gridCharacter = GridPane()

        gridCharacter.maxWidth = 600.0
        gridCharacter.maxHeight = 400.0
        gridCharacter.prefWidth = 600.0
        gridCharacter.prefHeight = 400.0

        gridCharacter.isGridLinesVisible = true
        gridCharacter = gridCharacter.apply {
            prefWidth = 100.0
            prefHeight = 100.0
            background = Background(
                BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)
            )
        }
        match.updateGrid(gridCharacter, false)


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

        labelLog = Label(match.printState(ETAPE.CREEE))
        this.center = gridCharacter
        this.bottom = labelLog
        this.charSelOnGrid = mutableListOf()

        info = GridPane().apply{
            vgap = 20.0
            padding = Insets(70.0)
            hgap = 50.0

        }
        this.right = info
        this.index = 0



        var photo = Rectangle(80.0, 80.0).apply {
            fill = Color.WHITE
            stroke = Color.BLACK
        }
        photoContainer = StackPane(photo)
        photoContainer.maxWidth = Double.MAX_VALUE
        photoContainer.maxHeight = Double.MAX_VALUE

        zoneIdPerso = TextField()

        this.viewContainer = Pane(WaitingPlayer())

        info.add(photoContainer, 0, 0)
        info.add(zoneIdPerso, 0, 1)
        info.add(viewContainer, 0, 2)
        info.alignment = Pos.CENTER
    }

    fun setRightView(newVue: Node) {
        val nodesToRemove = info.children.filter {
            GridPane.getColumnIndex(it) == 0 && GridPane.getRowIndex(it) == 3
        }
        info.children.removeAll(nodesToRemove)
        info.add(newVue, 0, 3)
    }

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
