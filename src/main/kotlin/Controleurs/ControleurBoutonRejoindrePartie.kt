package Controleurs

import javafx.event.ActionEvent
import javafx.event.EventHandler
import javafx.scene.control.Label
import modele.Client
import vue.GameBoard
import vue.MainView
import vue.MatchList
import vue.MatchMaking

class ControleurBoutonRejoindrePartie(val client : Client, val view : MainView, val matchList: MatchList) : EventHandler<ActionEvent> {

    // Cliquer sur ce bouton affiche la vue ListPartie
    override fun handle(event: ActionEvent) {
        view.center = matchList
        client.updateMatchList()
    }
}