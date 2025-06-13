package vue

import javafx.scene.control.Button
import javafx.scene.layout.VBox

class Game : VBox() {

    val btnbox : VBox //center
    val btnnew : Button //vbox
    val btnjoin : Button //vbox

    init{
        btnnew = Button("Nouvelle Partie")
        btnjoin = Button("Rejoindre une Partie")
        btnbox = VBox(10.0, btnnew, btnjoin)
        this.children.add(btnbox)
    }
}