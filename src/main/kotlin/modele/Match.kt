package modele

import info.but1.sae2025.data.IdentificationJoueur
import info.but1.sae2025.data.Personnage

class Match(matchId: Int, playerId: IdentificationJoueur, characterList: MutableList<Personnage>) {

    private val matchId: Int
    private var playersList: MutableList<IdentificationJoueur>
    private val characterList: MutableList<Personnage>
    private val fakeCharacter: Personnage
    private var characterPicked: MutableList<Personnage>
    private var boardList: List<MutableList<Personnage>>
    private var roundCounter: Int
    private var question: String
    private var answer: String
    private lateinit var winner: IdentificationJoueur
    private var haveWinner: Boolean
    private var saved: Boolean

    init {
        this.matchId = matchId
        this.playersList = mutableListOf(playerId)
        this.characterList = characterList
        this.fakeCharacter = Personnage("", "", "")
        this.characterPicked = mutableListOf(fakeCharacter, fakeCharacter)
        this.boardList = listOf(characterList.shuffled().toMutableList(), characterList.shuffled().toMutableList())
        this.roundCounter = 0
        this.question = ""
        this.answer = ""
        this.haveWinner = false
        this.saved = false
    }

    // Fonctions principales

    fun joinMatch(player: IdentificationJoueur) {
        this.playersList.add(player)
    }

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

    fun makeGuess(player: IdentificationJoueur, character: Personnage) {
        TODO()
    }

    fun checkGuess(player: IdentificationJoueur, character: Personnage) {
        TODO()
    }

    fun nextRound() {
        this.question = ""
        this.answer = ""
        this.roundCounter += 1
    }

    fun endOfMatch() {
        TODO()
    }


    /// Fonctions de recuperations de variables
    fun getId() = this.matchId
    fun getPlayerList() = this.playersList
    fun getBoardList() = this.boardList
    fun getQuestion() = this.question
    fun getAnswer() = this.answer
    fun getState() = this.saved
    fun getWinner() = this.haveWinner
    fun getRound() = this.roundCounter

    fun getBoardByName(player: Int): List<String> {
        return boardList[player].mapIndexed { index, perso -> "${index + 1}. ${perso.prenom}" }
    }

    fun getMatchInfo(): String {
        val info =
            "**Joueur 1**\n Nom : ${this.playersList[0]}\n Board : ${this.boardList[0]}\n Personnage choisis : ${this.characterPicked[0]}\n\n**Joueur 2**\n Nom : ${this.playersList[1]}\n Board : ${this.boardList[1]}\n Personnage choisis : ${this.characterPicked[1]}"
        return info
    }

    // ETC
    // ETC
    // ETC


}