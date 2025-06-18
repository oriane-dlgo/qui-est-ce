package Controleurs


import javafx.event.ActionEvent
import javafx.event.EventHandler
import modele.Client
import vue.MatchList

class ControleurBoutonRefresh(val client: Client, val matchList: MatchList) : EventHandler<ActionEvent> {

    // REFRESH -> Grid MatchList
    override fun handle(event: ActionEvent) {

        // Recuperation d'une liste de parties à jour
        matchList.matchList = client.updateMatchList()

        // Actualisation de la grille
        matchList.updateMatchListGrid()
    }
}