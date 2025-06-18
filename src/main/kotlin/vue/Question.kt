package vue

import javafx.geometry.Insets
import javafx.geometry.Pos
import javafx.scene.control.Button
import javafx.scene.control.Label
import javafx.scene.control.TextArea
import javafx.scene.control.TextField
import javafx.scene.layout.VBox
import javafx.scene.text.Font
import javafx.scene.text.FontWeight
import ui.createSecondButton
import ui.createTextLabel

class Question : VBox() {
    val labelQst : Label
    val textArea : TextArea
    val btnOk : Button


    init{
        labelQst = createTextLabel("Pose ta question :")
        textArea = TextArea("")
        textArea.isWrapText = true
        textArea.maxHeight = 150.0
        btnOk = createSecondButton("Valider")

        children.addAll(labelQst,textArea,btnOk)

        this.alignment = Pos.CENTER
        this.spacing = 20.0
        this.padding = Insets(15.0)



    }


}
