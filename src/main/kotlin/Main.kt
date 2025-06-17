import Controleurs.ControleurBoutonBack
import Controleurs.ControleurBoutonLogin
import Controleurs.ControleurBoutonRefresh
import Controleurs.ControleurBoutonStartMatch
import Controleurs.ControleurBoutonRejoindrePartie
import info.but1.sae2025.QuiEstCeClient
import javafx.application.Application
import javafx.scene.Scene
import javafx.scene.text.Font
import javafx.stage.Stage
import modele.Client
import vue.Login
import vue.MainView
import vue.MatchList
import vue.MatchMaking


class Main : Application() {
    override fun start(stage: Stage) {

        // FONTS UPLOAD
        Font.loadFont(javaClass.getResource("/fonts/Fascinate-Regular.ttf")?.toExternalForm(), 80.0)
        Font.loadFont(javaClass.getResource("/fonts/FascinateInline-Regular.ttf")?.toExternalForm(), 80.0)
        Font.loadFont(javaClass.getResource("/fonts/Satisfy-Regular.ttf")?.toExternalForm(), 80.0)

        // MODEL & VIEW INIT
        val mainView = MainView()
        val client = Client(QuiEstCeClient("localhost", 8080), mainView)
        val welcome = Welcome()
        val login = Login()
        val matchMaking = MatchMaking()
        val matchList = MatchList(client.getMatchList())

        // WELCOME
        client.start(welcome,login, 0.5) // 0.5 de base __ 0.0 pour dev

        // LOGIN
        login.btnLogin.onAction = ControleurBoutonLogin(client, mainView, login, matchMaking)

        // MATCHMAKING
        matchMaking.btnNew.onAction = ControleurBoutonStartMatch(client, mainView, matchList, true)
        matchMaking.btnList.onAction = ControleurBoutonRejoindrePartie(client, mainView, matchList)
        matchMaking.btnReturn.onAction = ControleurBoutonBack(mainView, login)

        // MATCHLIST
        matchList.btnJoin.onAction = ControleurBoutonStartMatch(client, mainView, matchList, false)
        matchList.btnReturn.onAction = ControleurBoutonLogin(client, mainView, login, matchMaking)
        matchList.btnRefresh.onAction = ControleurBoutonRefresh(client, matchList)

        // SHOW()
        val scene = Scene(mainView, 1000.0, 600.0)
        scene.stylesheets.add(javaClass.getResource("/style.css")!!.toExternalForm())
        stage.scene = scene
        stage.title = "C KI LUI ?"
        stage.show()
    }
}
    fun main() {
        Application.launch(Main::class.java)
    }
