package Controleurs

import javafx.event.ActionEvent
import javafx.event.EventHandler
import modele.Client
import vue.EnterCode
import vue.GameBoard
import vue.MainView

class ControleurBoutonNouvellePartie(val client: Client, val view : MainView): EventHandler<ActionEvent> {

    override fun handle(event : ActionEvent){

        val gameBoardView = GameBoard()
        view.setCenterView(gameBoardView)
        // gameBoardView.btnvalid.setOnAction(ControleurBoutonValiderCode(client, view))

        client.matchCreate()

        println("Liste des partie sur le serveur : ${client.getMatchServerList()}")

    }
}