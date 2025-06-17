package vue

import javafx.scene.control.Button
import javafx.scene.control.Label
import javafx.scene.layout.VBox
import javafx.scene.text.Font
import javafx.scene.text.FontWeight
import ui.createHomeLabel
import ui.createSecondButton

class HideCharacter(val answer : String) : VBox() {

    val labelResponse : Label
    val btnHide : Button
    val btnOk : Button
    val proposition : Button



    init{
        labelResponse = createHomeLabel("La réponse à \nla question est :\n$answer ")
        btnHide = createSecondButton("Cacher des personnages")
        btnOk = createSecondButton("Fin du tour")
        proposition = createSecondButton("Proposer une solution")

        this.children.addAll(labelResponse, btnHide, btnOk, proposition)
    }


}