package Controleurs

import javafx.event.ActionEvent
import javafx.event.EventHandler
import modele.Client
import vue.MainView
import vue.creaprofil

class ControleurName(val modele: Client, val view: MainView): EventHandler<ActionEvent> {

    override fun handle(event: ActionEvent){
        val newVue = creaprofil()
        view.setCenterView(newVue)
    }

}