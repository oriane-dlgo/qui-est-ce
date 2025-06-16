import Controleurs.ControleurBoutonJouer
import info.but1.sae2025.QuiEstCeClient
import javafx.animation.Animation
import javafx.animation.KeyFrame
import javafx.animation.Timeline
import javafx.application.Application
import javafx.scene.Scene
import javafx.stage.Stage
import javafx.util.Duration
import modele.Client
import vue.MainView

class Main : Application() {
    override fun start(stage: Stage) {
        val vue = MainView()
        val modele = Client(server = QuiEstCeClient("localhost", 8080))

        vue.changeCenterView(vue.btn1, ControleurBoutonJouer(modele, vue)) //on change le center du mainview en Login()

        val scene = Scene(vue, 1000.0, 600.0)
        stage.scene = scene
        stage.title = "C KI LUI ?"
        stage.show()
    }
}
    fun main() {
        Application.launch(Main::class.java)
    }
