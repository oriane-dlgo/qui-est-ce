package ui

import javafx.scene.control.Label

fun createTitleLabel(text : String) : Label{
    val label = Label(text)
    label.styleClass.add("title-label")
    return label
}

fun createTextLabel(text : String) : Label{
    val label = Label(text)
    label.styleClass.add("text-label")
    return label
}