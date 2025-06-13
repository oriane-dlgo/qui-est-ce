package Controleurs

import javafx.event.ActionEvent
import javafx.event.EventHandler
import modele.Client
import vue.MainView
import vue.Name
import vue.Game

class ControleurName(val modele: Client, val view: MainView): EventHandler<ActionEvent> {


    override fun handle(event: ActionEvent) {
        val nameView = Name()
        view.setCenterView(nameView)

        // pour aller sur la vue Game avec le bouton btndia1 (valider)
        nameView.btndia1.setOnAction(ControleurGame(modele, view))
    }

}