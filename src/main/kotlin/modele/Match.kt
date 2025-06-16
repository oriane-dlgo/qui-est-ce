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
import vue.GameBoard
import java.net.URLEncoder
import java.nio.charset.StandardCharsets
import javax.swing.text.MutableAttributeSet

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
    private var keyPass : MutableList<Int>

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
        this.matchState = ETAPE.CREEE

        this.listSelChar = mutableListOf()
        this.listHideChar = mutableListOf()


        if (playerNo == 1) {
            this.opponentId = server.requeteEtatPartie(matchId).idJoueur2
        } else {
            this.opponentId = server.requeteEtatPartie(matchId).idJoueur1
        }

        if (this.opponentId != -1) {
            this.opponentGrid = server.requeteGrilleJoueur(this.matchId, this.opponentId)
        }



        this.roundCounter = 1
        this.characterPicked = Personnage("", "", "")


    }
    //
    //
    //
    //
    // Fonctions principales

    fun endOfRound(){
        server.requeteChercherEncore(this.matchId, this.playerIdKey.id, this.playerIdKey.cle)
    }
    fun nextRound(){
        roundCounter++
    }
    /*fun nextRound(iCloseIt :Boolean = false) {
        this.roundCounter += 1
        if (iCloseIt){
            server.requeteChercherEncore(this.matchId, this.playerIdKey.id, this.playerIdKey.cle)
        }
    }*/

    fun printState(): String {
        val state = server.requeteEtatPartie(this.matchId)
        val log = "     Etape match : ${this.matchState}                    Tour n° $roundCounter                    Joueur n° $playerNo"
        val match ="\n     Etape serveur : ${state.etape}     id joueur 1 :${state.idJoueur1}     id joueur 2 :${state.idJoueur2}     question : ${state.questionCourante}     reponse : ${state.reponseCourante}     id match : ${this.matchId}"

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

        gameBoard.gridCharacter.children.forEach { node ->
            if (node is StackPane) {
                node.style = "-fx-border-color: #78a9af; -fx-border-width: 5;"
            }
        }
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
        return imageView
    }

    fun updateGrid(gridCharacter: GridPane, opponent: Boolean, listHideChar: List<Int> = listOf()): GridPane {

        gridCharacter.children.clear()
        gridCharacter.isGridLinesVisible = true

        var index = 1  // Pour associer chaque case à un numéro (1 à 24)

        for (row in 0 until 4) {
            for (col in 0 until 6) {
                val stack = StackPane()

                val picture = getPictureOf(row, col, opponent)

                if (index in listHideChar) {
                    picture.opacity = 0.2
                }

                stack.children.add(picture)
                stack.style = "-fx-border-color: #78a9af; -fx-border-width: 5;"

                // OPTION CLICK / SELECTION CHARACTER
                stack.userData = index
                stack.setOnMouseClicked {
                    val id = stack.userData as Int

                    // SELECTION SECRET CHARACTER
                    if (this.matchState == ETAPE.INITIALISATION && this.charPickedNo == -1) {
                        // Un seul personnage sélectionnable
                        // Nettoie ancienne sélection
                        this.listSelChar.clear()

                        // Réinitialise styles des autres cases
                        gridCharacter.children.forEach { node ->
                            if (node is StackPane) {
                                node.style = "-fx-border-color: #78a9af; -fx-border-width: 5;"
                            }
                        }

                        // Ajoute la nouvelle sélection
                        this.listSelChar.add(id)
                        stack.style = "-fx-border-color: #4e6b6e; -fx-border-width: 5;"
                    }
                    if ((this.playerNo == 1 && this.roundCounter % 2 != 0) || (this.playerNo == 2 && this.roundCounter % 2 == 0)) {
                        // SELECTION HIDE CHARACTER
                        if (this.matchState == ETAPE.ATTENTE_REFLEXION) {
                            if (!this.listSelChar.contains(id)) {
                                this.listSelChar.add(id)
                                stack.style = "-fx-border-color: #4e6b6e; -fx-border-width: 5;"
                            } else {
                                this.listSelChar.remove(id)
                                stack.style = "-fx-border-color: #78a9af; -fx-border-width: 5;"
                            }
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

    fun updateKeyPass(number : Int, erase : Boolean = false) : MutableList<Int>{
        if (erase){
            this.keyPass.subList(1, keyPass.size).clear()
        }else{
            this.keyPass.add(number)
        }
        return keyPass
    }
    fun getKeyPass() : MutableList<Int>{
        return this.keyPass
    }


    /*
          fun makeGuess(player: Int , characterIndex: Int) : Personnage{
              var character = this.boardList[player][characterIndex]
              this.characterGuess = character
              this.question = "GUESS"
              return character
          }

          fun checkGuess(player: Int) : Boolean{
              return this.characterGuess == this.characterPicked[player]
          }



          fun endOfMatch(player : Int) {
              this.winner = this.playersList[player]
              this.question = ""
              this.answer = ""
              this.saved = true
          }
           */


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


    /*
    fun getBoardByName(player: Int): List<String> {
        return boardList[player].mapIndexed { index, perso -> "${index + 1}. ${perso.prenom}" }
    }




    fun getMatchInfo(): String {
        val info =
            "**Joueur 1**\n Nom : ${this.playersList[0]}\n Board : ${this.boardList[0]}\n Personnage choisis : ${this.characterPicked[0]}\n\n**Joueur 2**\n Nom : ${this.playersList[1]}\n Board : ${this.boardList[1]}\n Personnage choisis : ${this.characterPicked[1]}"
        return info
    }
     */
// ETC
// ETC
// ETC


}