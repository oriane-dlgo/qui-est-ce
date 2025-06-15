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

class Match(server: QuiEstCeClient, matchId: Int, playerIdKey: IdentificationJoueur, playerNo: Int) {

    private val server: QuiEstCeClient
    private val matchId: Int
    private var playerIdKey: IdentificationJoueur
    private var playerNo: Int
    private var playerGrid: List<List<Personnage>>
    private var opponentId: Int
    private lateinit var opponentGrid: List<List<Personnage>>

    private var matchState : ETAPE

    private var characterPicked: Personnage
    private var question: String
    private var answer: String
    var charPicked : Int = -1

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

        this.matchState = ETAPE.CREEE


        if (playerNo == 0) {
            this.opponentId = server.requeteEtatPartie(matchId).idJoueur2
        } else {
            this.opponentId = server.requeteEtatPartie(matchId).idJoueur1
        }

        if (this.opponentId != -1) {
            this.opponentGrid = server.requeteGrilleJoueur(this.matchId, this.opponentId)
        }



        this.roundCounter = 0
        this.characterPicked = Personnage("", "", "")
        this.question = ""
        this.answer = ""


        // A CHECK
        //this.boardList = listOf(characterList.shuffled().toMutableList(), characterList.shuffled().toMutableList())
        //this.haveWinner = false
        //this.saved = false
    }
    //
    //
    //
    //
    // Fonctions principales

    fun printState(){
        println("Match : ${this.matchState}")
        println("serveur : ${server.requeteEtatPartie(this.matchId)}")
    }
    fun getMatchState() : ETAPE{
        return this.matchState
    }
    fun updateMatchState() : EtatPartie{

        val state = server.requeteEtatPartie(this.matchId)

        if(state.etape == ETAPE.INITIALISATION && matchState == ETAPE.CREEE){
            if (playerNo == 0) {
                this.opponentId = server.requeteEtatPartie(matchId).idJoueur2
            } else {
                this.opponentId = server.requeteEtatPartie(matchId).idJoueur1
            }
            this.opponentGrid = server.requeteGrilleJoueur(this.matchId, this.opponentId)
            this.matchState = ETAPE.INITIALISATION
        }
        return state
    }

    fun pickCharacter(row: Int, col: Int) {
        server.requeteChoixPersonnage(this.matchId, this.playerIdKey.id, this.playerIdKey.cle, row, col)
        this.characterPicked = this.playerGrid[row][col]

        println("Vous venez de choisir ${this.characterPicked}")
    }

    fun getPictureOf(row: Int, col: Int, opponent: Boolean = true): Node {
        val baseUrl = "http://localhost:8080/resources/but1/"

        val filename = if (opponent) {
            this.getOpponentGrid()[row][col].url
        } else {
            this.getGrid()[row][col].url
        }

        val fullUrl = "$baseUrl$filename"
        val image = Image(fullUrl)

        val imageView = ImageView(image).apply {
            fitWidth = 80.0
            fitHeight = 80.0
            isPreserveRatio = true
        }
        return imageView
    }




    fun updateGrid(gridCharacter: GridPane, opponent: Boolean = false): GridPane {

        var gridCharacter = GridPane()
        gridCharacter.isGridLinesVisible = true



        for (row in 0 until 4) {
            for (col in 0 until 6) {

                var picture = this.getPictureOf(row, col, opponent)
                val stack = StackPane().apply {
                    children.add(picture)
                    style = "-fx-border-color: black; -fx-border-width: 1;"
                }

                gridCharacter.add(stack, col, row)

            }
        }
        return gridCharacter
    }

    /*
    fun putQuestion(question: String) {
        this.question = question
    }

    fun putAnswer(answer: String) {
        this.answer = answer
    }

    fun removeCharacter(player: Int, characterList: MutableList<Int>) {
        for (i in characterList) {
            this.boardList[player][i] = fakeCharacter
        }
    }

    fun makeGuess(player: Int , characterIndex: Int) : Personnage{
        var character = this.boardList[player][characterIndex]
        this.characterGuess = character
        this.question = "GUESS"
        return character
    }

    fun checkGuess(player: Int) : Boolean{
        return this.characterGuess == this.characterPicked[player]
    }

    fun nextRound() {
        this.question = ""
        this.answer = ""
        this.roundCounter += 1
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
    fun getQuestion() = this.question
    fun getAnswer() = this.answer

    //fun getState() = this.saved
    //fun getWinner() = this.haveWinner
    fun getRound() = this.roundCounter
    fun getGuess() = this.characterGuess
    fun getGrid() = this.playerGrid
    fun getOpponentGrid() = this.opponentGrid
    fun getCurrentPlayer() = this.playerNo


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