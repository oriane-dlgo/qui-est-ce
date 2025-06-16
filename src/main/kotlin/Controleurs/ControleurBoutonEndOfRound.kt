package Controleurs

import javafx.event.ActionEvent
import javafx.event.EventHandler
import modele.Match
import vue.GameBoard


class ControleurBoutonEndOfRound(val match: Match, val gameBoard: GameBoard) : EventHandler<ActionEvent> {
    override fun handle(p0: ActionEvent?) {

        //match.nextRound(true)
        //match.updateKeyPass(7)

        //match.updateKeyPass(0, true)
        match.endOfRound()
        match.resetListSelChar(gameBoard)
    }
}




