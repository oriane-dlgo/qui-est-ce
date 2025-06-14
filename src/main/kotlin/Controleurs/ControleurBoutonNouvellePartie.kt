package Controleurs

import javafx.event.ActionEvent
import javafx.event.EventHandler
import modele.Client
import modele.Match
import vue.GameBoard
import vue.MainView
import vue.PickCharacter

class ControleurBoutonNouvellePartie(val client: Client, val view : MainView): EventHandler<ActionEvent> {

    override fun handle(event : ActionEvent){

        var currentMatch = client.matchCreate()

        val gameBoardView = GameBoard(currentMatch)
        val pickView = PickCharacter()
        view.setCenterView(gameBoardView)
        gameBoardView.setRightView(pickView)
        pickView.btnValid.setOnAction(ControleurBoutonValiderPerso(currentMatch, view, gameBoardView))

        // gameBoardView.btnvalid.setOnAction(ControleurBoutonValiderCode(client, view))


        println("Liste des partie sur le serveur : ${client.getMatchServerList()+currentMatch.getId()}")
        println("Etat de la partie : ${client.getMatchState()}")

    }
}