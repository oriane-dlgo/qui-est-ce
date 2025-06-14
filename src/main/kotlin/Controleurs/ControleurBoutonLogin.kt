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
            client.playerCreate(loginView.nom.text, loginView.prenom.text)
            println("create")
            view.setCenterView(gameView)
            // Changement de la vue
            println("switch view")


            // logs
            println("Vous avez cliquer sur \"Connexion\" ")
            println(client.getPlayerList())
            println(client.getPlayerListServer())
            println(client.getCurrentPlayer())


        } catch (e: QuiEstCeException) {
            println(e)
        }



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