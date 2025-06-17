import javafx.geometry.Pos
import javafx.scene.control.Label
import javafx.scene.layout.Priority
import javafx.scene.layout.VBox
import javafx.scene.text.Font
import javafx.scene.text.FontPosture
import javafx.scene.text.FontWeight

class Welcome : VBox() {
    val label: Label

    init {
        // Mise en forme générale
        prefHeight = 600.0
        spacing = 20.0
        alignment = Pos.TOP_CENTER
        style = """
            -fx-background-color: #78a9af;
            -fx-padding: 100 20 20 20;
        """.trimIndent()

        // Création du label
        label = Label("C KI LUI ?")
        label.font = Font.font("Fascinate", FontWeight.EXTRA_BOLD, FontPosture.REGULAR, 150.0)
        label.style = """
            -fx-text-fill: rgba(255,255,255,0.9);
            -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.4), 10, 0.2, 0, 4);
        """.trimIndent()

        // Ajout à la vue
        children.add(label)
        VBox.setVgrow(label, Priority.NEVER)
    }
}
