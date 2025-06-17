package ui

import javafx.scene.control.Button
import javafx.scene.image.Image
import javafx.scene.image.ImageView

fun createMainButton(text: String): Button {
    val button = Button(text)
    button.styleClass.add("main-button")
    button.maxHeight = 10.0
    return button
}

fun createSecondButton(text: String): Button {
    val button = Button(text)
    button.styleClass.add("second-button")
    button.maxHeight = 10.0
    return button
}


fun createSmallButton(text: String): Button {
    val button = Button(text)
    button.styleClass.add("small-button")
    return button
}

/*
fun createBackButton() : Button{
    val button = Button("<-")
    button.styleClass.add("back-button")
    return button
}
*/
fun createBackButton() : Button {

    val size = 50.0

    val imageNormal = ImageView(Image(object {}.javaClass.getResource("/assets/backBlue.png")!!.toExternalForm())).apply {
        this.fitWidth = size
        this.fitHeight = size
        this.isPreserveRatio = true
    }

    val imageHover = ImageView(Image(object {}.javaClass.getResource("/assets/backDark.png")!!.toExternalForm())).apply {
       this.fitWidth = size
        this.fitHeight = size
        this.isPreserveRatio = true
    }

    val btn = Button()
    btn.graphic = imageNormal
    btn.styleClass.add("back-button")

    btn.setOnMouseEntered {
        btn.graphic = imageHover
    }

    btn.setOnMouseExited {
        btn.graphic = imageNormal
    }
    return btn
}
