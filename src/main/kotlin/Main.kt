import Controleurs.ControleurBoutonLogin
import Controleurs.ControleurBoutonStartMatch
import Controleurs.ControleurBoutonRejoindrePartie
import info.but1.sae2025.QuiEstCeClient
import javafx.application.Application
import javafx.scene.Scene
import javafx.scene.text.Font
import javafx.stage.Stage
import modele.Client
import vue.GameBoard
import vue.Login
import vue.MainView
import vue.MatchList
import vue.MatchMaking


class Main : Application() {
    override fun start(stage: Stage) {

        Font.loadFont(javaClass.getResource("/fonts/Fascinate-Regular.ttf")?.toExternalForm(), 80.0)

        val mainView = MainView()
        val client = Client(server = QuiEstCeClient("localhost", 8080), mainView)

        val welcome = Welcom()
        val login = Login()
        val matchMaking = MatchMaking()

        val matchList = MatchList(client.getMatchList())


        // WELCOME
        client.showPopUp(welcome,login, 0.5)

        // LOGIN
        login.btnLogin.onAction = ControleurBoutonLogin(client, mainView, login, matchMaking)


        // MATCHMAKING
        matchMaking.btnNew.onAction = ControleurBoutonStartMatch(client, mainView, matchList, true)
        matchMaking.btnJoin.onAction = ControleurBoutonRejoindrePartie(client, mainView, matchList)
        //matchMaking.btnNew.onAction = GameClock(match, gameBoardView, mainView))

        // MATCHLIST
        matchList.joinBtn.onAction = ControleurBoutonStartMatch(client, mainView, matchList, false)
        matchList.retourBtn.onAction = ControleurBoutonLogin(client, mainView, login, matchMaking)

        // GAMEBOARD









        val scene = Scene(mainView, 1000.0, 600.0)
        stage.scene = scene
        stage.title = "C KI LUI ?"
        stage.show()
    }
}
    fun main() {
        Application.launch(Main::class.java)
    }
