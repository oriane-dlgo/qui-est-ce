package ui

import javafx.scene.control.Label
import javafx.scene.control.TextField

/*
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
*/
fun createHomeTextField(text : String) : TextField{
    val textField = TextField(text)
    textField.styleClass.add("home-textfield")
    return textField
}