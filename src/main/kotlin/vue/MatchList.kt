package vue

import javafx.scene.control.Button
import javafx.scene.control.Label
import javafx.scene.control.TextField
import javafx.scene.layout.GridPane
import javafx.scene.layout.VBox

class MatchList : VBox() {


    val listLabel : Label //vbox
    val containList : GridPane //vbox
    val validateBtn : Button
    val zoneIdPartie : TextField

    init{

        listLabel = Label("Liste des parties disponibles")
        containList = GridPane()
        zoneIdPartie = TextField()

        validateBtn = Button("Valider")

        this.children.addAll(listLabel, containList, zoneIdPartie,validateBtn)
    }

}