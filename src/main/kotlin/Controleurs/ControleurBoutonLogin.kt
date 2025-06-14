package Controleurs

import info.but1.sae2025.QuiEstCeClient
import info.but1.sae2025.exceptions.QuiEstCeException
import javafx.event.ActionEvent
import javafx.event.EventHandler
import modele.Client
import vue.MainView
import vue.Listpartie

import vue.Game
import vue.Login

class ControleurBoutonLogin(val client: Client, val view: MainView, val loginView: Login) : EventHandler<ActionEvent> {
    override fun handle(event: ActionEvent) {
        val gameView = Game()

        try {
            // Conexion joueur
            client.playerLogin(loginView.nom.text, loginView.prenom.text)

            // Changement de la vue
            view.setCenterView(gameView)
            gameView.btnjoin.setOnAction(ControleurBoutonRejoindre(client, view))

            // Logs
            println("Vous êtes : ${client.getCurrentPlayer()}\n")

            println("Liste des joueurs sur le serveur : ${client.getPlayerListServer()}")
            println("Liste des joueurs sur le client : ${client.getPlayerList()}")



        } catch (e: QuiEstCeException) {
            // Erreur si le joueur crée existe deja sur le serveur mais sur une autre machine
            println(e)
        }



    }
}