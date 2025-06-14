package Controleurs

import javafx.event.ActionEvent
import javafx.event.EventHandler
import modele.Client
import vue.MainView
import vue.Listpartie

class `Controleurlistpartie(peutetreàsupprimer)`(val modele : Client, val view: MainView) : EventHandler<ActionEvent> {
    override fun handle(event : ActionEvent){
        val newVue = Listpartie()
        view.setCenterView(newVue)


        println("CONTROLEUR ListPartie")
    }
}














