package vue

import javafx.event.ActionEvent
import javafx.event.EventHandler
import javafx.geometry.Insets
import javafx.geometry.Pos
import javafx.scene.control.Button
import javafx.scene.control.Label
import javafx.scene.layout.BorderPane
import javafx.scene.text.Font
import javafx.scene.text.FontWeight
import javax.swing.border.Border


class MainView : BorderPane(){
    val title : Label //root.top
    val btn1 : Button //center



    init{
        title = Label("C KI LUI")
        BorderPane.setAlignment(title, Pos.TOP_CENTER)
        BorderPane.setMargin(title, Insets(50.0))
        title.font= Font.font("Courier New", FontWeight.BOLD,28.0)
        title.style = "-fx-text-fill: white"


        btn1 = Button("JOUER")
        btn1.style = "-fx-background-color: #61888c; -fx-text-fill: white"
        btn1.setOnMouseEntered {
            btn1.style = "-fx-background-color: #4e6b6e; -fx-text-fill: black;"
        }

        btn1.setOnMouseExited {
            btn1.style = "-fx-background-color: #61888c; -fx-text-fill: white;"
        }
        btn1.font = Font.font("Courier New", FontWeight.BOLD, 30.0)
        BorderPane.setMargin(btn1, Insets(30.0))

        this.top = title
        this.center = btn1
        this.style = "-fx-background-color: #78a9af;"
    }






    fun setCenterView(newVue: javafx.scene.Node) {
        this.center = newVue
        this.top = null
    }


    fun changeCenterView(bouton: Button, action: EventHandler<ActionEvent>){
        bouton.onAction = action
    }


}