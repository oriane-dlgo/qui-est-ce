package vue

import javafx.scene.control.Button
import javafx.scene.control.Label
import javafx.scene.control.TextField
import javafx.scene.layout.VBox

class EnterCode : VBox() {

    val textentrercode : Label
    val code : TextField
    val btnvalid : Button

    init{
        textentrercode = Label("Entrez le code de la partie :")
        code = TextField()
        btnvalid = Button("Valider le code")
        this.children.addAll(textentrercode, code, btnvalid)
    }


}