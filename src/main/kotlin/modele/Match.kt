package modele

import info.but1.sae2025.QuiEstCeClient
import info.but1.sae2025.data.ETAPE
import info.but1.sae2025.data.EtatPartie
import info.but1.sae2025.data.IdentificationJoueur
import info.but1.sae2025.data.Personnage
import javafx.scene.Node
import javafx.scene.image.Image
import javafx.scene.image.ImageView
import javafx.scene.layout.GridPane
import javafx.scene.layout.StackPane
import javafx.scene.paint.Color
import javafx.scene.shape.Rectangle
import vue.GameBoard
import java.net.URLEncoder
import java.nio.charset.StandardCharsets
import javax.swing.text.MutableAttributeSet
import kotlin.rem

class Match(server: QuiEstCeClient, matchId: Int, playerIdKey: IdentificationJoueur, playerNo: Int) {

    private val server: QuiEstCeClient
    private val matchId: Int
    private var playerIdKey: IdentificationJoueur
    private var playerNo: Int
    private var playerGrid: List<List<Personnage>>
    private var opponentId: Int
    private lateinit var opponentGrid: List<List<Personnage>>
    private var listSelChar: MutableList<Int>
    private var listHideChar: MutableList<Int>
    private var keyPass: MutableList<Int>
    private var keySel: Int
    private var keyHide: Int
    private var roundByPlayer: Boolean

    private var matchState: ETAPE

    private var characterPicked: Personnage
    var charPickedNo: Int = -1

    // A CHECK
    private lateinit var characterGuess: Personnage

    //private var boardList: List<MutableList<Personnage>>
    private var roundCounter: Int

    private lateinit var winner: IdentificationJoueur
    //private var haveWinner: Boolean
    //private var saved: Boolean


    init {

        this.server = server
        this.matchId = matchId
        this.playerIdKey = playerIdKey
        this.playerNo = playerNo
        this.playerGrid = server.requeteGrilleJoueur(this.matchId, this.playerIdKey.id)

        this.keyPass = mutableListOf()
        this.keySel = -1
        this.keyHide = 0
        this.matchState = ETAPE.CREEE

        this.listSelChar = mutableListOf()
        this.listHideChar = mutableListOf()

        this.roundCounter = 1
        this.characterPicked = Personnage("", "", "")

        roundByPlayer =
            (this.playerNo == 1 && this.roundCounter % 2 != 0) || (this.playerNo == 2 && this.roundCounter % 2 == 0)

        if (playerNo == 1) {
            this.opponentId = server.requeteEtatPartie(matchId).idJoueur2
        } else {
            this.opponentId = server.requeteEtatPartie(matchId).idJoueur1
        }

        if (this.opponentId != -1) {
            this.opponentGrid = server.requeteGrilleJoueur(this.matchId, this.opponentId)
        }


    }
    //
    //
    //
    //
    // Fonctions principales

    fun endOfRound() {

        server.requeteChercherEncore(this.matchId, this.playerIdKey.id, this.playerIdKey.cle)
    }

    fun nextRound() : Int {
        roundCounter++
        roundByPlayer =
            (this.playerNo == 1 && this.roundCounter % 2 != 0) || (this.playerNo == 2 && this.roundCounter % 2 == 0)
        return this.roundCounter
    }
    /*fun nextRound(iCloseIt :Boolean = false) {
        this.roundCounter += 1
        if (iCloseIt){
            server.requeteChercherEncore(this.matchId, this.playerIdKey.id, this.playerIdKey.cle)
        }
    }*/

    fun printState(matchState: ETAPE): String {
        val state = server.requeteEtatPartie(this.matchId)
        val log =
            "     Etape match : ${matchState}                    Tour n° $roundCounter                    Joueur n° $playerNo"
        val match =
            "\n     Etape serveur : ${state.etape}     id joueur 1 :${state.idJoueur1}     id joueur 2 :${state.idJoueur2}     question : ${state.questionCourante}     reponse : ${state.reponseCourante}     id match : ${this.matchId}"

        return log + match
    }

    fun getMatchState(): ETAPE {
        return this.matchState
    }

    fun initOponentInfo() {

        if (playerNo == 1) {
            this.opponentId = server.requeteEtatPartie(matchId).idJoueur2
        } else {
            this.opponentId = server.requeteEtatPartie(matchId).idJoueur1
        }
        this.opponentGrid = server.requeteGrilleJoueur(this.matchId, this.opponentId)
    }

    fun updateMatchState(): EtatPartie {
        this.matchState = server.requeteEtatPartie(this.matchId).etape
        return server.requeteEtatPartie(this.matchId)
    }

    fun resetListSelChar(gameBoard: GameBoard) {
        this.listSelChar.clear()

        /*
        gameBoard.gridCharacter.children.forEach { node ->
            if (node is StackPane) {
                node.style = "-fx-border-color: #78a9af; -fx-border-width: 4;"
            }
        }

         */
    }

    fun pickCharacter(row: Int, col: Int) {
        server.requeteChoixPersonnage(this.matchId, this.playerIdKey.id, this.playerIdKey.cle, row, col)
        this.characterPicked = this.playerGrid[row][col]

    }

