package modele

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class MatchTest {

    // PICK CHARACTER
    @Test
    fun `pickCharacter T1`() {
        // Entrée : row=2, col=4
        // Classe d’équivalence : Coordonnées valides
        // Résultat attendu : Personnage sélectionné
    }

    @Test
    fun `pickCharacter T2`() {
        // Entrée : row=-1, col=2
        // Classe d’équivalence : Row hors grille
        // Résultat attendu : Erreur ou ignoré
    }

    @Test
    fun `pickCharacter T3`() {
        // Entrée : row=4, col=1
        // Classe d’équivalence : Row hors grille
        // Résultat attendu : Erreur ou ignoré
    }

    @Test
    fun `pickCharacter T4`() {
        // Entrée : row=1, col=6
        // Classe d’équivalence : Col hors grille
        // Résultat attendu : Erreur ou ignoré
    }

    // UPDATE KEY PASS
    @Test
    fun `updateKeyPass T1`() {
        // Entrée : number=4, erase=false
        // Classe d’équivalence : Ajout simple
        // Résultat attendu : 4 ajouté à keyPass
    }

    @Test
    fun `updateKeyPass T2`() {
        // Entrée : number=9, erase=true
        // Classe d’équivalence : Effacement partiel
        // Résultat attendu : Efface sauf le 1er et ajoute 9
    }

    @Test
    fun `updateKeyPass T3`() {
        // Entrée : number=-1, erase=false
        // Classe d’équivalence : Valeur négative
        // Résultat attendu : Ajoute ou erreur selon logique
    }

    // GET ROW COL
    @Test
    fun `getRowCol T1`() {
        // Entrée : caseId=1
        // Classe d’équivalence : caseId valide
        // Résultat attendu : (0, 0)
    }

    @Test
    fun `getRowCol T2`() {
        // Entrée : caseId=24
        // Classe d’équivalence : caseId valide
        // Résultat attendu : (3, 5)
    }

    @Test
    fun `getRowCol T3`() {
        // Entrée : caseId=0
        // Classe d’équivalence : caseId invalide
        // Résultat attendu : null
    }

    @Test
    fun `getRowCol T4`() {
        // Entrée : caseId=25
        // Classe d’équivalence : caseId invalide
        // Résultat attendu : null
    }

    // NEXT ROUND
    @Test
    fun `nextRound T1`() {
        // Entrée : Tour impair, joueur 1
        // Classe d’équivalence : Tour joueur actif
        // Résultat attendu : roundCounter +1, roundByPlayer = true
    }

    @Test
    fun `nextRound T2`() {
        // Entrée : Tour pair, joueur 2
        // Classe d’équivalence : Tour joueur actif
        // Résultat attendu : roundCounter +1, roundByPlayer = true
    }

    // PUT QUESTION
    @Test
    fun `putQuestion T1`() {
        // Entrée : question = 'a-t-il une barbe ?'
        // Classe d’équivalence : Question valide
        // Résultat attendu : Question envoyée au serveur
    }

    @Test
    fun `putQuestion T2`() {
        // Entrée : question vide
        // Classe d’équivalence : Entrée vide
        // Résultat attendu : Erreur ou refus d’envoi
    }

    // MAKE GUESS

    @Test
    fun `makeGuess T1`() {
        // Entrée : caseId = 5
        // Classe d’équivalence : caseId valide
        // Résultat attendu : Devine à (row, col) correspondant
    }

    @Test
    fun `makeGuess T2`() {
        // Entrée : caseId = 0
        // Classe d’équivalence : caseId invalide
        // Résultat attendu : Aucune action ou erreur
    }

    @Test
    fun `makeGuess T3`() {
        // Entrée : caseId = 25
        // Classe d’équivalence : caseId invalide
        // Résultat attendu : Aucune action ou erreur
    }

}