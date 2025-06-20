import info.but1.sae2025.QuiEstCeClient
import info.but1.sae2025.data.ETAPE
import info.but1.sae2025.exceptions.QuiEstCeException
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.nio.channels.UnresolvedAddressException

class QuiEstCeClientTest {
 companion object {
  lateinit var client: QuiEstCeClient

  @BeforeAll
  @JvmStatic
  fun setup() {
   client = QuiEstCeClient("localhost", 8080)
  }
 }

 //méthode requeteCreationJoueur
 @Test
 fun requeteCreationJoueur_retourneUnIdentifiantValide() {
  val ident = client.requeteCreationJoueur("Piastri", "Pierre")
  assertNotNull(ident)
  assertTrue(ident.id > 0)
  assertTrue(ident.cle.isNotEmpty())
 }

 @Test
 fun `requeteCreationJoueur avec nom vide échoue`() {
  assertThrows<IllegalArgumentException> {
   client.requeteCreationJoueur("", "Charles")
  }
 }


 @Test
 fun `requeteCreationJoueur avec prénom vide doit échouer`() {
  assertThrows<IllegalArgumentException> {
   client.requeteCreationJoueur("Leclerc", "")
  }
 }


 @Test
 fun `requeteCreationJoueur avec caractères spéciaux doit lever une exception`() {
  assertThrows<QuiEstCeException> {
   client.requeteCreationJoueur("Dùpond", "Jëan@")
  }
 }


 @Test
 fun requeteCreationJoueur_nomLong() {
  val nomLong = "A".repeat(200)
  assertThrows<QuiEstCeException> {
   client.requeteCreationJoueur(nomLong, "Jean")
  }
 }


 //méthode requeteEssai

 @Test
 fun testRequeteEssai_retourneNonNull() {
  val client = QuiEstCeClient("localhost", 8080)
  assertNotNull(client.requeteEssai())
 }

 @Test
 fun testRequeteEssai_retourneStringNonVide() {
  val client = QuiEstCeClient("localhost", 8080)
  assertTrue(client.requeteEssai().isNotEmpty())
 }

 @Test
 fun testRequeteEssai_appelsMultiples() {
  val client = QuiEstCeClient("localhost", 8080)
  repeat(5) {
   assertNotNull(client.requeteEssai())
  }
 }

 //méthode requeteJoueurs

 @Test
 fun testRequeteJoueurs_listeNonNulle() {
  val client = QuiEstCeClient("localhost", 8080)
  assertNotNull(client.requeteJoueurs())
 }

 @Test
 fun testRequeteJoueurs_retourneListeSansErreur() {
  val client = QuiEstCeClient("localhost", 8080)
  val joueurs = client.requeteJoueurs()
  assertNotNull(joueurs)
  assertDoesNotThrow { joueurs.size } // vérifie qu’on peut l’utiliser sans crash
 }


 @Test
 fun testRequeteJoueurs_listeContientIdsPositifs() {
  val client = QuiEstCeClient("localhost", 8080)
  val joueurs = client.requeteJoueurs()
  assertTrue(joueurs.all { it > 0 })
 }

 @Test
 fun testRequeteJoueurs_listeNonVideApresCreation() {
  val client = QuiEstCeClient("localhost", 8080)
  client.requeteCreationJoueur("Chirac", "Jacques")
  val joueurs = client.requeteJoueurs()
  assertTrue(joueurs.isNotEmpty())
 }

 @Test
 fun testRequeteJoueurs_listeCoherenteApresCreation() {
  val client = QuiEstCeClient("localhost", 8080)
  val id = client.requeteCreationJoueur("Hamilton", "Lewis").id
  val joueurs = client.requeteJoueurs()
  assertTrue(joueurs.contains(id))
 }

//méthode requeteJoueur(IdJoueur : Int)

 @Test
 fun testRequeteJoueur_retourneJoueurValide() {
  val client = QuiEstCeClient("localhost", 8080)
  val id = client.requeteCreationJoueur("Robert", "Lewis").id
  val joueur = client.requeteJoueur(id)
  assertEquals("Robert", joueur.nom)
  assertEquals("Lewis", joueur.prenom)
 }

 @Test
 fun testRequeteJoueur_idInexistant() {
  val client = QuiEstCeClient("localhost", 8080)
  assertThrows<IllegalArgumentException> {
   client.requeteJoueur(-1)
  }
 }

