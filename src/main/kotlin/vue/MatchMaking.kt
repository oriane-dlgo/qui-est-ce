package vue

import javafx.scene.control.Button
import javafx.scene.layout.VBox

class MatchMaking : VBox() {

    val btnBox : VBox //center
    val btnNew : Button //vbox
    val btnJoin : Button //vbox

    init{
        btnNew = Button("Nouvelle Partie")
        btnJoin = Button("Rejoindre une Partie")
        btnBox = VBox(10.0, btnNew, btnJoin)
        this.children.add(btnBox)
    }
}