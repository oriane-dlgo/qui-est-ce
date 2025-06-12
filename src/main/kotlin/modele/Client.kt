package modele

import info.but1.sae2025.QuiEstCeClient
import info.but1.sae2025.data.Joueur
import info.but1.sae2025.data.IdentificationJoueur
import info.but1.sae2025.data.Personnage

class Client(server: QuiEstCeClient) {

    private var server: QuiEstCeClient

    private var playerList: MutableList<Joueur>
    private var matchList: MutableList<Int>
    private var currentPlayer : Int = 0

    init {
        this.server = server
        this.playerList = getServerPlayerList(server)
        this.matchList = mutableListOf()
    }

    //
    //
    //
    //
    // Fonctions principales
    fun playerCreate(server: QuiEstCeClient, player: Joueur) {
        var nom = player.nom.lowercase()
        var prenom = player.prenom.lowercase()
        server.requeteCreationJoueur(nom, prenom)
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

    fun getIdWithName(id : Int){

        for (id in server.requeteJoueurs())
            return



        return
    }


    fun getServerPlayerList(server: QuiEstCeClient): MutableList<Joueur> {
        var list = mutableListOf<Joueur>()
        for (i in 0 until server.requeteJoueurs().size) {
            val id = server.requeteJoueurs()[i]
            list.add(server.requeteJoueur(id))
        }
        return list
    }

    fun playerIsInList(server : QuiEstCeClient, lastName : String, name : String) : Boolean{
        var tmpPlayer = Joueur(lastName, name)
        return (tmpPlayer in getServerPlayerList(server))
    }

    fun playerLogIn(){
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