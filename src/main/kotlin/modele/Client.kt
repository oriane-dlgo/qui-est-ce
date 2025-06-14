package modele

import info.but1.sae2025.QuiEstCeClient
import info.but1.sae2025.data.Joueur
import info.but1.sae2025.data.IdentificationJoueur
import info.but1.sae2025.data.Personnage
import kotlinx.serialization.json.Json
import java.io.File

class Client(server: QuiEstCeClient) {

    private var server: QuiEstCeClient
    private var playerList: MutableList<Pair<IdentificationJoueur, Joueur>>
    private var playerListServer : MutableList<Pair<IdentificationJoueur?, Joueur>>
    private var matchList: MutableList<Int>
    private var currentPlayer: Int = 0

    init {
        this.server = server
        this.playerList = mutableListOf()
        this.matchList = mutableListOf()
        this.playerListServer = getPlayerListServer()
    }
//
//
//
//
// Fonctions principales

    fun playerCreate(lastName: String, name: String) {

        var lastName = lastName.uppercase()
        var name = name.lowercase()
        var idKey = server.requeteCreationJoueur(lastName, name)
        var player = Joueur(lastName, name)
        this.playerList.add(Pair(idKey, player))

        /*
        // Sérialisation → JSON
        val jsonData = Json.encodeToString(playerList)
        File("data/playerList.json").writeText(jsonData)
        */

    }

    fun matchCreate(playerId: IdentificationJoueur, characterList: MutableList<Personnage>): Match {

        val matchId = this.matchList.size + 1

        val match = Match(matchId, playerId, characterList)
        this.matchList.add(match.getId())

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


    fun getPlayerListServer(): MutableList<Pair<IdentificationJoueur?, Joueur>> {
        var pairList : MutableList<Pair<IdentificationJoueur?, Joueur>> = mutableListOf()

        for (i in 0 until this.server.requeteJoueurs().size) {
            val id = this.server.requeteJoueurs()[i]
            var player = this.server.requeteJoueur(id)

            var matchingPlayer = playerList.find { it.second == player }

            if (matchingPlayer != null){
                pairList.add(matchingPlayer)
            }
            else{
                pairList.add(Pair(null, player))
            }
        }

        return pairList
    }


    fun getJsonPlayerList(): MutableList<Pair<IdentificationJoueur, Joueur>> {

        // Désérialisation ← JSON
        val content = File("data/playerList.json").readText()
        val list = Json.decodeFromString<MutableList<Pair<IdentificationJoueur, Joueur>>>(content)

        return list
    }

    /*
    fun playerIsInList(server: QuiEstCeClient, lastName: String, name: String): Boolean {
        var tmpPlayer = Joueur(lastName, name)
        return (tmpPlayer in getPlayerListServer())
    }


     */
    fun playerLogIn() {
        this.currentPlayer
    }


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
    fun getMatchList() = this.matchList


}



/*         this.playerListJson =File("data/playerList.json")
if (!this.playerListJson.exists()) {
    playerListJson.parentFile.mkdirs() // crée le dossier si nécessaire
    playerListJson.writeText("[]") // initialise avec une liste vide
*/
