package vue

import javafx.scene.control.Button
import javafx.scene.control.Label
import javafx.scene.control.TextField
import javafx.scene.layout.BorderPane
import javafx.scene.layout.GridPane
import javafx.scene.layout.HBox
import javafx.scene.layout.VBox

class MatchList : BorderPane() {

    val contain1 : HBox
    val contain2 : VBox
    val listLabel : Label //vbox
    val containList : GridPane //vbox
    val validateBtn : Button
    val zoneIdPartie : TextField

    init{

        contain2 = VBox()
        contain1 = HBox()
        listLabel = Label("Liste des parties disponibles")
        this.top = listLabel
        containList = GridPane()
        zoneIdPartie = TextField()

        validateBtn = Button("Valider")


        contain2.children.addAll(zoneIdPartie,validateBtn)
        contain1.children.addAll(containList, contain2)
        this.center = contain1
    }

}