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



class MainView : BorderPane(){
    val title : Label //root.top
    val btn1 : Button //center



    init{
        title = Label("C KI LUI")
        // Les deux lignes permettent de placer le titre où on veut
        BorderPane.setAlignment(title, Pos.BOTTOM_CENTER)
        BorderPane.setMargin(title, Insets(50.0))
        title.font= Font.font("Courier New", FontWeight.BOLD,60.0) // Change la police d'écriture et la taille
        title.style = "-fx-text-fill: white"  // Met ala couleur en blanc


        btn1 = Button("JOUER")
        btn1.style = "-fx-background-color: #61888c; -fx-text-fill: white" // Met la couleur du fond et de l'écriture
        btn1.setOnMouseEntered {
            btn1.style = "-fx-background-color: #4e6b6e; -fx-text-fill: black;"  // Couleur du fond et de l'écriture lorsque la souris passe dessus
        }

        btn1.setOnMouseExited {
            btn1.style = "-fx-background-color: #61888c; -fx-text-fill: white;"  // Couleur du fond et de l'écriture lorsque la souris n'est plus dessus
        }
        btn1.font = Font.font("Courier New", FontWeight.BOLD, 50.0)  // Change la police d'écriture et la taille
        BorderPane.setAlignment(btn1, Pos.CENTER)
        BorderPane.setMargin(btn1, Insets(30.0))   // Place et modifie la taille du bouton

        this.top = title
        this.center = btn1
        this.style = "-fx-background-color: #78a9af;" // couleur de fond de tout le jeu
    }






    fun setCenterView(newVue: javafx.scene.Node) {
        this.center = newVue
        this.top = null
    }


    fun changeCenterView(bouton: Button, action: EventHandler<ActionEvent>){
        bouton.onAction = action
    }


}