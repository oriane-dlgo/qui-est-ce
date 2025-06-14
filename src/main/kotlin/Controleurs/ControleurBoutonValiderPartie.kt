package Controleurs

import javafx.event.ActionEvent
import javafx.event.EventHandler
import modele.Client
import vue.EnterCode
import vue.GameBoard
import vue.MainView

class ControleurBoutonValiderPartie(val modele : Client, val view : MainView) : EventHandler<ActionEvent> {

    override fun handle(event : ActionEvent){
        val gameView = GameBoard()

        view.setCenterView(gameView)

    }
}