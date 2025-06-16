package Controleurs

import info.but1.sae2025.data.ETAPE
import javafx.animation.Animation
import javafx.animation.KeyFrame
import javafx.animation.Timeline
import javafx.event.ActionEvent
import javafx.event.EventHandler
import javafx.scene.control.Label
import javafx.util.Duration
import modele.Match
import vue.Answer
import vue.GameBoard
import vue.Guess
import vue.HideCharacter
import vue.PickCharacter
import vue.WaitingPlayer

class GameClock(val match: Match, val gameBoard: GameBoard) : EventHandler<ActionEvent> {

    var keyPass : MutableList<Int>
    var roundCounter : Int
    var matchRound : Int

    init {
        val waitingPlayer = WaitingPlayer()

        roundCounter = 1
        matchRound = match.getRound()
        keyPass = mutableListOf<Int>()


        val timeline = Timeline(
            KeyFrame(Duration.seconds(4.0), EventHandler {

                if (matchRound +1 == roundCounter){
                    roundCounter = matchRound
                    keyPass.clear()
                }

                match.updateMatchState()
                gameBoard.bottom = Label(match.printState())

                if (match.getMatchState() == ETAPE.CREEE) {

                    // ***** INIT *****
                    // STATE : La partie vient de se lancer et le joueur 1 est seul
                    // DO : Affiche la vue d'attente d'adversaire
                    println("Attente du joueur adverse")

                }
                if (match.getMatchState() == ETAPE.INITIALISATION && match.charPickedNo == -1 && keyPass.find { it == 1 } == null) {
                    // ***** PICK CHAR *****
                    // STATE : Le deuxieme joueur vient de rejoindre
                    // DO : Affiche la vue pour la selection de perso
                    match.initOponentInfo()

                    keyPass.add(1)
                    val pickView = PickCharacter()
                    gameBoard.switchChildView(pickView)
                    pickView.btnValid.onAction = ControleurBoutonValiderPerso(match, gameBoard)


                }
                if (match.getMatchState() == ETAPE.INITIALISATION && match.charPickedNo == 1 && keyPass.find { it == 2 } == null) {
                    // ***** PICK CHAR *****
                    // STATE : Un des joueurs a selectionné son perso et attend l'autre
                    // DO : Affiche la vue d'attente d'adversaire
                    keyPass.add(2)
                    val waitingPlayer = WaitingPlayer()
                    gameBoard.switchChildView(waitingPlayer)
                }

                ////////////////////////////////////////////////////////////////////////////////////////
                //////     ------------------------   TOUR IMPAIR   -----------------------       //////
                ////////////////////////////////////////////////////////////////////////////////////////
                var matchPlayerNo : Int = match.getPlayerNo()
                var currentPlayerNo : Int

                if (match.getRound() % 2 == 0) {
                    if (matchPlayerNo == 1){
                        currentPlayerNo = 2
                    }else{
                        currentPlayerNo = 1
                    }
                }else{
                    if (matchPlayerNo == 1){
                        currentPlayerNo = 1
                    }else{
                        currentPlayerNo = 2
                    }

                }

                    // QUESTION
                    if (match.getMatchState() == ETAPE.ATTENTE_QUESTION && keyPass.find { it == 3 } == null) {
                        // STATE : TOUR IMPAIR -> joueur 1 questionne, joueur 2 wait
                        // DO : Affiche la vue Guess et WaitingPlayer

                        if (currentPlayerNo == 1) {
                            val guess = Guess()
                            gameBoard.switchChildView(guess)
                            guess.question.onAction = ControleurBoutonQuestion(match, gameBoard)
                        } else {
                            val waitingPlayer = WaitingPlayer()
                            gameBoard.switchChildView(waitingPlayer)
                        }
                        keyPass.add(3)
                    }
                    // REPONSE
                    if (match.getMatchState() == ETAPE.ATTENTE_REPONSE && keyPass.find { it == 4 } == null) {
                        // STATE : TOUR IMPAIR -> joueur 2 repond, joueur 1 wait
                        // DO : Affiche la vue Answer et WaitingPlayer

                        if (currentPlayerNo == 2) {
                            val answer = Answer(match.getQuestion())
                            gameBoard.switchChildView(answer)
                            answer.btnOui.onAction = ControleurBoutonReponse(match, gameBoard, answer, 1)
                            answer.btnNon.onAction = ControleurBoutonReponse(match, gameBoard, answer, 2)

                        } else {
                            val waitingPlayer = WaitingPlayer()
                            gameBoard.switchChildView(waitingPlayer)
                        }
                        keyPass.add(4)
                    }
                    // REFLEXION
                    if (match.getMatchState() == ETAPE.ATTENTE_REFLEXION && keyPass.find { it == 5 } == null) {
                        // STATE : TOUR IMPAIR -> joueur 1 elimine, joueur 2 wait
                        // DO : Affiche la vue Answer et WaitingPlayer

                        if (currentPlayerNo == 1) {
                            val hideChar = HideCharacter(match.getAnswer())
                            gameBoard.switchChildView(hideChar)
                            hideChar.btnHide.onAction = ControleurBoutonHide(match, gameBoard)
                            hideChar.btnOk.onAction = ControleurBoutonHideFinish(match, gameBoard)


                        } else {
                            val waitingPlayer = WaitingPlayer()
                            gameBoard.switchChildView(waitingPlayer)
                        }
                        keyPass.add(8)
                    }


                /*
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
                */
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
