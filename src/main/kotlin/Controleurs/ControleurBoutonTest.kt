package Controleurs

import javafx.animation.Animation
import javafx.animation.KeyFrame
import javafx.animation.Timeline
import javafx.event.ActionEvent
import javafx.event.EventHandler
import javafx.util.Duration
import modele.Client
import modele.Match
import vue.GameBoard
import vue.HideCharacter
import vue.MainView
import vue.Login
import vue.Win


class ControleurBoutonTest(val mainView: MainView, val gameBoard: GameBoard) : EventHandler<ActionEvent> {
    override fun handle(p0: ActionEvent?) {
        showPopUp(mainView, gameBoard)
    }



}

// DEJA DANS CLIENT, A SUPPRIMER
fun showPopUp(mainView: MainView, gameBoard: GameBoard) {
    // Affiche le Win à la place du gameBoard
    mainView.center = Win(5)

    // Attend 3 secondes puis remet le gameBoard
    val timeline = Timeline(
        KeyFrame(Duration.seconds(3.0), EventHandler {
            mainView.center = gameBoard
        })
    )
    timeline.play()
}


