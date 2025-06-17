package Controleurs

import info.but1.sae2025.QuiEstCeClient
import info.but1.sae2025.exceptions.QuiEstCeException
import javafx.event.ActionEvent
import javafx.event.EventHandler
import javafx.scene.layout.Pane
import modele.Client
import vue.MainView

import vue.MatchMaking
import vue.Login
import vue.MatchList

class ControleurBoutonRefresh(val client: Client, val matchList : MatchList ) : EventHandler<ActionEvent> {

    // REFRESH -> Grid MatchList
    override fun handle(event: ActionEvent) {

        // Recuperation d'une liste de parties à jour
        matchList.matchList = client.updateMatchList()

        // Actualisation de la grille
        matchList.updateMatchListGrid()

    }
}