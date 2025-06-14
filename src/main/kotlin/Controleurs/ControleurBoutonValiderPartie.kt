package Controleurs

import javafx.event.ActionEvent
import javafx.event.EventHandler
import modele.Client
import vue.GameBoard
import vue.MainView
import vue.MatchList
import vue.PickCharacter

class ControleurBoutonValiderPartie(val client : Client, val view : MainView, val matchListView : MatchList) : EventHandler<ActionEvent> {

    override fun handle(event : ActionEvent){

        client.matchJoin(matchListView.zoneIdPartie.text.toInt())
        val gameView = GameBoard(client.getCurrentMatch())
        val pickView = PickCharacter()
        view.setCenterView(gameView)
        gameView.setRightView(pickView)
        pickView.btnValid.setOnAction(ControleurBoutonValiderPerso(client.getCurrentMatch(), view, gameView))


    }
}