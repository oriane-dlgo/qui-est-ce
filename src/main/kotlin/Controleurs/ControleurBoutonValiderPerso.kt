package Controleurs

import javafx.event.ActionEvent
import javafx.event.EventHandler
import modele.Client
import modele.Match
import vue.GameBoard
import vue.Guess
import vue.MainView

class ControleurBoutonValiderPerso (val match: Match, val view : MainView, val view2 : GameBoard): EventHandler<ActionEvent> {

    override fun handle(event: ActionEvent){
        val guess = Guess()
        view2.setRightView(guess)
    }

}