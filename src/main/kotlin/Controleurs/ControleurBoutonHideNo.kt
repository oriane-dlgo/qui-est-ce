package Controleurs

import javafx.event.ActionEvent
import javafx.event.EventHandler
import modele.Match
import vue.GameBoard


class ControleurBoutonHideNo(val match: Match, val gameBoard : GameBoard, val endOfRound : Boolean) : EventHandler<ActionEvent> {
    override fun handle(p0: ActionEvent?) {

        if (endOfRound){
            match.updateKeyPass(0, true)
            //match.updateKeyPass(7)
            match.endOfRound()
            match.resetListSelChar(gameBoard)
        }else{
            match.updateKeyPass(0, true)
            match.updateKeyHide()
            match.updateKeyHide()
        }


    }
}




