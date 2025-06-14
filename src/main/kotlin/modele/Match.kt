package modele

import info.but1.sae2025.QuiEstCeClient
import info.but1.sae2025.data.IdentificationJoueur
import info.but1.sae2025.data.Personnage

class Match(matchId: Int, playerId: IdentificationJoueur, server : QuiEstCeClient) {

    private val server : QuiEstCeClient
    private val matchId: Int
    private var playerList: MutableList<IdentificationJoueur>
    private val gridPlayer : MutableList<List<List<Personnage>>>
    private val fakeCharacter: Personnage
    private var characterPicked: MutableList<Personnage>
    private lateinit var characterGuess : Personnage
    //private var boardList: List<MutableList<Personnage>>
    private var roundCounter: Int
    private var question: String
    private var answer: String
    private lateinit var winner: IdentificationJoueur
    private var haveWinner: Boolean
    private var saved: Boolean
    private var currentPlayer : Int

    init {
        this.server = server
        this.matchId = matchId
        this.playerList = mutableListOf(playerId)
        this.gridPlayer = mutableListOf(server.requeteGrilleJoueur(this.matchId, this.playerList[0].id))
        this.fakeCharacter = Personnage("", "", "")
        this.characterPicked = mutableListOf(fakeCharacter, fakeCharacter)
        //this.boardList = listOf(characterList.shuffled().toMutableList(), characterList.shuffled().toMutableList())
        this.roundCounter = 0
        this.question = ""
        this.answer = ""
        this.haveWinner = false
        this.saved = false
        this.currentPlayer = 0
    }

    //
    //
    //
    //
    // Fonctions principales

    fun joinMatch(player: IdentificationJoueur) {
        this.playerList.add(player)
        this.currentPlayer = 1
        this.gridPlayer.add(server.requeteGrilleJoueur(this.matchId, this.playerList[0].id))
    }

    /*


    fun pickCharacter(player: Int, character: Int): Personnage {

        this.characterPicked[player] = this.boardList[player][character]

        return this.characterPicked[player]
    }

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
    fun getPlayerList() = this.playerList
    fun getCharacterPicked()= this.characterPicked
    //fun getBoardList() = this.boardList
    fun getQuestion() = this.question
    fun getAnswer() = this.answer
    fun getState() = this.saved
    fun getWinner() = this.haveWinner
    fun getRound() = this.roundCounter
    fun getGuess() = this.characterGuess
    fun getGrid() = this.gridPlayer
    fun getCurrentPlayer() = this.currentPlayer


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