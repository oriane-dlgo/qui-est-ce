package Controleurs

import javafx.event.ActionEvent
import javafx.event.EventHandler
import modele.Client
import vue.MainView
import vue.Listpartie

import vue.Game
import vue.Login

class ControleurBoutonLogin(val client: Client, val view: MainView) : EventHandler<ActionEvent> {
    override fun handle(event: ActionEvent) {
        val gameView = Game()
        val loginView = Login()

        view.setCenterView(gameView)

        println("Vous avez cliquer sur \"Connexion\" ")

        client.playerCreate(loginView.nom.toString(), loginView.prenom.toString())

        //pour passer à la vue Listpartie via le bouton btnjoin
        gameView.btnjoin.setOnAction {
            val listVue = Listpartie()
            view.setCenterView(listVue)
        }
    }
}