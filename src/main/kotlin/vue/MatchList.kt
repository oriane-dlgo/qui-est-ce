package vue

import javafx.geometry.Insets
import javafx.scene.control.Button
import javafx.scene.control.Label
import javafx.scene.layout.BorderPane
import javafx.scene.layout.GridPane
import javafx.scene.text.Font
import javafx.scene.text.FontWeight

class MatchList : BorderPane() {


    val listLabel : Label //vbox
    val containList : GridPane //vbox
    val joinBtn : Button
    var champID : String
    var selectedLabel : Label?
    init{


        listLabel = Label("Liste des parties disponibles :")
        listLabel.font = Font.font("Courier New", FontWeight.BOLD, 20.0)  // Change la police d'écriture et la taille
        listLabel.style = "-fx-text-fill: white"   // Met en blanc


        containList = GridPane()
        containList.maxWidth = Double.MAX_VALUE  // Fait en sorte que le GridPane prenne toujours tout la largeur
        containList.padding = Insets(10.0)  // Bordure de 10 autour de ce qu'il y a dans le GridPane
        containList.style = "-fx-background-color: white"  // Met le fon en blanc

        joinBtn = Button("Rejoindre")
        joinBtn.style = "-fx-background-color: #61888c; -fx-text-fill: white"      // Couleur du fond et de l'écriture
        joinBtn.setOnMouseEntered {
            joinBtn.style = "-fx-background-color: #4e6b6e; -fx-text-fill: black;" // Couleur du fond et de l'écriture lorsque la souris passe dessus
        }
        joinBtn.setOnMouseExited {
            joinBtn.style = "-fx-background-color: #61888c; -fx-text-fill: white;" // Couleur du fond et de l'écriture lorsque la souris n'est plus dessus
        }
        joinBtn.font = Font.font("Courier New", FontWeight.BOLD, 20.0) // Change la police et la taille


        champID = ""  // Variable qui va servir à stocker l'ID de la partie sélectionnée

        selectedLabel = null  //  Variable qui va permettre de stocker le label sélectionné précédent

        this.top = listLabel
        this.center = containList
        this.bottom = joinBtn
        this.padding = Insets(20.0)
        BorderPane.setMargin(listLabel, Insets(10.0))
        BorderPane.setMargin(containList, Insets(10.0))
        BorderPane.setMargin(joinBtn, Insets(10.0))


        if (champID.isEmpty()) {
            joinBtn.isDisable = true  // désactive le bouton (grisé, non cliquable)
        }
    }

    fun styleGridPane(label: Label) {          //fonction qui permet d'afficher une bordure une fois l'ID cliqué
        label.style = "-fx-padding: 3px"
        label.setOnMouseClicked {
            selectedLabel?.style = ""   //label précédent sans style
            label.style = "-fx-border-color: #61888c; -fx-border-radius: 5px; -fx-padding: 5px " // Bordure
            selectedLabel = label //actualise le label actuel en label précédent
            champID = label.text // champID prend la valeur du label

            // Mise à jour du bouton dès que champID change
            joinBtn.isDisable = champID.isEmpty()

        }

    }


}


