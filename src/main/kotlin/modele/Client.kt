package modele

import Welcome
import info.but1.sae2025.QuiEstCeClient
import info.but1.sae2025.data.IdentificationJoueur
import info.but1.sae2025.data.Joueur
import info.but1.sae2025.exceptions.QuiEstCeException
import javafx.animation.FadeTransition
import javafx.util.Duration
import kotlinx.serialization.json.Json
import vue.Login
import vue.MainView
import java.io.File

class Client(server: QuiEstCeClient, val mainView: MainView) {

    private var server: QuiEstCeClient
    private var playerList: MutableList<Pair<Joueur, IdentificationJoueur>>
    private var playerListServer: MutableList<Pair<Joueur, IdentificationJoueur?>>
    private lateinit var currentMatch: Match
    private var matchList: List<Int>
    private var currentPlayer: Pair<Joueur, IdentificationJoueur>
    var title: String

    init {
        this.server = server
        this.playerList = getPlayerListJson()
        this.playerListServer = getPlayerListServer()
        //this.matchList = mutableListOf()
        this.matchList = server.requeteListeParties()
        this.title = "Match n°$this.id"

        this.currentPlayer = Pair(Joueur("", ""), IdentificationJoueur(0, ""))

    }

    //
//
//
//
// Fonctions principales
    fun playerLogin(lastName: String, name: String): Pair<String, String> {

        var lastName = lastName.uppercase()
        var name = name.lowercase()
        var player = Joueur(lastName, name)

        playerListServer = getPlayerListServer()

        var matchingPlayerServer = playerListServer.find { it.first == player }
        var matchingPlayerJson = playerList.find { it.first == player }

        println("find on server $matchingPlayerServer")
        println("find on local : $matchingPlayerJson")

        if (matchingPlayerServer == null) {
            var idKey = server.requeteCreationJoueur(lastName, name)
            println("***  Un joueur a été crée  ***")
            this.playerList.add(Pair(player, idKey))
            this.currentPlayer = Pair(player, idKey)

            // Sérialisation → JSON
            checkJsonPresent()
            val jsonData = Json.encodeToString(playerList)
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
/// Fonctions SERVER

    fun getIdWithName(id: Int) {

        for (id in server.requeteJoueurs())
            return



        return
    }


    fun getPlayerListServer(): MutableList<Pair<Joueur, IdentificationJoueur?>> {
        var pairList: MutableList<Pair<Joueur, IdentificationJoueur?>> = mutableListOf()

        for (i in 0 until this.server.requeteJoueurs().size) {
            val id = this.server.requeteJoueurs()[i]
            var player = this.server.requeteJoueur(id)

            var matchingPlayer = playerList.find { it.first == player }

            if (matchingPlayer != null) {
                pairList.add(matchingPlayer)
            } else {
                pairList.add(Pair(player, null))
            }
        }

        return pairList
    }


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

    fun updateMatchList() {
        this.matchList = server.requeteListeParties()
    }
/*
    fun showPopUp(viewToPop: Pane, viewToBack: Pane, time: Double, stat: Boolean = false) {
        viewToPop.opacity = 0.0
        this.mainView.center = viewToPop

        // Transition d'apparition
        val fadeIn = FadeTransition(Duration.seconds(time + 0.5), viewToPop).apply {
            fromValue = 0.0
            toValue = 1.0
            delay = Duration.seconds(0.5)
        }

        // Transition de disparition après `time` secondes
        val fadeOut = FadeTransition(Duration.seconds(time + 0.5), viewToPop).apply {
            fromValue = 1.0
            toValue = 0.0
            delay = Duration.seconds(0.5)
        }

        // Une fois la disparition finie, on remet viewToBack
        fadeOut.setOnFinished {
            this.mainView.center = viewToBack
        }

        // Enchaîner les transitions
        fadeIn.setOnFinished {
            fadeOut.play()
        }

        fadeIn.play()
    }
    */
    fun start(welcome: Welcome, login: Login, time: Double, stat: Boolean = false) {
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
    /*
    fun playerIsInList(server: QuiEstCeClient, lastName: String, name: String): Boolean {
        var tmpPlayer = Joueur(lastName, name)
        return (tmpPlayer in getPlayerListServer())
    }


     */

///// FONCTION QUI CREE UNE LISTE POUR AFFICHAGE DANS LE CLIENT, A VOIR SI UTILE  //////
    /* fun createPlayerList(server : QuiEstCeClient): MutableList<Pair<String, Int>>{
        val playerList = getServerPlayerList(server)
        val idList = server.requeteJoueurs()
        val list = mutableMapOf<IdentificationJoueur, Joueur>()

        val pairs = mutableListOf<Pair<String, Int>>()

        for (i in playerList.indices) {
            val player = playerList[i]
            val id = idList[i]

            var name = "${player.prenom[0].uppercase()}${player.prenom.substring(1)} "
            var lastName = "${player.nom[0].uppercase()}${player.nom.substring(1)} "

            pairs.add("$name $lastName" to id)
        }
        return pairs
    }
     */


    //
//
//
//
/// Fonctions de recuperations de données
    fun getPlayerList() = this.playerList

    //fun getMatchList() = this.matchList
    fun getMatchList() = this.matchList

    fun getCurrentPlayer() = this.currentPlayer
    fun getCurrentMatch() = this.currentMatch
    fun getMatchState() = server.requeteEtatPartie(this.currentMatch.getId())


}


/*         this.playerListJson =File("data/playerList.json")
if (!this.playerListJson.exists()) {
    playerListJson.parentFile.mkdirs() // crée le dossier si nécessaire
    playerListJson.writeText("[]") // initialise avec une liste vide
*/
