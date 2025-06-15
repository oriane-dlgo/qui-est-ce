package vue

import javafx.scene.control.Button
import javafx.scene.control.Label
import javafx.scene.layout.HBox
import javafx.scene.layout.VBox

class Proposition : VBox() {
    val propoLabel : Label
    val btn : HBox
    val btnOui : Button
    val btnNon : Button



    init{
        propoLabel = Label("C'est le personnage de ton adversaire?")
        btn = HBox()
        btnOui = Button("Oui")
        btnNon = Button("Non")

        btn.children.addAll(btnOui, btnNon)
        this.children.addAll(propoLabel, btn)

    }
}