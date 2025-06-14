package vue

import javafx.scene.control.Button
import javafx.scene.control.Label
import javafx.scene.layout.GridPane
import javafx.scene.layout.VBox

class Listpartie : VBox() {


    val listlabel : Label //vbox
    val containlist : GridPane //vbox


    init{

        listlabel = Label("Liste des parties disponibles")
        containlist = GridPane()


        this.children.addAll(listlabel, containlist)
    }

}