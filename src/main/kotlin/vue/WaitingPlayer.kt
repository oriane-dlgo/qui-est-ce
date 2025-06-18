package vue

import javafx.animation.RotateTransition
import javafx.geometry.Insets
import javafx.geometry.Pos
import javafx.scene.control.Button
import javafx.scene.control.Label
import javafx.scene.image.Image
import javafx.scene.image.ImageView
import javafx.scene.layout.VBox
import javafx.scene.text.Font
import javafx.scene.text.FontWeight
import javafx.util.Duration
import ui.createTextLabel

class WaitingPlayer : VBox() {

    val label : Label
    val image : Image
    val imageView : ImageView

    init{


        this.label = createTextLabel("Waiting opponent")
        image = Image(javaClass.getResource("/assets/progress_activity_1000dp_61888C_FILL0_wght400_GRAD0_opsz48.png")!!.toExternalForm())
        imageView = ImageView(image)
        imageView.fitWidth = 50.0
        imageView.isPreserveRatio = true

        val rotate = RotateTransition(Duration.seconds(2.0), imageView)
        rotate.byAngle = 360.0
        rotate.cycleCount = RotateTransition.INDEFINITE
        rotate.isAutoReverse = false
        rotate.play()
        this.children.addAll(imageView, label)
        label.isWrapText = true


        this.alignment = Pos.CENTER
        this.padding = Insets(10.0)

    }



    fun setMessage(message: String){
        label.text = message
    }
}