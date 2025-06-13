package vue

import javafx.event.ActionEvent
import javafx.event.EventHandler
import javafx.scene.control.Button
import javafx.scene.control.Label
import javafx.scene.layout.BorderPane

class MainView : BorderPane(){
    val title : Label //root.top
    val btn1 : Button //center
    val btndia1 : Button //hbox


    init{
        title = Label("C KI LUI")
        btn1 = Button("JOUER")
        this.top = title
        this.center = btn1
        btndia1 = Button("Valider")


    }

    fun setCenterView(newVue: javafx.scene.Node) {
        this.center = newVue
        this.bottom = btndia1
    }

    fun setCenterView2(newVue: javafx.scene.Node) {
        this.center = newVue
        this.bottom = null
    }

    fun creaprofil(bouton: Button, action: EventHandler<ActionEvent>){
        bouton.onAction = action

  }




}