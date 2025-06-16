package Controleurs

import javafx.event.ActionEvent
import javafx.event.EventHandler
import modele.Match
import vue.GameBoard

class ControleurBoutonGuess(val match : Match, val gameBoard : GameBoard) : EventHandler<ActionEvent> {
    override fun handle(event: ActionEvent) {

        match.makeGuess(match.getListSelChar()[0])
        match.resetListSelChar(gameBoard)
    }
}