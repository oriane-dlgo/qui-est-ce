package Controleurs

import javafx.event.ActionEvent
import javafx.event.EventHandler
import modele.Match
import vue.GameBoard
import vue.GetResponse
import vue.MainView

class ControleurBoutonQuestion(val match : Match, val gameBoard : GameBoard) : EventHandler<ActionEvent> {
    override fun handle(event: ActionEvent) {

        match.putQuestion(gameBoard.zoneIdPerso.text)
    }
}