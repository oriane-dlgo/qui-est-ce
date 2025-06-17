package Controleurs

import info.but1.sae2025.exceptions.QuiEstCeException
import javafx.event.ActionEvent
import javafx.event.EventHandler
import javafx.scene.layout.Pane
import modele.Client
import vue.MainView

import vue.MatchMaking
import vue.Login

class ControleurBoutonBack(val mainView: MainView, val viewToGo: Pane) : EventHandler<ActionEvent> {
    override fun handle(event: ActionEvent) {
            mainView.center = viewToGo
    }
}