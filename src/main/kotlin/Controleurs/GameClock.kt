package Controleurs

import info.but1.sae2025.data.ETAPE
import javafx.animation.Animation
import javafx.animation.KeyFrame
import javafx.animation.Timeline
import javafx.event.ActionEvent
import javafx.event.EventHandler
import javafx.util.Duration
import modele.Client
import modele.Match
import vue.Answer
import vue.GameBoard
import vue.Question
import vue.HideCharacter
import vue.Login
import vue.Loose
import vue.MainView
import vue.MatchList
import vue.MatchMaking
import vue.NextRound
import vue.PickCharacter
import vue.TestPopUp
import vue.WaitingPlayer
import vue.Win

class GameClock(val client: Client, val match: Match, val gameBoard: GameBoard, val mainView: MainView) :
    EventHandler<ActionEvent> {

    var waitingPlayer: WaitingPlayer = WaitingPlayer()
    var keyPass: MutableList<Int>
    val answer = Answer(match.getQuestion())
    val pickView = PickCharacter()
    val question = Question()
    val hideChar = HideCharacter(match.getAnswer())

    //var charPickBool: Boolean
    var matchState: ETAPE

    init {

        keyPass = match.getKeyPass()
        var (lastName, name) = client.getCurrentPlayer().first
        //charPickBool = false
        matchState = ETAPE.CREEE

        val timeline = Timeline(
            KeyFrame(Duration.seconds(0.5), EventHandler {

                match.updateMatchState()

                // ***** CREE *****
                if (matchState == ETAPE.CREEE) {
                    // STATE : La partie vient de se lancer et le joueur 1 est seul

                    // Change d'etat dès que le serveur change
                    matchState = match.getMatchState()

                    // Switch view - Waiting player
                    waitingPlayer.setMessage("En attente d'un adversaire...")
                    gameBoard.switchChildView(waitingPlayer)
                }
                // ***** INITIALISATION ***** // ***** PICK CHAR *****
                if (matchState == ETAPE.INITIALISATION && match.charPickedNo == -1 && keyPass.find { it == 1 } == null) {
                    // STATE : Le deuxieme joueur vient de rejoindre

                    // Ajout de la KeyPass & MaJ de la KeySel
                    match.updateKeyPass(1)
                    match.updateKeySel(1)

                    // Initialisation des données adversaire
                    match.initOponentInfo()

                    // Switch View - Pick char
                    //val pickView = PickCharacter(match)
                    gameBoard.switchChildView(pickView)
                    pickView.btnOk.onAction = ControleurBoutonValiderPerso(match, gameBoard)

                }
                if (matchState == ETAPE.INITIALISATION && match.charPickedNo == -1) {
                    pickView.updateBtn(match)
                }
                // ***** INITIALISATION ***** // ***** WAITING *****
                if (matchState == ETAPE.INITIALISATION && match.charPickedNo != -1 && keyPass.find { it == 2 } == null && keyPass.any { it == 1 }) {
                    // STATE : Un des joueurs a selectionné son perso et attend l'autre

                    // Ajout de la KeyPass & MaJ de la KeySel
                    match.updateKeyPass(2)
                    match.updateKeySel(0)

                    // Switch View - Waiting Player
                    val waitingPlayer = WaitingPlayer()
                    waitingPlayer.setMessage("Ton adversaire choisi son personnage...")
                    gameBoard.switchChildView(waitingPlayer)
                }
                // ANTI JUMP_OVER_STATE //  Passage a ATTENTE_QUESTION si serveur & client OK
                if (matchState == ETAPE.INITIALISATION && matchState != match.getMatchState() && keyPass.any { it == 2 }) {
                    matchState = ETAPE.ATTENTE_QUESTION
                }


                // Si c'est le round de joueur actuel de questionner
                if (match.getRoundByPlayer()) {


                    // ***** QUESTION *****
                    if (matchState == ETAPE.ATTENTE_QUESTION && keyPass.find { it == 3 } == null) {
                        // STATE : TOUR IMPAIR -> joueur 1 questionne, joueur 2 wait

                        // Ajout de la KeyPass
                        match.updateKeyPass(3)
                        match.resetKeyHide()

                        // Switch View - Question
                        //val question = Question()
                        gameBoard.switchChildView(question)
                        question.btnOk.onAction = ControleurBoutonQuestion(match, gameBoard, question)
                    }
                    // ANTI JUMP_OVER_STATE // Passage a ATTENTE_REPONSE si serveur & client OK
                    if (matchState == ETAPE.ATTENTE_QUESTION && matchState != match.getMatchState() && keyPass.any { it == 3 }) {
                        matchState = ETAPE.ATTENTE_REPONSE
                    }
                    if (matchState == ETAPE.ATTENTE_QUESTION) {
                        question.updateBtn()
                    }


                    // ***** ANSWER *****
                    if (matchState == ETAPE.ATTENTE_REPONSE && keyPass.find { it == 4 } == null) {
                        // STATE : TOUR IMPAIR -> joueur 2 repond, joueur 1 wait

                        // Ajout de la KeyPass
                        match.updateKeyPass(4)

                        // Switch View - Answer
                        val waitingPlayer = WaitingPlayer()
                        waitingPlayer.setMessage("Ton adversaire répond à ta question...")
                        gameBoard.switchChildView(waitingPlayer)

                    }
                    // ANTI JUMP_OVER_STATE // Passage a ATTENTE_REFLEXION si serveur & client OK
                    if (matchState == ETAPE.ATTENTE_REPONSE && matchState != match.getMatchState() && keyPass.any { it == 4 }) {
                        matchState = ETAPE.ATTENTE_REFLEXION
                    }


                    // ***** REFLEXION *****
                    if (matchState == ETAPE.ATTENTE_REFLEXION && keyPass.find { it == 5 } == null) {
                        // STATE : TOUR IMPAIR -> joueur 1 elimine, joueur 2 wait

                        // Ajout de la KeyPass
                        match.updateKeyPass(5)

                        // Switch View - Reflexion
                        //val hideChar = HideCharacter(match.getAnswer())
                        gameBoard.switchChildView(hideChar)

                        when (match.getKeyHide()) {
                            0 -> {
                                hideChar.switchHideView(0)
                                hideChar.btnYes.onAction = ControleurBoutonHideYes(match, true)
                                hideChar.btnNo.onAction = ControleurBoutonHideNo(match, gameBoard, false)
                            }

                            1 -> {
                                hideChar.switchHideView(1)
                                hideChar.btnOk.onAction = ControleurBoutonHideOk(match, gameBoard, true)
                            }

                            2 -> {
                                hideChar.switchHideView(2)
                                hideChar.btnYes.onAction = ControleurBoutonHideYes(match, false)
                                hideChar.btnNo.onAction = ControleurBoutonHideNo(match, gameBoard, true)
                            }

                            3 -> {
                                hideChar.switchHideView(3)
                                hideChar.btnOk.onAction = ControleurBoutonHideOk(match, gameBoard, false)
                            }
                        }
                    }
                    // ANTI JUMP_OVER_STATE // Passage a ATTENTE_QUESTION ou TERMINE si serveur & client OK et win ou non
                    if (matchState == ETAPE.ATTENTE_REFLEXION && matchState != match.getMatchState() && keyPass.any { it == 5 }) {

                        if (match.getMatchState() == ETAPE.TERMINEE) {
                            matchState = ETAPE.TERMINEE
                        } else {
                            var popUp = NextRound(match.nextRound())
                            client.nextRoundPopUp(match, gameBoard, popUp)
                            matchState = ETAPE.ATTENTE_QUESTION
                            keyPass = match.updateKeyPass(0, true)
                        }
                    }
                    if (matchState == ETAPE.ATTENTE_REFLEXION && match.getKeyHide() == 3) {
                        hideChar.updateBtn(match)
                    }


                    // ***** END OF MATCH *****
                    if (matchState == ETAPE.TERMINEE && keyPass.find { it == 6 } == null) {
                        // STATE : TOUR PAIR -> joueur 2 gagne, joueur 1 loose

                        // Ajout de la KeyPass
                        match.updateKeyPass(6)

                        // Switch View - Win
                        val win = Win(match.getRound())
                        gameBoard.switchEndView(win)

                        var login = Login()
                        login.textFieldLastName.text = lastName
                        login.textFieldName.text = name
                        var matchMaking = MatchMaking()
                        matchMaking.btnNew.onAction =
                            ControleurBoutonStartMatch(client, mainView, MatchList(client.getMatchList()), true)
                        matchMaking.btnList.onAction =
                            ControleurBoutonRejoindrePartie(client, mainView, MatchList(client.getMatchList()))
                        matchMaking.btnReturn.onAction = ControleurBoutonBack(mainView, login)
                        gameBoard.initBtnAgain()
                        gameBoard.btnAgain.onAction = ControleurBoutonLogin(client, mainView, login, matchMaking)
                    }
                }


                // Si c'est le round du joueur actuel de repondre
                else {
                    // ***** QUESTION *****
                    if (matchState == ETAPE.ATTENTE_QUESTION && keyPass.find { it == 3 } == null && keyPass.find { it == 7 } == null) {
                        // STATE : TOUR IMPAIR -> joueur 1 questionne, joueur 2 wait

                        // Ajout de la KeyPass
                        match.updateKeyPass(3)
                        match.resetKeyHide()

                        // Switch View - Question
                        waitingPlayer.setMessage("Ton adversaire pose sa question...")
                        gameBoard.switchChildView(waitingPlayer)
                    }
                    // ANTI JUMP_OVER_STATE // Passage a ATTENTE_REPONSE si serveur & client OK
                    if (matchState == ETAPE.ATTENTE_QUESTION && matchState != match.getMatchState() && keyPass.any { it == 3 }) {
                        matchState = ETAPE.ATTENTE_REPONSE
                    }


                    // ***** ANSWER *****
                    if (matchState == ETAPE.ATTENTE_REPONSE && keyPass.find { it == 4 } == null) {
                        // STATE : TOUR IMPAIR -> joueur 2 repond, joueur 1 wait

                        // Ajout de la KeyPass
                        match.updateKeyPass(4)

                        // Switch View - Answer

                        gameBoard.switchChildView(answer)
                        answer.btnOui.onAction = ControleurBoutonReponse(match, gameBoard, answer, 1)
                        answer.btnNon.onAction = ControleurBoutonReponse(match, gameBoard, answer, 2)
                    }
                    // ANTI JUMP_OVER_STATE // Passage a ATTENTE_REFLEXION si serveur & client OK
                    if (matchState == ETAPE.ATTENTE_REPONSE && matchState != match.getMatchState() && keyPass.any { it == 4 }) {
                        matchState = ETAPE.ATTENTE_REFLEXION
                    }
                    if (matchState == ETAPE.ATTENTE_REPONSE) {
                        answer.updateQuestion(match.getQuestion())
                    }


                    // ***** REFLEXION *****
                    if (matchState == ETAPE.ATTENTE_REFLEXION && keyPass.find { it == 5 } == null) {
                        // STATE : TOUR IMPAIR -> joueur 1 elimine, joueur 2 wait

                        // Ajout de la KeyPass
                        match.updateKeyPass(5)

                        // Switch View - Reflexion
                        val waitingPlayer = WaitingPlayer()
                        waitingPlayer.setMessage("Ton adversaire rélféchit...")
                        gameBoard.switchChildView(waitingPlayer)
                    }
                    // ANTI JUMP_OVER_STATE // Passage a ATTENTE_QUESTION ou TERMINE si serveur & client OK et win ou non
                    if (matchState == ETAPE.ATTENTE_REFLEXION && matchState != match.getMatchState() && keyPass.any { it == 5 }) {

                        if (match.getMatchState() == ETAPE.TERMINEE) {
                            matchState = ETAPE.TERMINEE
                        } else {
                            var popUp = NextRound(match.nextRound())
                            client.nextRoundPopUp(match, gameBoard, popUp)
                            keyPass = match.updateKeyPass(0, true)
                            matchState = ETAPE.ATTENTE_QUESTION


                        }
                    }


                    // ***** END OF MATCH *****
                    if (matchState == ETAPE.TERMINEE && keyPass.find { it == 6 } == null) {
                        // STATE : TOUR PAIR -> joueur 2 gagne, joueur 1 loose

                        // Ajout de la KeyPass
                        match.updateKeyPass(6)

                        // Switch View - Loose
                        val loose = Loose(match.getRound())
                        gameBoard.switchEndView(loose)
                        var login = Login()
                        login.textFieldLastName.text = lastName
                        login.textFieldName.text = name
                        var matchMaking = MatchMaking()
                        matchMaking.btnNew.onAction =
                            ControleurBoutonStartMatch(client, mainView, MatchList(client.getMatchList()), true)
                        matchMaking.btnList.onAction =
                            ControleurBoutonRejoindrePartie(client, mainView, MatchList(client.getMatchList()))
                        matchMaking.btnReturn.onAction = ControleurBoutonBack(mainView, login)
                        gameBoard.initBtnAgain()
                        gameBoard.btnAgain.onAction = ControleurBoutonLogin(client, mainView, login, matchMaking)
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