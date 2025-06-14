package vue

import javafx.scene.control.Button
import javafx.scene.layout.BorderPane
import javafx.scene.layout.GridPane
import javafx.scene.layout.HBox
import javafx.scene.layout.VBox
import javafx.scene.paint.Color
import javafx.scene.shape.Rectangle

class GameBoard : BorderPane() {
    val gridCharactere: GridPane
    val info: VBox
    val photo: Rectangle
    val btnValid : Button

    init{
        gridCharactere = GridPane()
        this.center = gridCharactere
        gridCharactere.isGridLinesVisible = true
        info = VBox()
        this.right = info

        val total = 24
        val columns = 6
        for (i in 0 until total) {
            val row = i / columns
            val col = i % columns

            val cell = Rectangle(80.0, 80.0).apply {
                fill = Color.LIGHTGRAY
                stroke = Color.BLACK
            }

            gridCharactere.add(cell, col, row)
        }

        photo = Rectangle(50.0, 50.0).apply{
            fill = Color.WHITE
            stroke = Color.BLACK
        }

        btnValid = Button("Valider")

        info.children.addAll(photo, btnValid)

    }

    fun setRightView(newVue: javafx.scene.Node) {
        this.right = newVue
    }

}