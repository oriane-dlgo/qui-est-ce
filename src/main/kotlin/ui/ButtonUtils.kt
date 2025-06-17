package ui

import javafx.scene.control.Button

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
