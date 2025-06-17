package Controleurs

import javafx.event.ActionEvent
import javafx.event.EventHandler
import modele.Client
import modele.Match
import vue.GameBoard
import vue.HideCharacter
import vue.MainView
import vue.Login


class ControleurBoutonHide(val match: Match, val gameBoard: GameBoard) : EventHandler<ActionEvent> {
    override fun handle(p0: ActionEvent?) {

        val list = match.getListSelChar()
        gameBoard.gridCharacter = match.updateGrid( gameBoard.gridCharacter, true , list)
    }
}




