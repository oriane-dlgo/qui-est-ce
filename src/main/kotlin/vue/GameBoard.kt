package vue

import javafx.scene.control.Button
import javafx.scene.control.TextField
import javafx.scene.image.Image
import javafx.scene.image.ImageView
import javafx.scene.layout.BorderPane
import javafx.scene.layout.GridPane
import javafx.scene.layout.HBox
import javafx.scene.layout.StackPane
import javafx.scene.layout.VBox
import javafx.scene.paint.Color
import javafx.scene.shape.Rectangle
import modele.Match

class GameBoard(match: Match) : BorderPane() {
    val gridCharactere: GridPane
    val info: GridPane
    val photo: Rectangle

    var index: Int
    val zoneIdPerso : TextField

    init {
        gridCharactere = GridPane()
        this.center = gridCharactere
        gridCharactere.isGridLinesVisible = true
        info = GridPane()
        this.right = info
        this.index = 0

        for (row in 0 until 4) {
            for (col in 0 until 6) {

                val baseUrl = "http://localhost:8080/resources/but1/"
                val filename = match.getGrid()[row][col].url
                val fullUrl = "$baseUrl$filename"
                val image = Image(fullUrl)

                val imageView = ImageView(image).apply {
                    fitWidth = 80.0
                    fitHeight = 80.0
                    isPreserveRatio = true
                }

                val stack = StackPane().apply {
                    children.add(imageView)
                    style = "-fx-border-color: black; -fx-border-width: 1;"
                }

                gridCharactere.add(stack, col, row)

            }
        }

        /*
                        val cell = Rectangle(80.0, 80.0).apply {
                            fill = Color.LIGHTGRAY
                            stroke = Color.BLACK
                        }
         */

        zoneIdPerso = TextField()
        photo = Rectangle(50.0, 50.0).apply {
            fill = Color.WHITE
            stroke = Color.BLACK
        }

        info.add(photo, 0, 0)
        info.add(zoneIdPerso, 0, 1)

    }

    fun setRightView(newVue: javafx.scene.Node) {
        info.add(newVue, 0, 3)
    }

}