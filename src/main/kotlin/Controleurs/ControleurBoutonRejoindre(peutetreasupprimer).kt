package Controleurs

import javafx.event.ActionEvent
import javafx.event.EventHandler
import modele.Client
import vue.Listpartie
import vue.MainView

class `ControleurBoutonRejoindre(peutetreasupprimer)`(val modele : Client, val view : MainView) : EventHandler<ActionEvent> {

    override fun handle(event: ActionEvent) {
        val newvue = Listpartie()
        view.setCenterView(newvue)
    }
}