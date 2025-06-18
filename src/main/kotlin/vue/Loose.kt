package vue

import javafx.geometry.Insets
import javafx.geometry.Pos
import javafx.scene.control.Button
import javafx.scene.control.Label
import javafx.scene.image.Image
import javafx.scene.image.ImageView
import javafx.scene.layout.VBox
import javafx.scene.text.Font
import javafx.scene.text.FontWeight
import ui.createFascinateLabel
import ui.createSmallButton
import ui.createTextLabel
import ui.createTitleLabel

class Loose(val round : Int): VBox() {
    val labelD : Label
    val label : Label
    val label2 : Label
    var img : Image
    val imageView : ImageView

    init{

        labelD = createFascinateLabel("DEFAITE ! ")
        labelD.padding = Insets(50.0,0.0,30.0,0.0)
        label = createTextLabel("Ton adversaire a été plus rapide que toi... ")
        label2 = createTextLabel("La partie a durée $round tours.")

        img = Image(javaClass.getResource("/assets/sentiment_frustrated_1000dp_000B_FILL0_wght400_GRAD0_opsz48.png")!!.toExternalForm())
        imageView = ImageView(img)
        imageView.fitWidth = 100.0
        imageView.fitHeight = 100.0


        this.children.addAll(imageView,labelD, label,label2)
        this.padding = Insets(20.0)
        this.alignment = Pos.CENTER
    }
}