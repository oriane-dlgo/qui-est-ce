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

        val list = gameBoard.zoneIdPerso.text          // VERSION DEV - EN ATTENDANT (MerciGPT<3)
            .trim()                                 // Supprime les espaces en début/fin
            .split(" ")                 // Coupe la chaîne à chaque espace
            .mapNotNull { it.toIntOrNull() }        // Convertit chaque élément en Int (en filtrant les valeurs non valides)

        //match.hideCharacter(list)

        gameBoard.center = match.updateGrid( gameBoard.gridCharacter, true , list)


    }
}




