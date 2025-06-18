package Controleurs

import javafx.event.ActionEvent
import javafx.event.EventHandler
import modele.Match
import vue.GameBoard


class ControleurBoutonHideOk(val match: Match, val gameBoard: GameBoard, val hide : Boolean) : EventHandler<ActionEvent> {
    override fun handle(p0: ActionEvent?) {

        match.updateKeyHide()
        match.updateKeyPass(0, true)
        match.updateKeySel(0)

        if (hide){
            val list = match.getListSelChar()
            match.addToListHideChar(list)
            gameBoard.gridCharacter = match.updateGrid( gameBoard.gridCharacter, true)//list
        }else{
            var idChar = match.getListSelChar()[0]
            match.makeGuess(idChar)
            gameBoard.gridCharacter = match.updateGrid( gameBoard.gridCharacter, true)//list
        }
        match.resetListSelChar(gameBoard)

    }
}




