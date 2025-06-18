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

class Win(val round: Int) : VBox() {
    val labelV: Label
    val label: Label
    val label1: Label
    val img: Image
    val imageView: ImageView

    init {

        labelV = createFascinateLabel("Victoire ! ")
        labelV.padding = Insets(50.0, 0.0, 30.0, 0.0)
        label = createTextLabel("Félicitation, tu as réussi à deviner le personnage choisi par ton adversaire !")
        label1 = createTextLabel("Et ça en seulement $round tours.")

        img = Image(
            javaClass.getResource("/assets/celebration_1000dp_000B_FILL0_wght400_GRAD0_opsz48.png")!!.toExternalForm()
        )
        imageView = ImageView(img)
        imageView.fitWidth = 100.0
        imageView.fitHeight = 100.0

        this.children.addAll(imageView, labelV, label, label1)
        this.padding = Insets(20.0)
        this.alignment = Pos.CENTER

    }
}