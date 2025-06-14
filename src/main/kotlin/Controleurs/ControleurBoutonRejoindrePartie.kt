package Controleurs

import javafx.event.ActionEvent
import javafx.event.EventHandler
import modele.Client
import vue.EnterCode
import vue.MainView

class ControleurBoutonRejoindrePartie(val modele : Client, val view : MainView) : EventHandler<ActionEvent> {

    override fun handle(event: ActionEvent) {

        val codeview = EnterCode()

        view.setCenterView(codeview)


        println("CONTROLEUR Rejoindre")

        codeview.btnvalid.setOnAction(ControleurBoutonValiderCode(modele, view))

        //pour passer à la vue Listpartie via le bouton btnjoin



    }
}