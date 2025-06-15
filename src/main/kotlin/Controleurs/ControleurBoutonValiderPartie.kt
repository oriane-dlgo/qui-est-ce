package Controleurs

import javafx.event.ActionEvent
import javafx.event.EventHandler
import modele.Client
import vue.GameBoard
import vue.MainView
import vue.MatchList
import vue.MatchMaking
import vue.PickCharacter

class ControleurBoutonValiderPartie(val client : Client, val mainView : MainView, val matchListView : MatchList) : EventHandler<ActionEvent> {

    override fun handle(event : ActionEvent){

        // Rejoin un match
        var match = client.matchJoin(matchListView.champID.toInt())

        //Switch vue
        val gameBoardView = GameBoard(client.getCurrentMatch())
        val pickView = PickCharacter()
        mainView.setCenterView(gameBoardView)
        gameBoardView.setRightView(pickView)

        // Launch GameClock
        matchListView.joinBtn.setOnAction(GameClock(match, gameBoardView))

        // Controleur de la prochaine vue
        pickView.btnValid.setOnAction(ControleurBoutonValiderPerso(client.getCurrentMatch(), gameBoardView))

        println(client.getMatchState())

    }
}