    fun getPictureOf(row: Int, col: Int, opponent: Boolean = true): Node {
        val baseUrl = "http://localhost:8080/resources/but1/"

        val filename = if (opponent) {
            this.getOpponentGrid()[row][col].url
        } else {
            this.getGrid()[row][col].url
        }

        val fileNameEncoded = URLEncoder.encode(filename, StandardCharsets.UTF_8.toString())

        val fullUrl = "$baseUrl$fileNameEncoded"
        val image = Image(fullUrl)

        val imageView = ImageView(image).apply {
            fitWidth = 100.0
            fitHeight = 100.0
            isPreserveRatio = true
        }
        val border = Rectangle(102.0, 102.0).apply {
            fill = Color.WHITE
        }
        border.style = "-fx-border-color: #78a9af;"

        return StackPane(border, imageView)

    }


    fun updateGrid(gridCharacter: GridPane, opponent: Boolean): GridPane { // , listHideChar: List<Int> = listOf()


        gridCharacter.children.clear()
        gridCharacter.isGridLinesVisible = false

        var index = 1  // Pour associer chaque case à un numéro (1 à 24)

        for (row in 0 until 4) {
            for (col in 0 until 6) {
                val stack = StackPane()

                val picture = getPictureOf(row, col, opponent)

                if (index in this.listHideChar) {
                    picture.opacity = 0.2
                }

                stack.children.add(picture)
                stack.style = "-fx-border-color: rgba(255, 255, 255, 0.9) ; -fx-border-width: 4;"
                stack.prefHeight = 108.0
                stack.minHeight = 108.0
                stack.prefWidth = 108.0
                stack.minWidth = 108.0

                // OPTION CLICK / SELECTION CHARACTER
                stack.userData = index
                stack.setOnMouseClicked {
                    val id = stack.userData as Int

                    // SELECTION 1 BY 1
                    if (this.keySel == 1) {
                        // Un seul personnage sélectionnable
                        // Nettoie ancienne sélection
                        this.listSelChar.clear()

                        // Réinitialise styles des autres cases
                        gridCharacter.children.forEach { node ->
                            if (node is StackPane) {
                                node.style = "-fx-border-color: rgba(255, 255, 255, 0.9); -fx-border-width: 4;"
                            }
                        }

                        // Ajoute la nouvelle sélection
                        this.listSelChar.add(id)

                        stack.style = "-fx-border-color: #78a9af; -fx-border-width: 4;"

                    }
                    if (this.keySel == 2) {
                        // SELECTION MULTIPLE
                        // QUAND ON CLIQUE SUR UNE CELULE EN KEYSEL2, AJOUTE AU LIST SEL ET MET CONTOUR BLEU
                        if (!this.listSelChar.contains(id)) {
                            this.listSelChar.add(id)
                            stack.style = "-fx-border-color: #78a9af; -fx-border-width: 4;"
                        // SI ON RECLIQUE DESSUS ELLE SE REMET SANS BLEU
                        } else {
                            this.listSelChar.remove(id)
                            stack.style = "-fx-border-color: rgba(255, 255, 255, 0.9); -fx-border-width: 4;"

                        }
                    }
                }
                gridCharacter.add(stack, col, row)
                index++
            }
        }
        return gridCharacter
    }


    fun putQuestion(question: String) {
        server.requetePoserQuestion(this.matchId, this.playerIdKey.id, this.playerIdKey.cle, question)
    }

    fun putAnswer(answer: String) {
        server.requeteDonnerReponse(this.matchId, this.playerIdKey.id, this.playerIdKey.cle, answer)
    }

    fun updateKeyHide() {


        this.keyHide++
    }

    fun updateKeyPass(number: Int, erase: Boolean = false): MutableList<Int> {
        if (erase) {
            this.keyPass.subList(1, keyPass.size).clear()
        } else {
            this.keyPass.add(number)
        }
        return keyPass
    }

    fun getKeyPass(): MutableList<Int> {
        return this.keyPass
    }

    fun makeGuess(caseId: Int) {

        this.listHideChar.add(caseId)

        val position = getRowCol(caseId)
        if (position != null) {
            val (row, col) = position
            server.requeteTrouve(this.matchId, this.playerIdKey.id, this.playerIdKey.cle, row, col)
        }


    }

    fun getRowCol(caseId: Int): Pair<Int, Int>? {
        var i: Int = 0
        for (row in 0 until 4) {
            for (col in 0 until 6) {
                i++
                if (i == caseId) {
                    return Pair(row, col)
                }
            }
        }
        return null
    }
    fun updateKeySel(key: Int) {
        this.keySel = key
    }

    fun resetKeyHide(){
        this.keyHide = 0
    }

    fun addToListHideChar(list : List<Int>){
        this.listHideChar += list
    }
    //
//
//
//
// Fonctions de recuperations de variables
    fun getId() = this.matchId

    //fun getPlayerList() = this.playerList
    fun getCharacterPicked() = this.characterPicked

    //fun getBoardList() = this.boardList
    fun getQuestion() = server.requeteEtatPartie(this.matchId).questionCourante
    fun getAnswer() = server.requeteEtatPartie(this.matchId).reponseCourante

    //fun getState() = this.saved
//fun getWinner() = this.haveWinner
    fun getRound() = this.roundCounter
    fun getGuess() = this.characterGuess
    fun getGrid() = this.playerGrid
    fun getOpponentGrid() = this.opponentGrid
    fun getCurrentPlayer() = this.playerNo
    fun getPlayerNo() = this.playerNo
    fun getListSelChar() = this.listSelChar
    fun getRoundByPlayer() = this.roundByPlayer
    fun getKeyHide() = this.keyHide
    fun getKeySel() = this.keySel
    fun getListHide() = this.listHideChar
    fun isPlayerSelected() = (this.listSelChar.size != 0)
    fun getMatchId() = this.matchId


}