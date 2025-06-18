package vue

import javafx.geometry.Insets
import javafx.geometry.Pos
import javafx.scene.control.Button
import javafx.scene.control.Label
import javafx.scene.layout.VBox
import modele.Match
import ui.createHomeLabel
import ui.createSecondButton
import ui.createTextLabel

class HideCharacter(val answer : String) : VBox() {

    val label1 : Label
    val label2 : Label
    var label3 : Label
    var label4 : Label
    val btnYes : Button
    val btnNo : Button
    val btnOk : Button
    val btnBox : VBox
    val box1 : VBox
    // val box2 : VBox


    init{
        label1 = createTextLabel("Réponse :$answer ")
        label1.isWrapText = true
        label1.padding = Insets(0.0,0.0,5.0,0.0)

        label2 = createTextLabel("Voulez vous caché \ndes personnages ?")
        label2.padding = Insets(0.0,0.0,9.0,0.0)

        label2.isWrapText = true

        btnYes = createSecondButton("Oui")
        btnNo = createSecondButton("Non")
        btnBox = VBox(10.0,btnYes,btnNo)
        btnBox.alignment = Pos.CENTER
        box1 = VBox(15.0,label1,label2,btnBox)
        box1.alignment = Pos.CENTER

        label3 = createTextLabel("Selectionnez sur la grille")
        label3.isWrapText = true
        btnOk = createSecondButton("Valider")

        label4 = createTextLabel("Voulez vous faire un guess ?")
        label4.style = "-fx-font-size: 16px"
        label4.isWrapText = true
        label4.padding = Insets(0.0, 0.0, 40.0, 0.0)


        /*box2 = VBox()
        box2.children.addAll(label4, btnBox)
        box2.alignment = Pos.CENTER*/
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
    fun updateBtn(match: Match){
        if (!match.isPlayerSelected()) {
            btnOk.isDisable = true  // désactive le bouton (grisé, non cliquable)
        }else{
            btnOk.isDisable = false
        }
    }
}