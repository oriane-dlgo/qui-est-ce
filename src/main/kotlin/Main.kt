import info.but1.sae2025.QuiEstCeClient
import info.but1.sae2025.data.IdentificationJoueur
import info.but1.sae2025.data.Personnage
import modele.Client

fun main() {
    println("C ki la ?")
    var serveur: QuiEstCeClient
    serveur = QuiEstCeClient("172.26.69.145", 8080)
    // configuration à modifier bien entendu
    serveur.requeteEssai()


    // TESTS
    // Initialisation de variables
    val client = Client()
    var playerQ = 1
    var playerR = 2

    // Initialisation de deux fake IDJoueur
    var player1Id = IdentificationJoueur(7, "K")
    var player2Id = IdentificationJoueur(11, "W")

    // Initialisation d'une fake liste de personnages
    var characterList = mutableListOf(
        Personnage("Renard", "Clara", "http://172.26.69.145:8080/resources/but1/RENARD-clara.jpg"),
        Personnage("Delgado", "Oriane", "http://172.26.69.145:8080/resources/but1/DELGADO-oriane.jpg"),
        Personnage("Ferreira", "Gaetan", "http://172.26.69.145:8080/resources/but1/FERREIRA-gaetan.jpg"),
        Personnage("Filmont", "Felix", "http://172.26.69.145:8080/resources/but1/FILMONT-felix.jpg"),
        Personnage("Chelli", "Enzo", "http://172.26.69.145:8080/resources/but1/CHELLI-enzo.jpg"),
        Personnage("Cochard", "Bastian", "http://172.26.69.145:8080/resources/but1/FERREIRA-gaetan.jpg")
    )

    var keepPlaying: Boolean = true
    while (keepPlaying) {


        // Demande de creation de partie
        var check: Boolean = false
        while (!check) {
            println("Voulez creer une partie ? y/n")
            val answerMatchCreate = readln()

            if (answerMatchCreate == "y") {
                check = true
            }
        }

        // Demarrage de la partie
        var match = client.matchCreate(player1Id, characterList)
        println("[SYS] : Vous venez de creer la partie n°${match.getId()}")
        println("[SYS] : Liste des parties du client : ${client.getMatchList()}")
        match.joinMatch(player2Id)
        println("[SYS] : Un deuxieme joueur vient de rejoindre la partie")

        // Choix du personnage Joueur 1
        for (i in 0..2) println()
        println("*******JOUEUR 1*********")
        println("[P1] : Voici les personnages :")
        println("[P1] : ${match.getBoardByName(playerQ-1)}")
        println("[P1] : Lequel choisissez vous ?")
        var charIndex1 = readln().toInt()
        var charPick1 = match.pickCharacter(playerQ-1, charIndex1 - 1)
        println("[P1] : Vous avez choisis ${charPick1.prenom}")

        // Choix du personnage Joueur 2
        for (i in 0..50) println()
        println("*******JOUEUR 2*********")
        println("[P2] : Voici les personnages :")
        println("[P2] : ${match.getBoardByName(playerR-1)}")
        println("[P2] : Lequel choisissez vous ?")
        var charIndex2 = readln().toInt()
        var charPick2 = match.pickCharacter(playerR-1, charIndex2 - 1)
        println("[P2] : Vous avez choisis ${charPick2.prenom}")




        // Demarrage de l'enchainement des tours
        match.nextRound()
        var roundsInProgress: Boolean = true
        while (roundsInProgress) {


            // Permet d'alterner les joueur sur les tours
            if (match.getRound()%2!=0) {
                playerQ = 1
                playerR = 2
            }
            else{
                playerQ = 2
                playerR = 1
            }

            // Debut du tour
            // Question ou guess - JOUEUR 1
            for (i in 0..50) println()
            println("*******JOUEUR $playerQ*********")
            println("[P$playerQ] : Voici les personnages :")
            println("[P$playerQ] : ${match.getBoardByName(playerQ-1)}")
            println("[P$playerQ] : Posez une question a votre adversaire :")
            match.putQuestion(readln())

            // Reponse - JOUEUR 2
            for (i in 0..50) println()
            println("*******JOUEUR $playerR*********")
            println("[P$playerR] : Voici la question de votre adversaire : ${match.getQuestion()}")
            println("[P$playerR] : Est-ce vrai ou faux ?")
            match.putAnswer(readln())

            // Ellimination de personnages - JOUEUR 1
            for (i in 0..50) println()
            println("*******JOUEUR $playerQ*********")
            println("[P$playerQ] : Votre adversaire vous a repondu : ${match.getAnswer()}")
            println("[P$playerQ] : Lequel de ces personnages eliminez vous ?(put number, -1 for none) ${match.getBoardByName(playerQ-1)}")

            var killList = mutableListOf<Int>()
            killList.add(readln().toInt() - 1)
            var check1 = false
            while (!check1) {
                println("[P$playerQ] : Une autre ? -1 for none")
                var res = readln().toInt() - 1
                if (res >= 0) {
                    killList.add(res)
                } else {
                    check1 = true
                }
            }
            if (killList.size > 0) {
                match.removeCharacter(playerQ-1, killList)
            }
            println("[P$playerQ] : Il vous reste : ${match.getBoardByName(playerQ-1)}")

            // Tour suivant
            var check2: Boolean = false
            while (!check2) {
                println("[P1] : Tour suivant ? y/n")
                val nextRound = readln()
                if (nextRound == "y") {
                    check2 = true
                    match.nextRound()

                }
            }



            /*
            if (match.getWinner()) {
                println("Partie terminé ? y/n")
                val answerQuitMatch = readln()
                if (answerQuitMatch == "y") {
                    roundsInProgress = false
                }
            }
            */

        }

        println("Voulez vous fermer le jeu ? y/n")
        val answerQuitGame = readln()
        if (answerQuitGame == "y") {
            keepPlaying = false
        }
    }
}