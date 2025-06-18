package vue

import javafx.event.ActionEvent
import javafx.event.EventHandler
import javafx.geometry.Insets
import javafx.geometry.Pos
import javafx.scene.control.Button
import javafx.scene.control.Label
import javafx.scene.control.ScrollPane
import javafx.scene.layout.BorderPane
import javafx.scene.layout.GridPane
import javafx.scene.layout.HBox
import javafx.scene.layout.StackPane
import javafx.scene.layout.VBox
import javafx.scene.text.Font
import javafx.scene.text.FontWeight
import modele.Client
import modele.Match
import ui.*

class MatchList(matchList: List<Int>) : StackPane() {
    val matchIdToRow = mutableMapOf<Int, Int>()
    var matchList: List<Int>
    var selectedLabel: Label?
    var selectedId: Int

    val backSquare: StackPane
    val content : VBox
    //val top: HBox
    val label: Label //vbox
    val btnJoin: Button

    val listContainer: VBox //vbox
    val gridlist: GridPane
    val scrollbar: ScrollPane

    //val containerRefreshBtn : StackPane
    val btnRefresh : Button

    //val containerReturnBtn : StackPane
    val btnReturn : Button

    init {

        this.matchList = matchList
        selectedLabel = null
        selectedId = -1
        //champID = String

        backSquare = StackPane()
        backSquare.maxWidth = 500.0
        backSquare.maxHeight = 500.0
        backSquare.prefWidth = 500.0
        backSquare.prefHeight = 500.0
        backSquare.style ="-fx-background-color: rgba(255, 255, 255, 0.9); -fx-background-radius: 10px; -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.4), 10, 0.2, 0, 4);"
        backSquare.padding = Insets(20.0)

        content = VBox().apply {
            alignment = Pos.CENTER
            spacing = 40.0
            padding = Insets(0.0, 0.0, 20.0, 0.0)
        }


        //top = HBox()
        label = createHomeLabel("Liste des parties").apply {
            style = """ -fx-font-size: 30px; """
        }

        btnJoin = createMainButton("Rejoindre")

        listContainer = VBox() //vbox

        gridlist = GridPane()
        gridlist.maxWidth = Double.MAX_VALUE  // Fait en sorte que le GridPane prenne toujours tout la largeur
        gridlist.padding = Insets(10.0)  // Bordure de 10 autour de ce qu'il y a dans le GridPane
        gridlist.style = "-fx-background-color: white; -fx-border-radius: 10px"
//      gridlist.font = Font.font("Courier New", FontWeight.BOLD, 35.0)// Met le fon en blanc

        //la boucle va remplir le gridpane containList avec la fonction du modele Client getMatchServerList()
        //le withIndex va permettre d'avoir l'index et le contenu à l'index indiqué
        updateMatchListGrid()

        scrollbar = ScrollPane()
        scrollbar.content = gridlist
        scrollbar.isFitToHeight = true
        scrollbar.isFitToWidth = true

        //containerReturnBtn = StackPane()
        btnReturn = createBackButton()
        //containerReturnBtn.children.add(btnReturn)

        //containerRefreshBtn = StackPane()
        btnRefresh = createRefreshButton()
        //containerRefreshBtn.children.add(btnRefresh)

        listContainer.children.add(scrollbar)

        content.children.addAll(label, listContainer, btnJoin)
        backSquare.children.addAll(content, btnReturn, btnRefresh)
        StackPane.setAlignment(btnReturn, Pos.TOP_LEFT)
        StackPane.setAlignment(btnRefresh, Pos.TOP_RIGHT)

        this.children.add(backSquare)
        this.padding = Insets(20.0)


        if (selectedLabel == null) {
            btnJoin.isDisable = true  // désactive le bouton (grisé, non cliquable)
        }
    }
    /*

    fun styleGridPane(label: Label) {          //fonction qui permet d'afficher une bordure une fois l'ID cliqué
        label.style = "-fx-padding: 3px"
        label.font = Font.font("Courier New", FontWeight.BOLD, 20.0)
        label.setOnMouseClicked {
            //selectedLabel?.style = ""   //label précédent sans style
            label.style = "-fx-border-color: #61888c; -fx-border-radius: 8px; -fx-padding: 5px " // Bordure
            //selectedLabel = label //actualise le label actuel en label précédent
            //selectedLabel = label.text // champID prend la valeur du label

            // Mise à jour du bouton dès que champID change
            btnJoin.isDisable = selectedLabel == -1

        }
*/
    fun getIdCreator(){

    }

    fun updateMatchListGrid(){
        gridlist.children.clear()
        for ((i, match) in matchList.asReversed().withIndex()) {
            val label = Label(match.toString())

            val label2 = createNomLabel("Partie n° ")

            this.styleGridPane(label)
            this.gridlist.add(label, 1, i)
            this.gridlist.add(label2, 0, i)


            matchIdToRow[match] = i
        }
    }

    fun styleGridPane(label: Label) {
        label.style = "-fx-padding: 5px;"
        label.font = Font.font("Courier New", FontWeight.BOLD, 20.0)

        label.setOnMouseClicked {
            // Enlève le style de l'ancien label sélectionné
            selectedLabel?.style = "-fx-padding: 5px; -fx-font-size: 20px;"

            // Applique le style au nouveau
            label.style = """
            -fx-border-color: #61888c;
            -fx-border-radius: 8px;
            -fx-padding: 5px;
            -fx-font-size: 20px;
        """.trimIndent()

            // Mets à jour la sélection
            selectedLabel = label
            selectedId = label.text.toInt()

            // Active le bouton Rejoindre
            btnJoin.isDisable = false

        }


        fun setOnRetourAction(handler: EventHandler<ActionEvent>) {
            btnReturn.onAction = handler
        }
    }
//    fun getNameCreatorMatch(nom : String, prenom : String){
//        nom.text = "Créé par le joueur $nom $prenom"
//    }
//fun desactiverLigne(matchId: Int) {
//    val rowIndex = matchIdToRow[matchId] ?: return
//    println("Désactivation ligne $rowIndex pour matchId = $matchId")
//
//    for (node in gridlist.children) {
//        val nodeRow = GridPane.getRowIndex(node) ?: 0
//        if (nodeRow == rowIndex) {
//            node.isDisable = true
//            node.style += " -fx-opacity: 0.5;"
//        }
//    }
//}



}