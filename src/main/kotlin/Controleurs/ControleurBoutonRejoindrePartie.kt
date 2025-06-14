package Controleurs

import javafx.event.ActionEvent
import javafx.event.EventHandler
import javafx.scene.control.Label
import modele.Client
import vue.MainView
import vue.MatchList

class ControleurBoutonRejoindrePartie(val modele : Client, val view : MainView) : EventHandler<ActionEvent> {

    // Cliquer sur ce bouton ne fait qu'afficher la vue ListPartie
    override fun handle(event: ActionEvent) {

        val matchView = MatchList()
        view.setCenterView(matchView)

         // Une liste de chaînes par exemple

        matchView.validateBtn.setOnAction(ControleurBoutonValiderPartie(modele, view))

        println("CONTROLEUR REJOINDRE")
        //la boucle va remplir le gridpane containList avec la fonction du modele Client getMatchServerList()
        //le withIndex va permettre d'avoir l'index et le contenu à l'index indiqué
        val matchList = modele.getMatchServerList()
        for ((i, match) in matchList.withIndex()) {
            val label = Label(match.toString())
            matchView.containList.add(label, 0, i)
        }
    }
}