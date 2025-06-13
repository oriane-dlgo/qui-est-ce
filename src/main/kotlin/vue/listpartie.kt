package vue

import javafx.scene.control.Button
import javafx.scene.control.Label
import javafx.scene.layout.GridPane
import javafx.scene.layout.VBox

class listpartie : VBox() {


    val listlabel : Label //vbox
    val containlist : GridPane //vbox
    val btncode : Button //right

    init{

        listlabel = Label("Liste des parties disponibles")
        containlist = GridPane()
        btncode = Button("Entrer Code")

        this.children.addAll(listlabel, containlist, btncode)
    }

}