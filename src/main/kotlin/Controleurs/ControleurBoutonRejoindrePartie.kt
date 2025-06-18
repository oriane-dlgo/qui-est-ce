package Controleurs

import javafx.event.ActionEvent
import javafx.event.EventHandler
import modele.Client
import vue.MainView
import vue.MatchList


class ControleurBoutonRejoindrePartie(val client: Client, val view: MainView, val matchList: MatchList) :
    EventHandler<ActionEvent> {

    // AFFICHE -> Vue MatchList
    override fun handle(event: ActionEvent) {

        // Recuperation d'une liste de parties à jour
        matchList.matchList = client.updateMatchList()

        // Actualisation de la grille
        matchList.updateMatchListGrid()

        // Affichage
        view.center = matchList

    }
}