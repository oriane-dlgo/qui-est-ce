package vue

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


    }


}