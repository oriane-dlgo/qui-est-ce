package Controleurs

import javafx.event.ActionEvent
import javafx.event.EventHandler
import modele.Client
import vue.MainView
import vue.Login

class ControleurBoutonJouer(val modele: Client, val view: MainView): EventHandler<ActionEvent> {


    override fun handle(event: ActionEvent) {

        val loginView = Login()
        view.setCenterView(loginView)
        loginView.btndia1.setOnAction(ControleurBoutonLogin(modele, view, loginView))

        println("Vous avez cliqué sur \"Jouer\" ")
    }

}