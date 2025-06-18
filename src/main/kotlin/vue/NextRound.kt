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

class NextRound(val round : Int): VBox() {
    val label : Label

    init{

        label = createFascinateLabel("Tour n° $round")
        label.padding = Insets(50.0,0.0,30.0,0.0)

        this.children.addAll(label)
        this.padding = Insets(20.0)
        this.alignment = Pos.CENTER
    }
}