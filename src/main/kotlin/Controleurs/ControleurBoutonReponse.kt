package Controleurs

import javafx.event.ActionEvent
import javafx.event.EventHandler
import modele.Match
import vue.GameBoard

class ControleurBoutonReponse(val match : Match, val gameBoard : GameBoard) : EventHandler<ActionEvent> {
    override fun handle(event: ActionEvent) {

        match.putAnswer(gameBoard.zoneIdPerso.text)

        // Next view
        //val hideChara = HideCharacter()
        //gameBoard.setRightView(hideChara)



    }
}