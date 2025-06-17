package vue

import javafx.geometry.Insets
import javafx.geometry.Pos
import javafx.scene.control.Button
import javafx.scene.control.TextField
import javafx.scene.layout.VBox
import javafx.scene.text.Font
import javafx.scene.text.FontWeight
import ui.createSecondButton

class Question : VBox() {
    val textField : TextField
    val question : Button


    init{
        textField = TextField("")
        question = createSecondButton("Valider votre question")
        children.addAll(question, textField)
        this.alignment = Pos.CENTER
        this.spacing = 40.0
        this.padding = Insets(15.0)



    }


}