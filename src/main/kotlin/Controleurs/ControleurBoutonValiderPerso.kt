package Controleurs

import info.but1.sae2025.data.ETAPE
import javafx.event.ActionEvent
import javafx.event.EventHandler
import modele.Client
import modele.Match
import vue.GameBoard
import vue.Guess
import vue.MainView

class ControleurBoutonValiderPerso (val match: Match, val view : GameBoard): EventHandler<ActionEvent> {

    override fun handle(event: ActionEvent) {

        if(match.getMatchState() == ETAPE.CREEE){
            println("L'adversaire n'est pas encore arrivé")
            // Bouton valider a desactiver
        }
        else {
            var index = view.zoneIdPerso.text.toInt()
            var i: Int = 0

            for (row in 0 until 4) {
                for (col in 0 until 6) {
                    i++
                    if (i == index) {
                        match.pickCharacter(row, col)
                        view.photoContainer.children.clear()
                        view.photoContainer.children.add(match.getPictureOf(row, col, false))
                    }
                }
            }
            view.center = match.updateGrid(view.gridCharacter, true)
            view.zoneIdPerso.text = ""
            match.charPicked = 1
        }
    }
}