 @Test
 fun testRequeteJoueur_idNegatif() {
  val client = QuiEstCeClient("localhost", 8080)
  assertThrows<IllegalArgumentException> {
   client.requeteJoueur(-5)
  }
 }

 @Test
 fun testRequeteJoueur_champsNonNull() {
  val client = QuiEstCeClient("localhost", 8080)
  val id = client.requeteCreationJoueur("Montagné", "Gilbert").id
  val joueur = client.requeteJoueur(id)
  assertNotNull(joueur.nom)
  assertNotNull(joueur.prenom)
 }

 @Test
 fun testRequeteJoueur_consistanceAvecCreation() {
  val client = QuiEstCeClient("localhost", 8080)
  val id = client.requeteCreationJoueur("Jugnot", "Gerard").id
  val joueur = client.requeteJoueur(id)
  assertEquals("Jugnot", joueur.nom)
  assertEquals("Gerard", joueur.prenom)
 }

//méthode requeteCreationPartie

 @Test
 fun testCreationPartie_valide() {
  val client = QuiEstCeClient("localhost", 8080)
  val joueur = client.requeteCreationJoueur("Ronaldo", "Christiano")
  val idPartie = client.requeteCreationPartie(joueur.id, joueur.cle)
  assertTrue(idPartie > 0)
 }

 @Test
 fun testCreationPartie_joueurInexistant() {
  val client = QuiEstCeClient("localhost", 8080)
  assertThrows<IllegalArgumentException> {
   client.requeteCreationPartie(9999, "fakeKey")
  }
 }

 @Test
 fun testCreationPartie_cleInvalide() {
  val client = QuiEstCeClient("localhost", 8080)
  val joueur = client.requeteCreationJoueur("Messi", "Lionel")
  assertThrows<IllegalArgumentException> {
   client.requeteCreationPartie(joueur.id, "")
  }
 }

 @Test
 fun testCreationPartie_retourneIdPositif() {
  val client = QuiEstCeClient("localhost", 8080)
  val joueur = client.requeteCreationJoueur("Civil", "François")
  val idPartie = client.requeteCreationPartie(joueur.id, joueur.cle)
  assertTrue(idPartie > 0)
 }

 @Test
 fun testCreationPartie_doubleCreation() {
  val client = QuiEstCeClient("localhost", 8080)
  val joueur = client.requeteCreationJoueur("Ninney", "Pierre")
  val idPartie1 = client.requeteCreationPartie(joueur.id, joueur.cle)
  val idPartie2 = client.requeteCreationPartie(joueur.id, joueur.cle)
  assertNotEquals(idPartie1, idPartie2)
 }

 //méthode requeteEtatPartie(idPartie: Int): EtatPartie
 @Test
 fun testRequeteEtatPartie_retourneEtatCorrect() {
  val client = QuiEstCeClient("localhost", 8080)
  val joueur = client.requeteCreationJoueur("Macron", "Emmanuel")
  val idPartie = client.requeteCreationPartie(joueur.id, joueur.cle)
  val etat = client.requeteEtatPartie(idPartie)
  assertNotNull(etat)
 }

 @Test
 fun testRequeteEtatPartie_idInvalide() {
  val client = QuiEstCeClient("localhost", 8080)
  assertThrows(IllegalArgumentException::class.java) {
   client.requeteEtatPartie(-1)
  }
 }

 @Test
 fun testRequeteEtatPartie_etapeInitialisation() {
  val client = QuiEstCeClient("localhost", 8080)
  val joueur = client.requeteCreationJoueur("Robbie", "Margot")
  val idPartie = client.requeteCreationPartie(joueur.id, joueur.cle)
  val etat = client.requeteEtatPartie(idPartie)
  assertEquals(ETAPE.CREEE, etat.etape)
 }

 @Test
 fun testRequeteEtatPartie_champsNonNuls() {
  val client = QuiEstCeClient("localhost", 8080)
  val joueur = client.requeteCreationJoueur("Kardashian", "Kim")
  val idPartie = client.requeteCreationPartie(joueur.id, joueur.cle)
  val etat = client.requeteEtatPartie(idPartie)
  assertNotNull(etat.idJoueur1)
  assertNotNull(etat.etape)
 }

