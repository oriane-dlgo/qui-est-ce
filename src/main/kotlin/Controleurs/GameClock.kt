package Controleurs

import info.but1.sae2025.data.ETAPE
import javafx.animation.Animation
import javafx.animation.KeyFrame
import javafx.animation.Timeline
import javafx.event.ActionEvent
import javafx.event.EventHandler
import javafx.util.Duration
import modele.Match
import vue.GameBoard
import vue.Guess
import vue.PickCharacter
import vue.WaitingPlayer

class GameClock(val match: Match, val gameBoard: GameBoard) : EventHandler<ActionEvent> {

    init {
        val waitingPlayer = WaitingPlayer()


        val timeline = Timeline(
            KeyFrame(Duration.seconds(4.0), EventHandler {
                match.updateMatchState()
                match.printState()

                if (match.getMatchState() == ETAPE.CREEE) {
                    // ***** INIT *****
                    // STATE : La partie vient de se lancer et le joueur 1 est seul
                    // DO : Affiche la vue d'attente d'adversaire
                    println("Attente du joueur adverse")

                }
                if (match.getMatchState() == ETAPE.INITIALISATION && match.charPicked == -1) {
                    // ***** PICK CHAR *****
                    // STATE : Le deuxieme joueur vient de rejoindre
                    // DO : Affiche la vue pour la selection de perso
                    val pickView = PickCharacter()
                    gameBoard.switchChildView(pickView)
                    pickView.btnValid.onAction = ControleurBoutonValiderPerso(match, gameBoard)


                }
                if (match.getMatchState() == ETAPE.INITIALISATION && match.charPicked == 1) {
                    // ***** PICK CHAR *****
                    // STATE : Un des joueurs a selectionné son perso et attend l'autre
                    // DO : Affiche la vue d'attente d'adversaire

                    val waitingPlayer = WaitingPlayer()
                    gameBoard.switchChildView(waitingPlayer)
                }

                ////////////////////////////////////////////////////////////////////////////////////////
                //////     ------------------------   TOUR IMPAIR   -----------------------       //////
                ////////////////////////////////////////////////////////////////////////////////////////
                if (match.getRound() % 2 != 0) {
                    if (match.getMatchState() == ETAPE.ATTENTE_QUESTION) {
                        // STATE : TOUR IMPAIR -> joueur 1 play, joueur 2 wait
                        // DO : Affiche la vue Guess et WaitingPlayer
                        if (match.getPlayerNo() == 1) {
                            val guess = Guess()
                            gameBoard.switchChildView(guess)
                        } else {
                            val waitingPlayer = WaitingPlayer()
                            gameBoard.switchChildView(waitingPlayer)
                        }
                    }
                    if (match.getMatchState() == ETAPE.ATTENTE_REPONSE) {
                        // STATE : TOUR IMPAIR -> joueur 2 play, joueur 1 wait
                        // DO : Affiche la vue Answer et WaitingPlayer
                        if (match.getPlayerNo() == 2) {
                            //val answer = Answer()
                            //gameBoard.switchChildView(answer)
                        } else {
                            val waitingPlayer = WaitingPlayer()
                            gameBoard.switchChildView(waitingPlayer)
                        }
                    }
                }
                ////////////////////////////////////////////////////////////////////////////////////////
                //////     ------------------------    TOUR PAIR    -----------------------       //////
                ////////////////////////////////////////////////////////////////////////////////////////
                if (match.getRound() % 2 == 0) {
                    if (match.getMatchState() == ETAPE.ATTENTE_QUESTION) {
                        // STATE : TOUR IMPAIR -> joueur 2 play, joueur 1 wait
                        // DO : Affiche la vue Guess et WaitingPlayer
                        if (match.getPlayerNo() == 2) {
                            val guess = Guess()
                            gameBoard.switchChildView(guess)
                        } else {
                            val waitingPlayer = WaitingPlayer()
                            gameBoard.switchChildView(waitingPlayer)
                        }
                    }
                    if (match.getMatchState() == ETAPE.ATTENTE_REPONSE) {
                        // STATE : TOUR IMPAIR -> joueur 1 play, joueur 2 wait
                        // DO : Affiche la vue Answer et WaitingPlayer
                        if (match.getPlayerNo() == 1) {
                            //val answer = Answer()
                            //gameBoard.switchChildView(answer)
                        } else {
                            val waitingPlayer = WaitingPlayer()
                            gameBoard.switchChildView(waitingPlayer)
                        }
                    }
                }

                ////////////////////////////////////////////////////////////////////////////////////////
                //////      ------------------------   other    -----------------------       //////
                ////////////////////////////////////////////////////////////////////////////////////////


            })
        )
        timeline.cycleCount = Animation.INDEFINITE
        timeline.play()
    }

    override fun handle(p0: ActionEvent?) {
        TODO("Not yet implemented")
    }


}
