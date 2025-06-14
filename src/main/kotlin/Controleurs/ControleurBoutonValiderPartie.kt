package Controleurs

import javafx.event.ActionEvent
import javafx.event.EventHandler
import modele.Client
import vue.EnterCode
import vue.GameBoard
import vue.MainView

class ControleurBoutonValiderPartie(val client : Client, val view : MainView) : EventHandler<ActionEvent> {

    override fun handle(event : ActionEvent){
        val gameView = GameBoard(client.getCurrentMatch())

        view.setCenterView(gameView)

    }
}