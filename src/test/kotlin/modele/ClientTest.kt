package modele

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class ClientTest {
    // PLAYER LOGIN
    @Test
    fun `playerLogin T1`() {
        // Entrée : lastName='DUPONT', name='jean'
        // Classe d’équivalence : Joueur local et serveur
        // Résultat attendu : Connexion réussie
    }

    @Test
    fun `playerLogin T2`() {
        // Entrée : lastName='MARTIN', name='luc'
        // Classe d’équivalence : Serveur uniquement
        // Résultat attendu : Exception levée
    }

    @Test
    fun `playerLogin T3`() {
        // Entrée : lastName='NOUVEAU', name='paul'
        // Classe d’équivalence : Aucun joueur
        // Résultat attendu : Création du joueur
    }

    @Test
    fun `playerLogin T4`() {
        // Entrée : lastName='', name='vide'
        // Classe d’équivalence : Chaîne vide
        // Résultat attendu : Erreur ou comportement inattendu
    }

    @Test
    fun `playerLogin T5`() {
        // Entrée : lastName='ÉTRANGE', name='öscar'
        // Classe d’équivalence : Caractères spéciaux
        // Résultat attendu : Connexion ou erreur
    }

    // MATCH JOIN
    @Test
    fun `matchJoin T1`() {
        // Entrée : matchId valide (ex: 3)
        // Classe d’équivalence : ID existant
        // Résultat attendu : Partie rejointe avec succès
    }

    @Test
    fun `matchJoin T2`() {
        // Entrée : matchId inexistant (ex: 999)
        // Classe d’équivalence : ID inexistant
        // Résultat attendu : Erreur serveur ou exception
    }

    @Test
    fun `matchJoin T3`() {
        // Entrée : matchId = -1
        // Classe d’équivalence : ID invalide
        // Résultat attendu : Erreur ou comportement indéfini
    }

    // UPDATE MATCH LIST
    @Test
    fun `updateMatchList T1`() {
        // Entrée : aucune
        // Classe d’équivalence : Connexion serveur valide
        // Résultat attendu : Nouvelle liste de parties retournée
    }

    @Test
    fun `updateMatchList T2`() {
        // Entrée : aucune
        // Classe d’équivalence : Connexion serveur rompue
        // Résultat attendu : Exception ou liste inchangée
    }
}