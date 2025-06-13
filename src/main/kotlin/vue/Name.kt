package vue

import javafx.scene.control.Button
import javafx.scene.control.Label
import javafx.scene.control.TextField
import javafx.scene.layout.HBox
import javafx.scene.layout.VBox

class Name : VBox() {

    val dialog1 : VBox //center
    val dia1question : Label //vbox
    val dialog11 : HBox //vbox
    val nom : TextField //hbox
    val prenom : TextField //hbox
    val btndia1 : Button //hbox

    init{

        dia1question = Label("Entrer votre nom et prénom :")

        nom = TextField("nom")
        prenom = TextField("prénom")
        btndia1 = Button("Valider")
        dialog11 = HBox(10.0, nom, prenom, btndia1)
        dialog1 = VBox(10.0, dia1question, dialog11)

        // Ajout à la vue principale (this = VBox)
        this.children.add(dialog1)

    }

}