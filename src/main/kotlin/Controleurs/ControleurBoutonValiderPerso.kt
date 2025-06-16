package Controleurs

import info.but1.sae2025.data.ETAPE
import javafx.event.ActionEvent
import javafx.event.EventHandler
import modele.Match
import vue.GameBoard

class ControleurBoutonValiderPerso (val match: Match, val view : GameBoard): EventHandler<ActionEvent> {

    override fun handle(event: ActionEvent) {

        if(match.getMatchState() != ETAPE.CREEE){
            var index = match.getListSelChar()[0]
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
            match.updateMatchState()
            match.resetListSelChar()
            view.center = match.updateGrid(view.gridCharacter, true)
            view.zoneIdPerso.text = ""
            match.charPickedNo = 1
        }
    }
}