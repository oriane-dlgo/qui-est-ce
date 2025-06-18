package Controleurs

import javafx.event.ActionEvent
import javafx.event.EventHandler
import modele.Match


class ControleurBoutonHideYes(val match: Match, val multipleSel : Boolean) : EventHandler<ActionEvent> {
    override fun handle(p0: ActionEvent?) {

        if(multipleSel){
            match.updateKeySel(2)
        }else{
            match.updateKeySel(1)
        }
        match.updateKeyHide()
        match.updateKeyPass(0, true)
    }
}