 @Test
 fun testRequeteEtatPartie_consistanceAvecJoueur() {
  val client = QuiEstCeClient("localhost", 8080)
  val joueur = client.requeteCreationJoueur("Sha", "Kira")
  val idPartie = client.requeteCreationPartie(joueur.id, joueur.cle)
  val etat = client.requeteEtatPartie(idPartie)
  assertEquals(joueur.id, etat.idJoueur1)
 }

 //méthode requeteListePartiesCrees

 @Test
 fun testRequeteListePartiesCrees_apresCreation() {
  val client = QuiEstCeClient("localhost", 8080)
  val joueur = client.requeteCreationJoueur("Neutron", "Jimmy")
  client.requeteCreationPartie(joueur.id, joueur.cle)
  val parties = client.requeteListePartiesCreees()
  assertTrue(parties.isNotEmpty())
 }

 @Test
 fun testRequeteListePartiesCrees_contenuPositif() {
  val client = QuiEstCeClient("localhost", 8080)
  val joueur = client.requeteCreationJoueur("Jackman", "Hugh")
  client.requeteCreationPartie(joueur.id, joueur.cle)
  val parties = client.requeteListePartiesCreees()
  assertTrue(parties.all { it > 0 })
 }

 @Test
 fun testRequeteListePartiesCrees_coherence() {
  val client = QuiEstCeClient("localhost", 8080)
  val joueur = client.requeteCreationJoueur("Cooper", "Bradley")
  val id = client.requeteCreationPartie(joueur.id, joueur.cle)
  val parties = client.requeteListePartiesCreees()
  assertTrue(parties.contains(id))
 }

 @Test
 fun testRequeteListePartiesCrees_multiple() {
  val client = QuiEstCeClient("localhost", 8080)
  val joueur = client.requeteCreationJoueur("Cavill", "Henry")
  repeat(3) { client.requeteCreationPartie(joueur.id, joueur.cle) }
  val parties = client.requeteListePartiesCreees()
  assertTrue(parties.size >= 3)
 }

 //méthode requeteRejoindrePartie

 @Test
 fun testRequeteRejoindrePartie_valide() {
  val client = QuiEstCeClient("localhost", 8080)
  val joueur1 = client.requeteCreationJoueur("Cendrillon", "Cendrillon")
  val idPartie = client.requeteCreationPartie(joueur1.id, joueur1.cle)
  val joueur2 = client.requeteCreationJoueur("Blanche", "Neige")
  val etat = client.requeteRejoindrePartie(idPartie, joueur2.id, joueur2.cle)
  assertNotNull(etat)
 }

 @Test
 fun testRequeteRejoindrePartie_idInvalide() {
  val client = QuiEstCeClient("localhost", 8080)
  val joueur = client.requeteCreationJoueur("Green", "Rachel")
  assertThrows(IllegalArgumentException::class.java) {
   client.requeteRejoindrePartie(-1, joueur.id, joueur.cle)
  }
 }

 @Test
 fun testRequeteRejoindrePartie_cleInvalide() {
  val client = QuiEstCeClient("localhost", 8080)
  val joueur = client.requeteCreationJoueur("Carrel", "Steve")
  assertThrows(IllegalArgumentException::class.java) {
   client.requeteRejoindrePartie(1, joueur.id, "")
  }
 }

 @Test
 fun testRequeteRejoindrePartie_deuxiemeJoueur() {
  val client = QuiEstCeClient("localhost", 8080)
  val joueur1 = client.requeteCreationJoueur("Lautner", "Taylor")
  val idPartie = client.requeteCreationPartie(joueur1.id, joueur1.cle)
  val joueur2 = client.requeteCreationJoueur("Aliagas", "Nikos")
  val etat = client.requeteRejoindrePartie(idPartie, joueur2.id, joueur2.cle)
  assertEquals(joueur2.id, etat.idJoueur2)
 }

 @Test
 fun testRequeteRejoindrePartie_etapeAttenteQuestion() {
  val client = QuiEstCeClient("localhost", 8080)
  val joueur1 = client.requeteCreationJoueur("Swift", "Taylor")
  val idPartie = client.requeteCreationPartie(joueur1.id, joueur1.cle)
  val joueur2 = client.requeteCreationJoueur("Paco", "Rabanne")
  val etat = client.requeteRejoindrePartie(idPartie, joueur2.id, joueur2.cle)
  assertEquals(ETAPE.INITIALISATION, etat.etape)
 }

