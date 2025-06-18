package Controleurs

import info.but1.sae2025.exceptions.QuiEstCeException
import javafx.event.ActionEvent
import javafx.event.EventHandler
import javafx.scene.control.Alert
import javafx.scene.control.Alert.AlertType
import javafx.scene.control.ButtonType
import javafx.scene.image.Image
import javafx.scene.image.ImageView
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
                dialog.title = "Attention"
                dialog.headerText = ""
                dialog.contentText = "Cette partie à déjà ses deux joueurs, \n essaies d'en rejoindre une autre"
                val image = javaClass.getResource("/assets/error_80dp_61888C_FILL0_wght400_GRAD0_opsz48.png")
                    if (image != null) {
                    val imagee = Image(image.toExternalForm())
                    val imageView = ImageView(imagee)
                    imageView.fitHeight = 80.0
                    imageView.fitWidth = 80.0
                    dialog.graphic = imageView
                }


                val css = javaClass.getResource("/style.css")
                if (css != null) {
                    dialog.dialogPane.stylesheets.add(css.toExternalForm())
                }
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