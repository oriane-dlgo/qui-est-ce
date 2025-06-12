package modele

import info.but1.sae2025.data.Joueur
import info.but1.sae2025.data.IdentificationJoueur
import info.but1.sae2025.data.Personnage

class Client {

    private var playersList: MutableMap<IdentificationJoueur, Joueur>
    private var matchList: MutableList<Int>

    init {
        this.playersList = mutableMapOf()
        this.matchList = mutableListOf()
    }

    fun playerCreate() {
        TODO()
    }

    fun matchCreate(playerId: IdentificationJoueur, characterList: MutableList<Personnage>): Match {

        val matchId = this.matchList.size + 1

        val match = Match(matchId, playerId, characterList)
        this.matchList.add(match.getId())

        return match

    }

    /// Fonctions de recuperations de données
    fun getPlayerList() = this.playersList
    fun getMatchList() = this.matchList


}