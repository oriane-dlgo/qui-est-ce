package vue

import javafx.animation.KeyFrame
import javafx.animation.Timeline
import javafx.event.EventHandler
import javafx.scene.control.Button
import javafx.scene.layout.VBox
import javafx.util.Duration

class TestPopUp(): VBox() {
    //val label : Label
    val btn : Button

    init{

        btn = Button("TEST")
        this.children.addAll(btn)
    }
}

