package Controleurs

import javafx.event.ActionEvent
import javafx.event.EventHandler
import modele.Client
import vue.MainView
import vue.MatchMaking

class ControleurBoutonRetour(
    private val vuePrincipale: MainView,
    private val modele: Client
) : EventHandler<ActionEvent> {

    override fun handle(event: ActionEvent) {
        val matchMakingView = MatchMaking()

        // Rebranche les contrôleurs des boutons
        matchMakingView.btnJoin.setOnAction(ControleurBoutonRejoindrePartie(modele, vuePrincipale))
        matchMakingView.btnNew.setOnAction(ControleurBoutonNouvellePartie(modele, vuePrincipale, matchMakingView))

        // Réaffiche la vue MatchMaking dans la vue principale
        vuePrincipale.center = matchMakingView
    }
}