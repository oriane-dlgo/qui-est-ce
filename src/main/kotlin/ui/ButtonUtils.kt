package ui

import javafx.scene.control.Button

fun createMainButton(text: String): Button {
    val button = Button(text)
    button.styleClass.add("main-button")
    return button
}


fun createSmallButton(text: String): Button {
    val button = Button(text)
    button.styleClass.add("small-button")
    return button
}
