package modele

import info.but1.sae2025.QuiEstCeClient
import info.but1.sae2025.data.IdentificationJoueur
import info.but1.sae2025.data.Personnage

class Match(server : QuiEstCeClient, matchId: Int, playerIdKey: IdentificationJoueur, playerNo : Int) {

    private val server : QuiEstCeClient
    private val matchId: Int
    private var playerIdKey : IdentificationJoueur
    private var playerNo : Int
    private var playerGrid : List<List<Personnage>>
    private var opponentId : Int
    private lateinit var opponentGrid : List<List<Personnage>>

    private var characterPicked: Personnage
    private var question: String
    private var answer: String

    // A CHECK
    private lateinit var characterGuess : Personnage
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


        if (playerNo == 0){
            this.opponentId = server.requeteEtatPartie(matchId).idJoueur2
        }
        else{
            this.opponentId = server.requeteEtatPartie(matchId).idJoueur1
        }

        if (this.opponentId != -1){
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

    fun pickCharacter(row : Int, col : Int){
        server.requeteChoixPersonnage(this.matchId, this.playerIdKey.id, this.playerIdKey.cle, row, col)
        this.characterPicked = this.playerGrid[row][col]
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
    fun getCharacterPicked()= this.characterPicked
    //fun getBoardList() = this.boardList
    fun getQuestion() = this.question
    fun getAnswer() = this.answer
    //fun getState() = this.saved
    //fun getWinner() = this.haveWinner
    fun getRound() = this.roundCounter
    fun getGuess() = this.characterGuess
    fun getGrid() = this.playerGrid
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