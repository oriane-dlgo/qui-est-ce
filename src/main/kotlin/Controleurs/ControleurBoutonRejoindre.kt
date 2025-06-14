package Controleurs

import javafx.event.ActionEvent
import javafx.event.EventHandler
import modele.Client
import vue.Game
import vue.Listpartie
import vue.MainView

class ControleurBoutonRejoindre(val modele : Client, val view : MainView) : EventHandler<ActionEvent> {

    override fun handle(event: ActionEvent) {

        val listvue = Listpartie()


        println("CONTROLEUR Rejoindre")


        //pour passer à la vue Listpartie via le bouton btnjoin

        view.setCenterView(listvue)

    }
}