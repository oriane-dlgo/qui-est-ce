package Controleurs

import javafx.event.ActionEvent
import javafx.event.EventHandler
import modele.Client
import vue.EnterCode
import vue.MainView

class ControleurBoutonRejoindrePartie(val modele : Client, val view : MainView) : EventHandler<ActionEvent> {

    // Cliquer sur ce bouton ne fait qu'afficher la vue ListPartie
    override fun handle(event: ActionEvent) {

        val codeView = EnterCode()
        view.setCenterView(codeView)
        codeView.btnvalid.setOnAction(ControleurBoutonValiderCode(modele, view))

        println("CONTROLEUR REJOINDRE")
    }
}