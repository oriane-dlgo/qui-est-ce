package Controleurs

import javafx.event.ActionEvent
import javafx.event.EventHandler
import modele.Client
import vue.EnterCode
import vue.MainView

class ControleurBoutonValiderCode(val modele : Client, val view : MainView) : EventHandler<ActionEvent> {

    override fun handle(event : ActionEvent){
        val codeview = EnterCode()

        view.setCenterView(codeview)

    }
}