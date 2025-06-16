package Controleurs

import javafx.event.ActionEvent
import javafx.event.EventHandler
import modele.Match
import vue.Answer
import vue.GameBoard

class ControleurBoutonReponse(val match : Match, val gameBoard : GameBoard, val answer : Answer, val btn : Int = 1) : EventHandler<ActionEvent> {


    override fun handle(event: ActionEvent) {

        if (btn == 1){
            match.putAnswer(answer.btnOui.text.toString().lowercase())
        }else{
            match.putAnswer(answer.btnNon.text.toString().lowercase())
        }

    }
}