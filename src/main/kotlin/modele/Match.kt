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
        this.roundCounter += 1
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




















    ////////////////////// ICI FUN UPDATE GRID

    fun updateGrid(gridCharacter : GridPane , opponent: Boolean, listHideChar: List<Int> = listOf()): GridPane {

        gridCharacter.children.clear()
        println("**** \n La liste est celle de l'adversaire = $opponent \n****")
        gridCharacter.isGridLinesVisible = true

        var index = 1  // Pour associer chaque case à un numéro (1 à 24)

        for (row in 0 until 4) {
            for (col in 0 until 6) {
                val stack = StackPane()

                val picture = getPictureOf(row, col, opponent)

                if (index in listHideChar){
                    picture.opacity = 0.2
                }

                stack.children.add(picture)
                stack.style = "-fx-border-color: black; -fx-border-width: 1;"
                gridCharacter.add(stack, col, row)

                index++
            }
        }

        return gridCharacter
    }






































/*
    fun updateGrid(): GridPane {
        val state = this.matchState
        val listSelChar = this.listSelChar
        val listHideChar = this.listHideChar
        val gridCharacter = GridPane().apply { isGridLinesVisible = true }

        println("*******\n liste selection : $listSelChar \n liste hidden : $listHideChar\n STATE : $state \n*********")


        var index = 1

        for (row in 0 until 4) {
            for (col in 0 until 6) {
                val stack = StackPane()
                stack.userData = index

                val picture = if (state == ETAPE.INITIALISATION || state == ETAPE.CREEE) {
                    getPictureOf(row, col, false)
                } else {
                    getPictureOf(row, col)
                }
                // Appliquer opacité si caché
                if (index in listHideChar) {
                    picture.opacity = 0.3
                }

                stack.children.add(picture)

                // Définir comportement selon l'état
            if (state == ETAPE.INITIALISATION){
                        /*
                                                stack.setOnMouseClicked {

                                                    listSelChar.clear()
                                                    listSelChar.add(index)

                                                    if (listSelChar.contains(index)) {
                                                        listSelChar.remove(index)
                                                        stack.style = "-fx-border-color: black; -fx-border-width: 1;"
                                                    } else {
                                                        listSelChar.add(index)
                                                        stack.style = "-fx-border-color: red; -fx-border-width: 3;"
                                                    }
                                                }*/
                        stack.setOnMouseClicked {
                            // Si déjà sélectionné, on désélectionne tout
                            if (listSelChar.contains(index)) {
                                listSelChar.clear()
                                stack.style = "-fx-border-color: black; -fx-border-width: 1;"
                            } else {
                                // Sinon, on sélectionne uniquement cette case
                                listSelChar.clear()
                                listSelChar.add(index)

                                // On met à jour toutes les cases
                                gridCharacter.children.filterIsInstance<StackPane>().forEach { s ->
                                    val id = s.userData as Int
                                    s.style = if (id == index)
                                        "-fx-border-color: red; -fx-border-width: 3;"
                                    else
                                        "-fx-border-color: black; -fx-border-width: 1;"
                                }
                            }


                            // Style initial selon sélection
                            stack.style = if (listSelChar.contains(index)) {
                                "-fx-border-color: red; -fx-border-width: 3;"
                            } else {
                                "-fx-border-color: black; -fx-border-width: 1;"
                            }

                            gridCharacter.add(stack, col, row)
                            index++
                        }

                    }


                    ETAPE.ATTENTE_REFLEXION -> {
                        stack.setOnMouseClicked {
                            if (listSelChar.contains(index)) {
                                listSelChar.remove(index)
                                stack.style = "-fx-border-color: black; -fx-border-width: 1;"
                            } else {
                                listSelChar.add(index)
                                stack.style = "-fx-border-color: red; -fx-border-width: 3;"
                            }
                        }
                    }

                    else -> {
                        // Pas d'action : clics désactivés
                    }
                }

                // Style par défaut (selon si sélectionné ou non)
                stack.style = if (index in listSelChar)
                    "-fx-border-color: red; -fx-border-width: 3;"
                else
                    "-fx-border-color: black; -fx-border-width: 1;"

                gridCharacter.add(stack, col, row)
                index++
            }
        }

        return gridCharacter
    }
*/

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
    fun getPlayerNo() = this.playerNo


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