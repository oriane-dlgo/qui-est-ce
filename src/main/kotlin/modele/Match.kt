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

    private var matchState: ETAPE

    private var characterPicked: Personnage
    private var question: String
    private var answer: String
    var charPicked: Int = -1

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

        this.listSelChar = mutableListOf()
        this.listHideChar = mutableListOf()


        if (playerNo == 1) {
            this.opponentId = server.requeteEtatPartie(matchId).idJoueur2
        } else {
            this.opponentId = server.requeteEtatPartie(matchId).idJoueur1
        }

        if (this.opponentId != -1) {
            this.opponentGrid = server.requeteGrilleJoueur(this.matchId, this.opponentId)
            println("LLLAAAA ON DEMANDE LA GRILLE DE LADVERSAIRE LA PREMIERE FOIS")
        }



        this.roundCounter = 1
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

    fun nextRound() {
        this.question = ""
        this.answer = ""
        this.roundCounter += 1
        server.requeteChercherEncore(this.matchId, this.playerIdKey.id, this.playerIdKey.cle)
    }

    fun printState() {

        println("Serveur : ${server.requeteEtatPartie(this.matchId)}")
        println("Match : ${this.matchState}")
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

    /*
    fun updateMatchState(): EtatPartie {

        val state = server.requeteEtatPartie(this.matchId)

        if (state.etape == ETAPE.INITIALISATION && matchState == ETAPE.CREEE) {
            this.matchState = ETAPE.INITIALISATION
        }
        if (state.etape == ETAPE.ATTENTE_QUESTION && matchState == ETAPE.INITIALISATION) {
            this.matchState = ETAPE.ATTENTE_QUESTION
        }
        if (state.etape == ETAPE.ATTENTE_REPONSE && matchState == ETAPE.ATTENTE_QUESTION) {
            this.matchState = ETAPE.ATTENTE_REPONSE
        }
        if (state.etape == ETAPE.ATTENTE_REFLEXION && matchState == ETAPE.ATTENTE_REPONSE) {
            this.matchState = ETAPE.ATTENTE_REFLEXION
        }


        return state
    }

     */
    fun updateMatchState(): EtatPartie {
        this.matchState = server.requeteEtatPartie(this.matchId).etape
        return server.requeteEtatPartie(this.matchId)
    }
    fun resetListSelChar(){
        this.listSelChar = mutableListOf()
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
            fitWidth = 100.0
            fitHeight = 100.0
            isPreserveRatio = true
        }
        return imageView
    }

    fun updateGrid(gridCharacter: GridPane, opponent: Boolean, listHideChar: List<Int> = listOf()): GridPane {

        gridCharacter.children.clear()
        println("**** \n La liste est celle de l'adversaire = $opponent \n****")
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

                /////// TEST ENCADRER ROUGE
                // ID pour l'identifiant de la case
                stack.userData = index

                // Ajoute le clic
                //if (this.matchState == ETAPE.INITIALISATION || this.matchState == ETAPE.ATTENTE_REFLEXION)
                stack.setOnMouseClicked {

                        val id = stack.userData as Int

                        if (this.matchState == ETAPE.INITIALISATION && this.charPicked == -1) {
                            println("\n\n\nÉTAT ACTUEL DU MATCH = ${this.matchState}\n\n\n")

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
                    if ((this.playerNo == 1 && this.roundCounter %2 != 0) || (this.playerNo == 2 && this.roundCounter %2 == 0) ) {
                        if (this.matchState == ETAPE.ATTENTE_REFLEXION) {
                            // Sélection libre (phase de jeu ?)
                            if (!this.listSelChar.contains(id)) {
                                this.listSelChar.add(id)
                                stack.style = "-fx-border-color: #4e6b6e; -fx-border-width: 5;"
                            } else {
                                this.listSelChar.remove(id)
                                stack.style = "-fx-border-color: #78a9af; -fx-border-width: 5;"
                            }
                        }

                        println("Cases sélectionnées : $listSelChar")
                    }
                }
                ///////////////////
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
    fun getQuestion() = this.question
    fun getAnswer() = this.answer

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