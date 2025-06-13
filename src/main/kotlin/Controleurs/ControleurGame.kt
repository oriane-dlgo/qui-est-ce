package Controleurs

import javafx.event.ActionEvent
import javafx.event.EventHandler
import modele.Client
import vue.MainView
import vue.Listpartie

import vue.Game

class ControleurGame(val modele: Client, val view: MainView) : EventHandler<ActionEvent> {
    override fun handle(event: ActionEvent) {
        val gameView = Game()
        view.setCenterView(gameView)

        //pour passer à la vue Listpartie via le bouton btnjoin
        gameView.btnjoin.setOnAction {
            val listVue = Listpartie()
            view.setCenterView(listVue)
        }
    }
}