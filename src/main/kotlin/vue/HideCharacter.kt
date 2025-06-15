package vue

import javafx.scene.control.Button
import javafx.scene.control.Label
import javafx.scene.layout.VBox

class HideCharacter : VBox() {

    val labelResponse : Label
    val btnHide : Button
    val btnOk : Button



    init{
        labelResponse = Label("La réponse à la question est : ")
        btnHide = Button("Cacher des personnages")
        btnOk = Button("Compris")

        this.children.addAll(labelResponse, btnHide, btnOk)
    }


}