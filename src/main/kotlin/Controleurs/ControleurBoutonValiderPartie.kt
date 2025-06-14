package Controleurs

import javafx.event.ActionEvent
import javafx.event.EventHandler
import modele.Client
import vue.EnterCode
import vue.GameBoard
import vue.MainView
import vue.MatchList

class ControleurBoutonValiderPartie(val client : Client, val view : MainView, val matchListView : MatchList) : EventHandler<ActionEvent> {

    override fun handle(event : ActionEvent){

        client.matchJoin(matchListView.zoneIdPartie.text.toInt())
        val gameView = GameBoard(client.getCurrentMatch())

        view.setCenterView(gameView)

    }
}