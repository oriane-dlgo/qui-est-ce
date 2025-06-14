package vue

import javafx.scene.control.Button
import javafx.scene.control.Label
import javafx.scene.control.TextField
import javafx.scene.layout.VBox

class EnterCode : VBox() {

    val textEntrerCode : Label
    val code : TextField
    val btnValid : Button

    init{
        textEntrerCode = Label("Entrez le code de la partie :")
        code = TextField()
        btnValid = Button("Valider le code")
        this.children.addAll(textEntrerCode, code, btnValid)
    }


}