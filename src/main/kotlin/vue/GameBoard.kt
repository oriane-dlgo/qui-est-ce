package vue

import Controleurs.ControleurBoutonValiderPerso
import info.but1.sae2025.data.ETAPE
import javafx.animation.Animation
import javafx.animation.KeyFrame
import javafx.animation.Timeline
import javafx.event.EventHandler
import javafx.scene.Node
import javafx.scene.control.Button
import javafx.scene.control.TextField
import javafx.scene.image.Image
import javafx.scene.image.ImageView
import javafx.scene.layout.BorderPane
import javafx.scene.layout.GridPane
import javafx.scene.layout.HBox
import javafx.scene.layout.Pane
import javafx.scene.layout.StackPane
import javafx.scene.layout.VBox
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

    var index: Int
    val zoneIdPerso : TextField

    init {
        this.match = match
        gridCharacter = GridPane()
        gridCharacter.isGridLinesVisible = true
        gridCharacter = match.updateGrid(gridCharacter, false)
        this.center = gridCharacter

        info = GridPane()
        this.right = info
        this.index = 0



        var photo = Rectangle(50.0, 50.0).apply {
            fill = Color.WHITE
            stroke = Color.BLACK
        }
        photoContainer = StackPane(photo)

        zoneIdPerso = TextField()

        this.viewContainer = Pane(WaitingPlayer())

        info.add(photoContainer, 0, 0)
        info.add(zoneIdPerso, 0, 1)
        info.add(viewContainer, 0, 2)

    }





    fun setRightView(newVue: javafx.scene.Node) {
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
