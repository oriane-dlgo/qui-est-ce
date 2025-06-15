package Controleurs

import javafx.event.ActionEvent
import javafx.event.EventHandler
import modele.Client
import vue.GameBoard
import vue.MainView
import vue.MatchList
import vue.PickCharacter

class ControleurBoutonValiderPartie(val client : Client, val mainView : MainView, val matchListView : MatchList) : EventHandler<ActionEvent> {

    override fun handle(event : ActionEvent){

        // Rejoin un match
        client.matchJoin(matchListView.zoneIdPartie.text.toInt())

        //Switch vue
        val gameView = GameBoard(client.getCurrentMatch())
        val pickView = PickCharacter()
        mainView.setCenterView(gameView)
        gameView.setRightView(pickView)

        // Controleur de la prochaine vue
        pickView.btnValid.setOnAction(ControleurBoutonValiderPerso(client.getCurrentMatch(), gameView))

        println(client.getMatchState())

    }
}