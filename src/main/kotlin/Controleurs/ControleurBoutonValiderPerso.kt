package Controleurs

import javafx.event.ActionEvent
import javafx.event.EventHandler
import javafx.scene.layout.StackPane
import modele.Client
import modele.Match
import vue.GameBoard
import vue.Guess
import vue.MainView

class ControleurBoutonValiderPerso(val match: Match, val view: GameBoard) : EventHandler<ActionEvent> {

    override fun handle(event: ActionEvent) {
        val guess = Guess()
        view.setRightView(guess)

        var index = view.zoneIdPerso.text.toInt()
        var i: Int = 0

        println("index = ${view.zoneIdPerso.text.toInt()}")
        for (row in 0 until 4) {
            for (col in 0 until 6) {
                i++
                if (i == index) {

                    match.pickCharacter(row, col)
                    view.photoContainer.children.clear()
                    view.photoContainer.children.add(match.getPictureOf(row, col))
                    view.zoneIdPerso.text = ""

                    
                    view.gridCharactere


                }
            }
        }
    }
}