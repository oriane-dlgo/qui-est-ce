package Controleurs

import javafx.event.ActionEvent
import javafx.event.EventHandler
import modele.Client
import vue.EnterCode
import vue.MainView

class ControleurBoutonNouvellePartie(val client: Client, val view : MainView): EventHandler<ActionEvent> {

    override fun handle(event : ActionEvent){

        //val gameView = Game()
        //view.setCenterView(game)
        //gameView.btnvalid.setOnAction(ControleurBoutonValiderCode(client, view))


    }
}