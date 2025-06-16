package Controleurs

import javafx.event.ActionEvent
import javafx.event.EventHandler
import javafx.scene.control.Label
import modele.Client
import vue.MainView
import vue.MatchList

class ControleurBoutonRejoindrePartie(val client : Client, val view : MainView) : EventHandler<ActionEvent> {

    // Cliquer sur ce bouton affiche la vue ListPartie
    override fun handle(event: ActionEvent) {

        val matchListView = MatchList()
        view.setCenterView(matchListView)

        matchListView.joinBtn.setOnAction(ControleurBoutonValiderPartie(client, view, matchListView))
        client.getMatchList()
        view.center = matchListView
        matchListView.setOnRetourAction(ControleurBoutonRetour(view, client))


        //la boucle va remplir le gridpane containList avec la fonction du modele Client getMatchServerList()
        //le withIndex va permettre d'avoir l'index et le contenu à l'index indiqué
        client.updateMatchList()
        val matchList = client.getMatchList()
        for ((i, match) in matchList.withIndex()) {
            val label = Label(match.toString())
            matchListView.styleGridPane(label)
            matchListView.containList.add(label, 0, i)
        }
    }
}