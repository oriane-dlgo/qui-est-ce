package vue

import javafx.geometry.Insets
import javafx.geometry.Pos
import javafx.scene.control.Button
import javafx.scene.control.Label
import javafx.scene.control.TextField
import javafx.scene.image.Image
import javafx.scene.image.ImageView
import javafx.scene.layout.StackPane
import javafx.scene.layout.VBox
import javafx.scene.text.Font
import javafx.scene.text.FontWeight
import ui.createMainButton
import ui.createSmallButton
import ui.createTitleLabel

class Login : StackPane() {

    val backSquare : VBox
    val image: ImageView
    val boxTextEntries : VBox
    val boxLastName : VBox
    val labelLastName : Label
    val textFieldLastName : TextField
    val boxName : VBox
    val labelName : Label
    val textFieldName : TextField
    val btnLogin : Button //hbox

    init{

        backSquare = VBox()
        backSquare.maxWidth = 300.0
        backSquare.maxHeight = 400.0
        backSquare.style = "-fx-background-color: rgba(255, 255, 255, 0.9); -fx-background-radius: 10px; -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.4), 10, 0.2, 0, 4);"
        backSquare.alignment = Pos.CENTER //centre la VBox qui contient "Entrer votre nom et prénom :"
        backSquare.padding = Insets(60.0)
        backSquare.spacing = 40.0

        image = ImageView(Image("/assets/loginDarkBlue.png"))
        image.fitWidth = 100.0 // adapte la taille si tu veux
        image.isPreserveRatio = true

        boxTextEntries = VBox()

        boxLastName = VBox()

        labelLastName = createTitleLabel("Nom :")

        textFieldLastName = TextField("Chirrac")

        boxName = VBox()

        labelName = Label("Prenom :")

        textFieldName = TextField("Jacques")

        btnLogin = createMainButton("Jouer")


        boxLastName.children.addAll(labelLastName, textFieldLastName)
        boxName.children.addAll(labelName, textFieldName)

        boxTextEntries.children.addAll(boxLastName, boxName)

        backSquare.children.addAll(image, boxTextEntries, btnLogin)

        children.add(backSquare)

    }

}