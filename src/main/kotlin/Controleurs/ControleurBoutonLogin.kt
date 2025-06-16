package Controleurs

import info.but1.sae2025.exceptions.QuiEstCeException
import javafx.event.ActionEvent
import javafx.event.EventHandler
import modele.Client
import vue.MainView

import vue.MatchMaking
import vue.Login

class ControleurBoutonLogin(val client: Client, val view: MainView, val loginView: Login) : EventHandler<ActionEvent> {
    override fun handle(event: ActionEvent) {
        val matchMakingView = MatchMaking()


        try {
            // Connexion joueur
            client.playerLogin(loginView.nom.text, loginView.prenom.text)

            // Mise à jour du message de bienvenue dans MatchMaking
            matchMakingView.setBienvenueMessage(loginView.nom.text, loginView.prenom.text)
            // Changement de la vue
            view.setCenterView(matchMakingView)
            matchMakingView.btnJoin.setOnAction(ControleurBoutonRejoindrePartie(client, view))
            matchMakingView.btnNew.setOnAction(ControleurBoutonNouvellePartie(client, view, matchMakingView))

            // Logs
            println("Vous êtes : ${client.getCurrentPlayer()}\n")

            println("Liste des joueurs sur le serveur : ${client.getPlayerListServer()}")
            println("Liste des joueurs sur le client : ${client.getPlayerList()}")

        } catch (e: QuiEstCeException) {
            println(e)
        }
    }
}