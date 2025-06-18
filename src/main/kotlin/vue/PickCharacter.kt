package vue

import javafx.geometry.Insets
import javafx.geometry.Pos
import javafx.scene.control.Button
import javafx.scene.control.Label
import javafx.scene.layout.VBox
import modele.Match
import ui.createMainButton
import ui.createTextLabel

class PickCharacter() : VBox() {
    val btnOk : Button
    val label : Label
    val selectedChar : Boolean = false

    init{

        btnOk = createMainButton("Valider")
        label = createTextLabel("Choisissez votre personnage")
        this.children.addAll(label, btnOk)

        this.alignment = Pos.CENTER
        this.spacing = 40.0
        this.padding = Insets(15.0)

        btnOk.isDisable = true

        /*
        if (!match.isPlayerSelected()) {
            btnOk.isDisable = true  // désactive le bouton (grisé, non cliquable)
        }

         */

    }
    fun updateBtn(match : Match){
        if (!match.isPlayerSelected()) {
            btnOk.isDisable = true  // désactive le bouton (grisé, non cliquable)
        }else{
            btnOk.isDisable = false
        }
    }
}