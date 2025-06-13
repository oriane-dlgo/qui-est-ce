package vue

import javafx.scene.control.Button
import javafx.scene.control.Label
import javafx.scene.control.TextField
import javafx.scene.layout.BorderPane
import javafx.scene.layout.GridPane
import javafx.scene.layout.HBox
import javafx.scene.layout.VBox


class deletewhendone: BorderPane() {

    //var accueil
    val title : Label //root.top
    val btn1 : Button //center

    //var dialogue1 après accueil
    val dialog1 : VBox //center
    val dia1question : Label //vbox
    val dialog11 : HBox //vbox
    val nom : TextField //hbox
    val prenom : TextField //hbox
    val btndia1 : Button //hbox

    //var accueil après dialogue1 (mise à jour partie)
    val btnbox : VBox //center
    val btnnew : Button //vbox
    val btnjoin : Button //vbox

    //var page partie
    val listbox : VBox //center
    val listlabel : Label //vbox
    val containlist : GridPane //vbox
    val btncode : Button //right

    //var dialogue2 (voir draw.io)
    val codebox : VBox //center
    val codelabel : Label //vbox
    val entercode : TextField //vbox
    val btnvalid : Button



    init{
        //initialisation var accueil
        title = Label("C KI LUI")
        btn1 = Button("Connexion")
        this.top = title
        this.center = btn1

        //initialisation var dialogue1
        dialog1 = VBox()
        dia1question = Label("Entrer votre nom et prénom :")
        dialog11 = HBox()
        nom = TextField("nom")
        prenom = TextField("prénom")
        btndia1 = Button("Valider")

        //initialisation var mise a jour partie accueil
        btnbox = VBox()
        btnnew = Button("Nouvelle Partie")
        btnjoin = Button("Rejoindre une Partie")

        //initialisation var page partie
        listbox = VBox()
        listlabel = Label("Liste des parties disponibles")
        containlist = GridPane()
        btncode = Button("Entrer Code")

        //initialisation var dialogue2
        codebox = VBox()
        codelabel = Label("Entrer le code de la partie")
        entercode = TextField("")
        btnvalid = Button("Valider")


    }


    fun updatecenter() {
        TODO()
    }



}
