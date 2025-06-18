package modele

import Welcome
import info.but1.sae2025.QuiEstCeClient
import info.but1.sae2025.data.IdentificationJoueur
import info.but1.sae2025.data.Joueur
import info.but1.sae2025.exceptions.QuiEstCeException
import javafx.animation.FadeTransition
import javafx.util.Duration
import kotlinx.serialization.json.Json
import vue.GameBoard
import vue.Login
import vue.MainView
import vue.NextRound
import java.io.File

class Client(server: QuiEstCeClient, val mainView: MainView) {

    private var server: QuiEstCeClient
    private var playerListLocal: MutableList<Pair<Joueur, IdentificationJoueur>>
    private var playerListServer: MutableList<Pair<Joueur, IdentificationJoueur?>>
    private lateinit var currentMatch: Match
    private var matchList: List<Int>
    private var currentPlayer: Pair<Joueur, IdentificationJoueur>

    init {
        this.server = server
        this.playerListLocal = getPlayerListJson()
        this.playerListServer = getPlayerListServer()
        this.matchList = server.requeteListeParties()
        this.currentPlayer = Pair(Joueur("", ""), IdentificationJoueur(0, ""))
    }

    //
//
//
//
/// Fonctions principales
    fun playerLogin(lastName: String, name: String): Pair<String, String> {

        var lastName = lastName.uppercase()
        var name = name.lowercase()
        var player = Joueur(lastName, name)

        playerListServer = getPlayerListServer()

        var matchingPlayerServer = playerListServer.find { it.first == player }
        var matchingPlayerJson = playerListLocal.find { it.first == player }

        println("find on server $matchingPlayerServer")
        println("find on local : $matchingPlayerJson")

        if (matchingPlayerServer == null) {
            var idKey = server.requeteCreationJoueur(lastName, name)
            println("***  Un joueur a été crée  ***")
            this.playerListLocal.add(Pair(player, idKey))
            this.currentPlayer = Pair(player, idKey)

            // Sérialisation → JSON
            checkJsonPresent()
            val jsonData = Json.encodeToString(playerListLocal)
            File("data/playerList.json").writeText(jsonData)
        } else {
            if (matchingPlayerJson == null) {
                throw QuiEstCeException("Impossible de creer un personnage déjà crée sur une autre machine")
            } else {
                println("***  Vous venez de vous connecter  ***")
                this.currentPlayer = matchingPlayerJson
            }
        }
        return Pair(lastName, name)
    }

    fun matchCreate(): Match {

        val playerIdKey = this.currentPlayer.second
        val matchId = server.requeteCreationPartie(playerIdKey.id, playerIdKey.cle)

        val match = Match(server, matchId, playerIdKey, 1)
        this.currentMatch = match

        println("\nLa partie n°$matchId vient d'être crée\n")
        return match
    }

    fun matchJoin(matchId: Int): Match {
        val playerIdKey = this.currentPlayer.second
        server.requeteRejoindrePartie(matchId, playerIdKey.id, playerIdKey.cle)

        val match = Match(server, matchId, playerIdKey, 2)
        this.currentMatch = match
        return match
    }

    //
    //
    //
    //
    /// JSON MANAGE
    fun getPlayerListJson(): MutableList<Pair<Joueur, IdentificationJoueur>> {

        checkJsonPresent()
        // Désérialisation ← JSON
        val content = File("data/playerList.json").readText()
        val list = Json.decodeFromString<MutableList<Pair<Joueur, IdentificationJoueur>>>(content)

        return list
    }

    fun checkJsonPresent() {

        val dataDir = File("data")
        if (!dataDir.exists()) {
            dataDir.mkdirs()
        }
        val jsonFile = File(dataDir, "playerList.json")
        if (!jsonFile.exists()) {
            jsonFile.writeText("[]")
        }
    }

    //
//
//
//
/// Fonctions SERVER
    fun getPlayerListServer(): MutableList<Pair<Joueur, IdentificationJoueur?>> {
        var pairList: MutableList<Pair<Joueur, IdentificationJoueur?>> = mutableListOf()

        for (i in 0 until this.server.requeteJoueurs().size) {
            val id = this.server.requeteJoueurs()[i]
            var player = this.server.requeteJoueur(id)

            var matchingPlayer = playerListLocal.find { it.first == player }

            if (matchingPlayer != null) {
                pairList.add(matchingPlayer)
            } else {
                pairList.add(Pair(player, null))
            }
        }
        return pairList
    }

    fun updateMatchList(): List<Int> {
        val newList = server.requeteListeParties()
        this.matchList = newList
        return newList
    }

    //
    //
    //
    //
    /// POPUPS
    fun start(welcome: Welcome, login: Login, time: Double) {
        welcome.opacity = 0.0
        this.mainView.center = welcome

        // Transition d'apparition
        val fadeIn = FadeTransition(Duration.seconds(time + 0.5), welcome).apply {
            fromValue = 0.0
            toValue = 1.0
            delay = Duration.seconds(0.5)
        }

        // Transition de disparition après `time` secondes
        val fadeOut = FadeTransition(Duration.seconds(time + 0.5), welcome).apply {
            fromValue = 1.0
            toValue = 0.0
            delay = Duration.seconds(0.5)
        }

        // Une fois la disparition finie, on remet viewToBack
        fadeOut.setOnFinished {
            this.mainView.center = login
            login.startLogin()
        }

        // Enchaîner les transitions
        fadeIn.setOnFinished {
            fadeOut.play()
        }

        fadeIn.play()
    }

    fun nextRoundPopUp(match: Match, gameBoard: GameBoard, nextRound: NextRound): MutableList<Int> {
        nextRound.opacity = 0.0
        this.mainView.center = nextRound
        var keyPass = mutableListOf<Int>()

        // Transition d'apparition
        val fadeIn = FadeTransition(Duration.seconds(1.0), nextRound).apply {
            fromValue = 0.0
            toValue = 1.0
            delay = Duration.seconds(0.0)
        }

        // Transition de disparition après `time` secondes
        val fadeOut = FadeTransition(Duration.seconds(1.0), nextRound).apply {
            fromValue = 1.0
            toValue = 0.0
            delay = Duration.seconds(0.0)
        }

        // Une fois la disparition finie, on remet viewToBack
        fadeOut.setOnFinished {
            this.mainView.center = gameBoard
        }

        // Enchaîner les transitions
        fadeIn.setOnFinished {
            fadeOut.play()
        }

        fadeIn.play()
        return keyPass
    }

    //
//
//
//
/// Fonctions de recuperations de données
    //fun getPlayerList() = this.playerList
    //fun getListMatchFini() = server.requeteListePartiesTerminees()
    //fun getMatchList() = this.matchList
    fun getMatchList() = this.matchList
    fun getListMatchCreate() = server.requeteListePartiesCreees()
    fun getCurrentPlayer() = this.currentPlayer
    //fun getCurrentMatch() = this.currentMatch
    //fun getMatchState() = server.requeteEtatPartie(this.currentMatch.getId())
    //fun getPlayerById(id : Int) = server.requeteJoueur(id)
}
