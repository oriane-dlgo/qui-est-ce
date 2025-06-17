package Controleurs

import info.but1.sae2025.exceptions.QuiEstCeException
import javafx.event.ActionEvent
import javafx.event.EventHandler
import modele.Client
import vue.MainView

import vue.MatchMaking
import vue.Login

class ControleurBoutonLogin(val client: Client, val mainView: MainView, val login: Login, val matchMaking: MatchMaking) : EventHandler<ActionEvent> {
    override fun handle(event: ActionEvent) {
        val matchMakingView = MatchMaking()

        try {
            // Connexion joueur
            var player = client.playerLogin(login.textFieldLastName.text, login.textFieldName.text)

            // Changement de vue
            matchMaking.player = player
            mainView.center = matchMaking



        } catch (e: QuiEstCeException) {
            println(e)
        }
    }
}