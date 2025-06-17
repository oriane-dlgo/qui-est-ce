package Controleurs

import javafx.event.ActionEvent
import javafx.event.EventHandler
import modele.Match
import vue.GameBoard
import vue.Question
import javax.management.QueryExp

class ControleurBoutonQuestion(val match : Match, val gameBoard : GameBoard, val question : Question) : EventHandler<ActionEvent> {
    override fun handle(event: ActionEvent) {

        match.putQuestion(question.question.text)
    }
}