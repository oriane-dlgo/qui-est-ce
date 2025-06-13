package Controleurs

import javafx.event.ActionEvent
import javafx.event.EventHandler
import modele.Client
import vue.MainView
import vue.listpartie

class Controleurlistpartie(val modele : Client, val view: MainView) : EventHandler<ActionEvent> {
    override fun handle(event : ActionEvent){
        val newVue = listpartie()
        view.setCenterView(newVue)
    }
}














