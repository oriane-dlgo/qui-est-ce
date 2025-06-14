package Controleurs

import javafx.event.ActionEvent
import javafx.event.EventHandler
import modele.Client
import vue.GameBoard
import vue.MainView

class ControleurBoutonNouvellePartie(val client: Client, val view : MainView): EventHandler<ActionEvent> {

    override fun handle(event : ActionEvent){

        var currentMatch = client.matchCreate()

        val gameBoardView = GameBoard(currentMatch)
        view.setCenterView(gameBoardView)
        // gameBoardView.btnvalid.setOnAction(ControleurBoutonValiderCode(client, view))



        println("Liste des partie sur le serveur : ${client.getMatchServerList()+currentMatch.getId()}")
        println("Etat de la partie : ${client.getMatchState()}")

    }
}