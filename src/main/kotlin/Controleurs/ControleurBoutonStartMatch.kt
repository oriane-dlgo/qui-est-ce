package Controleurs

import info.but1.sae2025.exceptions.QuiEstCeException
import javafx.event.ActionEvent
import javafx.event.EventHandler
import javafx.scene.control.Alert
import javafx.scene.control.Alert.AlertType
import javafx.scene.control.ButtonType
import modele.Client
import modele.Match
import vue.GameBoard
import vue.MainView
import vue.MatchList
import vue.MatchMaking

class ControleurBoutonStartMatch(
    val client: Client,
    val mainView: MainView,
    val matchList: MatchList,
    val create: Boolean
) : EventHandler<ActionEvent> {

    override fun handle(event: ActionEvent) {

        val match: Match

        if (create) {
            match = client.matchCreate()
        } else {
            try{
            match = client.matchJoin(matchList.selectedId)
                val gameBoard = GameBoard(client, mainView, match)
                mainView.center = gameBoard

            }
            catch(e : QuiEstCeException){
                val dialog = Alert(AlertType.INFORMATION)
                dialog.title = "Cette partie n'est plus disponible"
                dialog.contentText = "Cette partie à déjà ses deux joueurs, " +
                        "essaies d'en rejoindre une autre"
                dialog.showAndWait()

            }

        }


//        if (match.getMatchState().toString() == "TERMINEE") {
//            println("Le match ${match.getId()} est terminé.")
//            matchList.desactiverLigne(match.getId())
//        }

//        if (match.getMatchState().toString() != "CREEE"){
//            val dialog = Alert(AlertType.INFORMATION)
//            dialog.title = "Cette partie n'est plus disponible"
//            dialog.contentText = "Cette partie à déjà ses deux joueurs, essaies d'en rejoindre une autre"
//            val option = dialog.showAndWait()
//            option.get() == ButtonType.OK
//        }


    }
}