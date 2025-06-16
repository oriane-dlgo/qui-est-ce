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
import kotlin.apply

class Login : VBox() {


    val dia1Question : Label //vbox
    val dialog11 : HBox //vbox
    val nom : TextField //hbox
    val prenom : TextField //hbox
    val btndia1 : Button //hbox

    init{

        dia1Question = Label("Entrer votre nom et prénom :")

        // Met en place la couleur, la police d'écriture et la taille
        dia1Question.style = "-fx-text-fill: white"
        dia1Question.font = Font.font("Courier New", FontWeight.BOLD, 35.0)

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

        btndia1 = Button("Valider")
        btndia1.style = "-fx-background-color: #61888c; -fx-text-fill: white"      // Couleur du fond et de l'écriture
        btndia1.setOnMouseEntered {
            btndia1.style = "-fx-background-color: #4e6b6e; -fx-text-fill: black;" // Couleur du fond et de l'écriture lorsque la souris passe dessus
        }
        btndia1.setOnMouseExited {
            btndia1.style = "-fx-background-color: #61888c; -fx-text-fill: white;" // Couleur du fond et de l'écriture lorsque la souris n'est plus dessus
        }
        btndia1.font = Font.font("Courier New", FontWeight.BOLD, 20.0) // Change la police et la taille


        dialog11 = HBox(10.0, nom, prenom)


        // Ajout à la vue principale (this = VBox)
        this.children.addAll(dia1Question, dialog11, btndia1)

        dialog11.alignment = Pos.CENTER
        dialog11.spacing = 20.0
        dialog11.padding = Insets(45.0)

        this.alignment = Pos.CENTER //centre la VBox qui contient "Entrer votre nom et prénom :"
        this.padding = Insets(60.0)
    }

}