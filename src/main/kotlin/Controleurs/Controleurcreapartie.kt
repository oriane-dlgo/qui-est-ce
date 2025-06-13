package Controleurs

import javafx.event.ActionEvent
import javafx.event.EventHandler
import modele.Client
import vue.MainView
import vue.listpartie

import vue.partie

class Controleurcreapartie(val modele : Client, val view : MainView) : EventHandler<ActionEvent> {

    override fun handle(event: ActionEvent){
        val newVue = partie()
        view.setCenterView(newVue)
        newVue.btnnew.setOnAction {
            println("Créer une nouvelle partie")

        }
        val newnewVue = listpartie()
        newVue.btnjoin.setOnAction {
        println("Rejoindre une partie")
        view.setCenterView2(newnewVue)

        }


    }

}