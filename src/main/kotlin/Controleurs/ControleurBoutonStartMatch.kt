package Controleurs

import javafx.event.ActionEvent
import javafx.event.EventHandler
import modele.Client
import modele.Match
import vue.GameBoard
import vue.MainView
import vue.MatchList
import vue.MatchMaking

class ControleurBoutonStartMatch(val client: Client, val mainView : MainView, val matchList: MatchList, val create : Boolean): EventHandler<ActionEvent> {

    override fun handle(event : ActionEvent){

        val match : Match

        if (create){
            match = client.matchCreate()
        }else{
            match = client.matchJoin(matchList.champID.toInt())
        }
        val gameBoard = GameBoard(client, mainView, match)

        mainView.center = gameBoard
    }
}