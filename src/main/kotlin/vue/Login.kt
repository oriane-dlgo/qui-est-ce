package vue

import javafx.geometry.Insets
import javafx.geometry.Pos
import javafx.scene.control.Button
import javafx.scene.control.Label
import javafx.scene.control.TextField
import javafx.scene.layout.HBox
import javafx.scene.layout.VBox
import javafx.scene.text.Font
import javafx.scene.text.FontWeight
import ui.createMainButton
import ui.createSmallButton
import ui.createTitleLabel

class Login : VBox() {

    val dia1Question : Label //vbox
    val dialog11 : HBox //vbox
    val nom : TextField //hbox
    val prenom : TextField //hbox
    val btnLogin : Button //hbox

    init{

        dia1Question = createTitleLabel("Entrer votre nom et prénom :")


        nom = TextField("nom")
        nom.font = Font.font("Courier New", FontWeight.BOLD, 18.0)
        prenom = TextField("prénom")
        prenom.font = Font.font("Courier New", FontWeight.BOLD, 18.0)
        // nom = TextField().apply {
        //    promptText = "nom"
        //}                                Code permettant d'avoir "nom" et "prénom" en fond
        //prenom = TextField().apply {     des textfields
        //    promptText = "prénom"
        //}

        btnLogin = createMainButton("Valider")


        dialog11 = HBox(10.0, nom, prenom)


        // Ajout à la vue principale (this = VBox)
        this.children.addAll(dia1Question, dialog11, btnLogin)

        dialog11.alignment = Pos.CENTER
        dialog11.spacing = 20.0
        dialog11.padding = Insets(45.0)

        this.alignment = Pos.CENTER //centre la VBox qui contient "Entrer votre nom et prénom :"
        this.padding = Insets(60.0)
    }

}