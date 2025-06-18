package Controleurs

import info.but1.sae2025.exceptions.QuiEstCeException
import javafx.event.ActionEvent
import javafx.event.EventHandler
import javafx.scene.control.Alert
import javafx.scene.control.Alert.AlertType
import javafx.scene.image.Image
import javafx.scene.image.ImageView
import modele.Client
import vue.MainView

import vue.MatchMaking
import vue.Login

class ControleurBoutonLogin(
    val client: Client,
    val mainView: MainView,
    val login: Login,
    val matchMaking: MatchMaking
) : EventHandler<ActionEvent> {
    override fun handle(event: ActionEvent) {
        try {
            // Connexion joueur
            var player = client.playerLogin(login.textFieldLastName.text, login.textFieldName.text)

            // Mise à jour du message de bienvenue
            matchMaking.setBienvenueMessage(login.textFieldName.text)

            // Changement de vue
            matchMaking.player = player
            mainView.center = matchMaking

        } catch (e: QuiEstCeException) {
            val dialog = Alert(AlertType.INFORMATION)
            dialog.title = "Attention"
            dialog.headerText = ""
            dialog.contentText = "Ce profil a déjà été créé sur une autre machine"
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
}
