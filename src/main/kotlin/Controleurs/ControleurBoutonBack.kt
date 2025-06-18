package Controleurs

import javafx.event.ActionEvent
import javafx.event.EventHandler
import javafx.scene.layout.Pane
import vue.MainView

class ControleurBoutonBack(val mainView: MainView, val viewToGo: Pane) : EventHandler<ActionEvent> {
    override fun handle(event: ActionEvent) {
            mainView.center = viewToGo
    }
}