 //méthode requeteChoixPersonnage


 @Test
 fun testRequeteChoixPersonnage_nomInvalide() {
  val client = QuiEstCeClient("localhost", 8080)
  val joueur = client.requeteCreationJoueur("Jolie", "Angelina")
  val idPartie = client.requeteCreationPartie(joueur.id, joueur.cle)
  assertThrows(QuiEstCeException::class.java) {
   client.requeteChoixPersonnage(idPartie, joueur.id, joueur.cle, 0, 0)
  }
 }

 @Test
 fun testRequeteChoixPersonnage_positionIncorrecte() {
  val client = QuiEstCeClient("localhost", 8080)
  val joueur = client.requeteCreationJoueur("Jackson", "Mickael")
  val idPartie = client.requeteCreationPartie(joueur.id, joueur.cle)
  val personnage = client.requeteGrilleJoueur(idPartie, joueur.id)[0]
  assertThrows(IllegalArgumentException::class.java) {
   client.requeteChoixPersonnage(idPartie, joueur.id, joueur.cle, -1, -1)
  }
 }

 //méthode requeteGrilleJoueur
 @Test
 fun testRequeteGrilleJoueur_nonVide() {
  val client = QuiEstCeClient("localhost", 8080)
  val joueur = client.requeteCreationJoueur("Rinner", "Teddy")
  val idPartie = client.requeteCreationPartie(joueur.id, joueur.cle)
  val grille = client.requeteGrilleJoueur(idPartie, joueur.id)
  assertTrue(grille.isNotEmpty())
 }

 @Test
 fun testRequeteGrilleJoueur_accesInvalide() {
  val client = QuiEstCeClient("localhost", 8080)
  assertThrows(IllegalArgumentException::class.java) {
   client.requeteGrilleJoueur(-1, -1)
  }
 }

 //méthode requetePoserQuestionValide


 @Test
 fun testRequetePoserQuestion_tropTot() {
  val client = QuiEstCeClient("localhost", 8080)
  val joueur = client.requeteCreationJoueur("Clarkson", "Kelly")
  val idPartie = client.requeteCreationPartie(joueur.id, joueur.cle)
  assertThrows(QuiEstCeException::class.java) {
   client.requetePoserQuestion(idPartie, joueur.id, joueur.cle, "Est-ce qu'il a des lunettes ?")
  }
 }

 //méthode

 @Test
 fun testRequeteDonnerReponse_sansQuestion() {
  val client = QuiEstCeClient("localhost", 8080)
  val joueur1 = client.requeteCreationJoueur("Morgan", "Clara")
  val idPartie = client.requeteCreationPartie(joueur1.id, joueur1.cle)
  val joueur2 = client.requeteCreationJoueur("Reaves", "Keanu")
  client.requeteRejoindrePartie(idPartie, joueur2.id, joueur2.cle)
  assertThrows(QuiEstCeException::class.java) {
   client.requeteDonnerReponse(idPartie, joueur2.id, joueur2.cle, "oui")
  }
 }
 //méthode

 @Test
 fun testRequeteChercherEncore_horsTour() {
  val client = QuiEstCeClient("localhost", 8080)
  val joueur1 = client.requeteCreationJoueur("Verstappen", "Max")
  val idPartie = client.requeteCreationPartie(joueur1.id, joueur1.cle)
  val joueur2 = client.requeteCreationJoueur("Ocon", "Esteban")
  client.requeteRejoindrePartie(idPartie, joueur2.id, joueur2.cle)
  assertThrows(QuiEstCeException::class.java) {
   client.requeteChercherEncore(idPartie, joueur2.id, joueur2.cle)
  }
 }

 //méthode


 @Test
 fun testRequeteTrouve_avecCleInvalide() {
  val client = QuiEstCeClient("localhost", 8080)
  val joueur = client.requeteCreationJoueur("Semoune", "Ellie")
  val idPartie = client.requeteCreationPartie(joueur.id, joueur.cle)
  val personnage = client.requeteGrilleJoueur(idPartie, joueur.id)[0]
  assertThrows(IllegalArgumentException::class.java) {
   client.requeteTrouve(idPartie, joueur.id, "cle_invalide", 0, 0)
  }
 }

//}
}

