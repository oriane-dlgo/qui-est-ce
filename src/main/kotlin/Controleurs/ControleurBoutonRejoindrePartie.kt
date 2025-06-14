package Controleurs

import javafx.event.ActionEvent
import javafx.event.EventHandler
import modele.Client
import vue.MainView
import vue.MatchList

class ControleurBoutonRejoindrePartie(val modele : Client, val view : MainView) : EventHandler<ActionEvent> {

    // Cliquer sur ce bouton ne fait qu'afficher la vue ListPartie
    override fun handle(event: ActionEvent) {

        val matchView = MatchList()
        view.setCenterView(matchView)
        matchView.validateBtn.setOnAction(ControleurBoutonValiderPartie(modele, view))

        println("CONTROLEUR REJOINDRE")
    }
}