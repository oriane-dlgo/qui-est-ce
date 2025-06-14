package Controleurs

import info.but1.sae2025.QuiEstCeClient
import info.but1.sae2025.exceptions.QuiEstCeException
import javafx.event.ActionEvent
import javafx.event.EventHandler
import modele.Client
import vue.MainView
import vue.Listpartie

import vue.MatchMaking
import vue.Login

class ControleurBoutonLogin(val client: Client, val view: MainView, val loginView: Login) : EventHandler<ActionEvent> {
    override fun handle(event: ActionEvent) {
        val matchmakingView = MatchMaking()

        try {
            // Connexion joueur
            client.playerLogin(loginView.nom.text, loginView.prenom.text)

            // Changement de la vue

            view.setCenterView(matchmakingView)
            matchmakingView.btnjoin.setOnAction(ControleurBoutonRejoindrePartie(client, view))

            // Logs
            println("Vous êtes : ${client.getCurrentPlayer()}\n")

            println("Liste des joueurs sur le serveur : ${client.getPlayerListServer()}")
            println("Liste des joueurs sur le client : ${client.getPlayerList()}")



        } catch (e: QuiEstCeException) {
            println(e)
        }

        matchmakingView.btnjoin.setOnAction(ControleurBoutonRejoindrePartie(client, view))

    }
}

/*
//pour passer à la vue Listpartie via le bouton btnjoin
gameView.btnjoin.setOnAction {
    val listVue = Listpartie()
    view.setCenterView(listVue)
        client.playerCreate(loginView.nom.toString(), loginView.prenom.toString())
//
        gameView.btnjoin.setOnAction(ControleurBoutonRejoindre(client, view))

//        //pour passer à la vue Listpartie via le bouton btnjoin
//        gameView.btnjoin.setOnAction {
//            val listVue = Listpartie()
//            view.setCenterView(listVue)
//        }
    }
}

 */