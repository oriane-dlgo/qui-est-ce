package Controleurs

import javafx.event.ActionEvent
import javafx.event.EventHandler
import modele.Match
import vue.GameBoard
import vue.MainView

class ControleurBoutonQuestion(val modele : Match, val view : MainView, val view2 : GameBoard) : EventHandler<ActionEvent> {
    override fun handle(event: ActionEvent) {

    }
}