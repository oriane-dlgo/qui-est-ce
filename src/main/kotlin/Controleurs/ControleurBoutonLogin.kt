package Controleurs

import javafx.event.ActionEvent
import javafx.event.EventHandler
import modele.Client
import vue.MainView
import vue.Listpartie

import vue.Game
import vue.Login

class ControleurBoutonLogin(val client: Client, val view: MainView, val loginView : Login) : EventHandler<ActionEvent> {
    override fun handle(event: ActionEvent) {
        val gameView = Game()

        // Changement de la vue
        view.setCenterView(gameView)
        //loginView.nom.text =

        // Creation du personnage
        client.playerCreate(loginView.nom.text, loginView.prenom.text)


        // logs
        println("Vous avez cliquer sur \"Connexion\" ")
        println(client.getPlayerList())
        println(client.getPlayerListServer())

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