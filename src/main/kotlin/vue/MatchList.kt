package vue

import javafx.scene.control.Button
import javafx.scene.control.Label
import javafx.scene.layout.GridPane
import javafx.scene.layout.VBox

class MatchList : VBox() {


    val listlabel : Label //vbox
    val containlist : GridPane //vbox
    val validateBtn : Button


    init{

        listlabel = Label("Liste des parties disponibles")
        containlist = GridPane()
        validateBtn = Button("Valider")


        this.children.addAll(listlabel, containlist, validateBtn)
    }

}