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
import vue.Question
import vue.HideCharacter
import vue.Loose
import vue.PickCharacter
import vue.WaitingPlayer
import vue.Win

class GameClock(val match: Match, val gameBoard: GameBoard) : EventHandler<ActionEvent> {

    var keyPass: MutableList<Int>

    //var roundCounter: Int
    //var matchRound: Int
    var charPickBool: Boolean
    var matchState : ETAPE

    init {
        val waitingPlayer = WaitingPlayer()

        //roundCounter = 1
        //matchRound = match.getRound()
        keyPass = match.getKeyPass()
        charPickBool = false
        matchState = ETAPE.CREEE


        val timeline = Timeline(
            KeyFrame(Duration.seconds(0.5), EventHandler {

                match.updateMatchState()
                gameBoard.bottom = Label(match.printState(matchState))

                if (matchState == ETAPE.CREEE) {

                    // ***** INIT *****
                    // STATE : La partie vient de se lancer et le joueur 1 est seul
                    // DO : Affiche la vue d'attente d'adversaire
                    println("Attente du joueur adverse")
                    matchState = match.getMatchState()

                }
                if (matchState == ETAPE.INITIALISATION && match.charPickedNo == -1 && keyPass.find { it == 1 } == null) {
                    // ***** PICK CHAR *****
                    // STATE : Le deuxieme joueur vient de rejoindre
                    // DO : Affiche la vue pour la selection de perso
                    match.initOponentInfo()
                    match.updateKeyPass(1)
                    //keyPass.add(1)
                    val pickView = PickCharacter()
                    gameBoard.switchChildView(pickView)
                    pickView.btnValid.onAction = ControleurBoutonValiderPerso(match, gameBoard)
                }
                if (matchState == ETAPE.INITIALISATION && match.charPickedNo != -1 && keyPass.find { it == 2 } == null && keyPass.any { it == 1 }) {
                    // ***** PICK CHAR *****
                    // STATE : Un des joueurs a selectionné son perso et attend l'autre
                    // DO : Affiche la vue d'attente d'adversaire
                    //keyPass.add(2)
                    val waitingPlayer = WaitingPlayer()
                    gameBoard.switchChildView(waitingPlayer)

                    match.updateKeyPass(2)
                }

                if (matchState == ETAPE.INITIALISATION && matchState != match.getMatchState() && keyPass.any { it == 2}){
                        matchState = ETAPE.ATTENTE_QUESTION
                }

                var currentPlayerNo: Int = match.getPlayerNo()


                ////////////////////////////////////////////////////////////////////////////////////////
                //////     ------------------------   TOUR IMPAIR   -----------------------       //////
                ////////////////////////////////////////////////////////////////////////////////////////



                if (match.getRound() % 2 != 0) {

                    // QUESTION
                    if (matchState == ETAPE.ATTENTE_QUESTION && keyPass.find { it == 3 } == null) {
                        // STATE : TOUR IMPAIR -> joueur 1 questionne, joueur 2 wait
                        // DO : Affiche la vue Guess et WaitingPlayer

                        if (currentPlayerNo == 1) {
                            val question = Question()
                            gameBoard.switchChildView(question)
                            question.question.onAction = ControleurBoutonQuestion(match, gameBoard)
                        } else {
                            val waitingPlayer = WaitingPlayer()
                            gameBoard.switchChildView(waitingPlayer)
                        }
                        match.updateKeyPass(3)
                        //keyPass.add(2)
                    }
                    if (matchState == ETAPE.ATTENTE_QUESTION && matchState != match.getMatchState() && keyPass.any { it == 3}){
                        matchState = ETAPE.ATTENTE_REPONSE
                    }

                    // REPONSE
                    if (matchState == ETAPE.ATTENTE_REPONSE && keyPass.find { it == 4 } == null) {
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
                        match.updateKeyPass(4)
                        //keyPass.add(3)
                    }
                    if (matchState == ETAPE.ATTENTE_REPONSE && matchState != match.getMatchState() && keyPass.any { it == 4}){
                        matchState = ETAPE.ATTENTE_REFLEXION
                    }


                    // REFLEXION
                    if (matchState == ETAPE.ATTENTE_REFLEXION && keyPass.find { it == 5 } == null) {
                        // STATE : TOUR IMPAIR -> joueur 1 elimine, joueur 2 wait
                        // DO : Affiche la vue Answer et WaitingPlayer

                        if (currentPlayerNo == 1) {
                            val hideChar = HideCharacter(match.getAnswer())
                            gameBoard.switchChildView(hideChar)
                            hideChar.btnHide.onAction = ControleurBoutonHide(match, gameBoard)
                            hideChar.btnOk.onAction = ControleurBoutonEndOfRound(match, gameBoard)
                            hideChar.proposition.onAction = ControleurBoutonGuess(match, gameBoard)

                        } else {
                            val waitingPlayer = WaitingPlayer()
                            gameBoard.switchChildView(waitingPlayer)
                        }
                        match.updateKeyPass(5)
                        //keyPass.add(4)
                    }
                    if (matchState == ETAPE.ATTENTE_REFLEXION && matchState != match.getMatchState() && keyPass.any { it == 5}){

                        if (match.getMatchState() == ETAPE.TERMINEE){
                            matchState = ETAPE.TERMINEE
                        }else{
                            matchState = ETAPE.ATTENTE_QUESTION
                            match.nextRound()
                            keyPass = match.updateKeyPass(0, true)
                        }

                    }
                    // END OF MATCH
                    if (matchState == ETAPE.TERMINEE && keyPass.find { it == 6 } == null){
                        // STATE : TOUR PAIR -> joueur 2 gagne, joueur 1 loose
                        // DO : Affiche la vue WIN et LOOSE
                        if (currentPlayerNo == 2) {
                            val win = Win(match.getRound())
                            gameBoard.switchChildView(win)
                            //win.btnAgain.onAction = ControleurBoutonAgain(match, gameBoard)
                        } else {
                            val loose = Loose(match.getRound())
                            gameBoard.switchChildView(loose)
                            //loose.btnAgain.onAction = ControleurBoutonAgain(match, gameBoard)
                        }
                        match.updateKeyPass(6)
                    }
                } else {
                    ////////////////////////////////////////////////////////////////////////////////////////
                    //////     ------------------------    TOUR PAIR    -----------------------       //////
                    ////////////////////////////////////////////////////////////////////////////////////////
                    // QUESTION

                    if (matchState == ETAPE.ATTENTE_QUESTION && keyPass.find { it == 3 } == null) {
                        // STATE : TOUR PAIR -> joueur 2 questionne, joueur 1 wait
                        // DO : Affiche la vue Guess et WaitingPlayer



                        if (currentPlayerNo == 2) {
                            val question = Question()
                            gameBoard.switchChildView(question)
                            question.question.onAction = ControleurBoutonQuestion(match, gameBoard)
                        } else {
                            val waitingPlayer = WaitingPlayer()
                            gameBoard.switchChildView(waitingPlayer)
                        }
                        match.updateKeyPass(3)
                        //keyPass.add(2)
                    }
                    if (matchState == ETAPE.ATTENTE_QUESTION && matchState != match.getMatchState() && keyPass.any { it == 3}){
                        matchState = ETAPE.ATTENTE_REPONSE
                    }


                    // REPONSE
                    if (matchState == ETAPE.ATTENTE_REPONSE && keyPass.find { it == 4 } == null) {
                        // STATE : TOUR PAIR -> joueur 1 repond, joueur 2 wait
                        // DO : Affiche la vue Answer et WaitingPlayer

                        if (currentPlayerNo == 1) {
                            val answer = Answer(match.getQuestion())
                            gameBoard.switchChildView(answer)
                            answer.btnOui.onAction = ControleurBoutonReponse(match, gameBoard, answer, 1)
                            answer.btnNon.onAction = ControleurBoutonReponse(match, gameBoard, answer, 2)

                        } else {
                            val waitingPlayer = WaitingPlayer()
                            gameBoard.switchChildView(waitingPlayer)
                        }
                        match.updateKeyPass(4)
                        //keyPass.add(3)
                    }
                    if (matchState == ETAPE.ATTENTE_REPONSE && matchState != match.getMatchState() && keyPass.any { it == 4}){
                        matchState = ETAPE.ATTENTE_REFLEXION
                    }


                    // REFLEXION
                    if (matchState == ETAPE.ATTENTE_REFLEXION && keyPass.find { it == 5 } == null) {
                        // STATE : TOUR PAIR -> joueur 2 elimine, joueur 1 wait
                        // DO : Affiche la vue Answer et WaitingPlayer

                        if (currentPlayerNo == 2) {
                            val hideChar = HideCharacter(match.getAnswer())
                            gameBoard.switchChildView(hideChar)
                            hideChar.btnHide.onAction = ControleurBoutonHide(match, gameBoard)
                            hideChar.btnOk.onAction = ControleurBoutonEndOfRound(match, gameBoard)
                            hideChar.proposition.onAction = ControleurBoutonGuess(match, gameBoard)


                        } else {
                            val waitingPlayer = WaitingPlayer()
                            gameBoard.switchChildView(waitingPlayer)
                        }
                        match.updateKeyPass(5)
                        //keyPass.add(4)
                    }
                    if (matchState == ETAPE.ATTENTE_REFLEXION && matchState != match.getMatchState() && keyPass.any { it == 5}){

                        if (match.getMatchState() == ETAPE.TERMINEE){
                            matchState = ETAPE.TERMINEE
                                                    }else{
                            matchState = ETAPE.ATTENTE_QUESTION
                            match.nextRound()
                            keyPass = match.updateKeyPass(0, true)
                        }

                    }
                    // END OF MATCH
                    if (matchState == ETAPE.TERMINEE && keyPass.find { it == 6 } == null){
                        // STATE : TOUR PAIR -> joueur 2 gagne, joueur 1 loose
                        // DO : Affiche la vue WIN et LOOSE
                        if (currentPlayerNo == 2) {
                            val win = Win(match.getRound())
                            gameBoard.switchChildView(win)
                            //win.btnAgain.onAction = ControleurBoutonAgain(match, gameBoard)
                        } else {
                            val loose = Loose(match.getRound())
                            gameBoard.switchChildView(loose)
                            //loose.btnAgain.onAction = ControleurBoutonAgain(match, gameBoard)
                        }
                        match.updateKeyPass(6)
                    }


                }
            })
        )
        timeline.cycleCount = Animation.INDEFINITE
        timeline.play()
    }

    override fun handle(p0: ActionEvent?) {
        TODO("Not yet implemented")
    }


}
