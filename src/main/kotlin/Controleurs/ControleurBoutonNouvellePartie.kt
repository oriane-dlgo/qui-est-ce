package Controleurs

import javafx.event.ActionEvent
import javafx.event.EventHandler
import modele.Client
import modele.Match
import vue.GameBoard
import vue.MainView
import vue.MatchMaking
import vue.PickCharacter

class ControleurBoutonNouvellePartie(val client: Client, val view : MainView, val matchMakingView : MatchMaking): EventHandler<ActionEvent> {

    override fun handle(event : ActionEvent){

        // Create match
        var match = client.matchCreate()

        // Switch view
        val gameBoardView = GameBoard(match)
        view.setCenterView(gameBoardView)

        // Launch GameClock
        matchMakingView.btnNew.setOnAction(GameClock(match, gameBoardView))
    }
}