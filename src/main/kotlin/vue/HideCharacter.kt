package vue

import javafx.geometry.Insets
import javafx.geometry.Pos
import javafx.scene.control.Button
import javafx.scene.control.Label
import javafx.scene.layout.VBox
import ui.createHomeLabel
import ui.createSecondButton

class HideCharacter(val answer : String) : VBox() {

    val label1 : Label
    val label2 : Label
    var label3 : Label
    var label4 : Label
    val btnYes : Button
    val btnNo : Button
    val btnOk : Button


    init{
        label1 = createHomeLabel("La réponse à \nla question est :\n$answer ")

        label2 = createHomeLabel("Voulez vous caché\ndes personnages ?")
        btnYes = createSecondButton("Oui")
        btnNo = createSecondButton("Non")

        label3 = createHomeLabel("Selectionnez sur la grille")
        btnOk = createSecondButton("Valider")

        label4 = createHomeLabel("Voulez vous faire un guess ?")
        // btnYes
        // btnNo

        // label3
        // btnOk
        this.alignment = Pos.CENTER
        this.spacing = 10.0
        this.padding = Insets(15.0)

    }

    fun switchHideView(key : Int){
        this.children.clear()
       when(key){
           0 -> this.children.addAll(label1,label2, btnYes, btnNo)
           1 -> this.children.addAll(label3, btnOk)
           2 -> this.children.addAll(label4, btnYes, btnNo)
           3 -> this.children.addAll(label3, btnOk)
       }
    }
}