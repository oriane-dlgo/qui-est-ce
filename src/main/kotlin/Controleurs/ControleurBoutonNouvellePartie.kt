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

        var match = client.matchCreate()

        val gameBoardView = GameBoard(match)
        view.setCenterView(gameBoardView)

        // Launch GameClock
        matchMakingView.btnNew.setOnAction(GameClock(match, gameBoardView))

        //pickView.btnValid.setOnAction(ControleurBoutonValiderPerso(currentMatch, gameBoardView))

        // gameBoardView.btnvalid.setOnAction(ControleurBoutonValiderCode(client, view))

        println("Liste des partie sur le serveur : ${client.getMatchServerList()+match.getId()}")
        println("Etat de la partie : ${client.getMatchState()}")

    }
}