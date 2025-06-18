package Controleurs

import javafx.event.ActionEvent
import javafx.event.EventHandler
import javafx.scene.control.Label
import modele.Match
import ui.createNomLabel
import ui.createTextLabel
import ui.createTitleLabel
import vue.GameBoard

class ControleurBoutonValiderPerso(val match: Match, val gameBoard: GameBoard) : EventHandler<ActionEvent> {

    override fun handle(event: ActionEvent) {
        // if (match.getMatchState() != ETAPE.CREEE)
        val position = match.getRowCol(match.getListSelChar()[0])


        if (position != null) {
            val (row, col) = position
            println("Ligne : $row, Colonne : $col")

            var index = match.getListSelChar()[0]
            var i: Int = 0
            for (row in 0 until 4) {
                for (col in 0 until 6) {
                    i++
                    if (i == index) {
                        val personnage = match.getGrid()[row][col]
                        val nomComplet = "${personnage.prenom} ${personnage.nom}"
                        val nom = createNomLabel(nomComplet)

                        match.pickCharacter(row, col)


                        gameBoard.pictureContainer.children.clear()
                        gameBoard.pictureContainer.children.add(match.getPictureOf(row, col, false))
                        gameBoard.character.children.add(nom)



                    }
                }
            }

            //match.updateMatchState()
            match.resetListSelChar(gameBoard)
            gameBoard.gridCharacter = match.updateGrid(gameBoard.gridCharacter, true)
            match.charPickedNo = 1

        }
    }
}

