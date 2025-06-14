package vue

import javafx.scene.control.Label
import javafx.scene.control.TextField
import javafx.scene.layout.VBox

class EnterCode : VBox() {

    val texteentrercode : Label
    val code : TextField

    init{
        texteentrercode = Label("Entrez le code de la partie :")
        code = TextField()
    }


}