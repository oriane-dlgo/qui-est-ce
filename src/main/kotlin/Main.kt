import Controleurs.ControleurBoutonJouer
import info.but1.sae2025.QuiEstCeClient
import javafx.application.Application
import javafx.scene.Scene
import javafx.stage.Stage
import modele.Client
import vue.MainView

class Main : Application() {
    override fun start(stage: Stage) {
        val vue = MainView()
        val modele = Client(server = QuiEstCeClient("localhost", 8080))

        vue.changeCenterView(vue.btn1, ControleurBoutonJouer(modele, vue)) //on change le center du mainview en creaprofil
       //on change le center du mainview en partie et en listpartie en appuyant sur btnjoin

        val scene = Scene(vue, 600.0, 400.0)
        stage.scene = scene
        stage.title = "C KI LUI ?"
        stage.show()
    }

//fun main() {
////    println("C ki la ?")
////    var server: QuiEstCeClient
////    server = QuiEstCeClient("localhost", 8080) //"172.26.69.145", 8080
////    // configuration à modifier bien entendu
////    server.requeteEssai()
////    val client = Client(server)
////
////    println("** Tests **\n\n")
////
////    var listPlayer = mutableListOf(
////        Joueur("Ferreira", "Gaetan"),
////        Joueur("Renard", "Clara"),
////        Joueur("Delgado", "Oriane"),
////        Joueur("kaw", " "),
////        Joueur("Filmont", "Felix"),
////        Joueur("Chelli", "Enzo"),
////        Joueur("Cochard", "Bastian")
////    )
////
////    //client.playerCreate(server, listPlayer[3])
////
////    println()
////    println("Player list :")
////    println(client.getPlayerList())
////    println()
////
////
////    startBashEdition(server)
//
//    Application.launch(Main::class.java)
//
//}
}

    fun main() {
        Application.launch(Main::class.java)
    }













    // ** Lancement du "Qui Est-ce ? - Bash Edition" **
    //println("Launch Bash Edition ? y/n")
    //if(readln()=="y") startBashEdition(server)
//}

/*
fun bashEdition(server : QuiEstCeClient){

    // Initialisation de variables
    val client = Client(server)
    var playerQ = 1
    var playerR = 2
    var space = 50

    // Initialisation de deux fake IDJoueur
    var listPlayer = mutableListOf(
        Joueur("Ferreira", "Gaetan"),
        Joueur("Renard", "Clara"),
        Joueur("Delgado", "Oriane"),
        Joueur("kaw", " "),
        Joueur("Filmont", "Felix"),
        Joueur("Chelli", "Enzo"),
        Joueur("Cochard", "Bastian")
    )

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

        for (i in 0..space) println()
        println("""
╔═══════════════════════════════════════════════════════════════════╗
║#  °     °     °     ° #     =========     #  °     °     °     ° #║
║...~~~'''~~~...~~~'''~~~     C KI LA ?     ~~~'''~~~...~~~'''~~~...║
║'''~~~...~~~'''~~~...~~~   Bash Edition    ~~~...~~~'''~~~...~~~'''║
║# _     _     _     _  #     =========     # _     _     _     _  #║
╚═══════════════════════════════════════════════════════════════════╝
""")
        // Demande de connection / creation joueur
        println("Veuillez vous connecter :")
        print("Nom :")
        var lastName = readln()
        print("Prenom :")
        var name = readln()

        if (client.playerIsInList(server, lastName, name))



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
        println("## PRESS ENTER ##")
        readln()

        // Choix du personnage Joueur 1
        for (i in 0..space) println()
        println("*** TOUR N° ${match.getRound()} - CHOIX DES PERSONNAGES ***")
        println(
            "_ JOUEUR $playerQ\n_ PLATEAU : ${
                match.getBoardByName(
                    playerQ - 1
                )
            }\n"
        )
        println("Lequel choisissez vous ? 1/2/3...")
        var charIndex1 = readln().toInt()
        var charPick1 = match.pickCharacter(playerQ - 1, charIndex1 - 1)
        println("Vous avez choisis : ${charPick1.prenom} ${charPick1.nom}")
        println("## PRESS ENTER ##")
        readln()

        // Choix du personnage Joueur 2
        for (i in 0..space) println()
        println("*** TOUR N° ${match.getRound()} - CHOIX DES PERSONNAGES ***")
        println(
            "_ JOUEUR $playerR\n_ PLATEAU : ${
                match.getBoardByName(
                    playerR - 1
                )
            }\n"
        )
        println("Lequel choisissez vous ? 1/2/3...")
        var charIndex2 = readln().toInt()
        var charPick2 = match.pickCharacter(playerR - 1, charIndex2 - 1)
        println("Vous avez choisis ${charPick2.prenom} ${charPick2.nom}")
        println("## PRESS ENTER ##")
        readln()


        // Demarrage de l'enchainement des tours
        var roundsInProgress: Boolean = true
        while (roundsInProgress) {
            match.nextRound()


            // Permet d'alterner les joueur sur les tours
            if (match.getRound() % 2 != 0) {
                playerQ = 1
                playerR = 2
            } else {
                playerQ = 2
                playerR = 1
            }

            // Debut du tour
            // Question ou guess - JOUEUR 1
            for (i in 0..space) println()
            println("*** TOUR N° ${match.getRound()} - QUESTION or GUESS ***")
            println(
                "_ JOUEUR $playerQ // Personnage choisis : ${match.getCharacterPicked()[playerQ-1].prenom} ${match.getCharacterPicked()[playerQ-1].nom}\n_ PLATEAU : ${
                    match.getBoardByName(
                        playerQ - 1
                    )
                }\n"
            )

            // Lance la demande de proposition si tour 3 mini
            if (match.getRound() > 2) {
                println("Voulez vous faire une suggestion ? y/n")
                if (readln() == "y") {
                    println("Quel personnage voulez vous guess ? 1/2/3...")
                    var charGues = readln().toInt()
                    match.makeGuess(playerQ - 1, charGues - 1)
                } else {
                    println("Posez une question a votre adversaire :")
                    match.putQuestion(readln())
                }
            } else {
                println("Posez une question a votre adversaire :")
                match.putQuestion(readln())
            }
            println("## PRESS ENTER ##")
            readln()


            // Reponse - JOUEUR 2
            for (i in 0..space) println()
            println("*** TOUR N° ${match.getRound()} - REPONSE ***")
            println(
                "_ JOUEUR $playerR // Personnage choisis : ${match.getCharacterPicked()[playerR - 1].prenom} ${match.getCharacterPicked()[playerR - 1].nom}\n_ PLATEAU : ${
                    match.getBoardByName(
                        playerR - 1
                    )
                }\n"
            )


            if (match.getQuestion() == "GUESS") {
                if (match.checkGuess(playerR - 1)) {
                    println("Le joueur adverse a gagné en trouvant ${match.getGuess().prenom}")
                    match.endOfMatch(playerR-1)
                    println("Voulez vous recommencer ? y/n")

                    if (readln() == "y") {
                        break
                    } else {
                        keepPlaying = false
                        break
                    }
                } else {
                    println("Le joueur adverse a proposé ${match.getGuess().prenom} et a échoué")
                }

            } else {
                println("Voici la question de votre adversaire : ${match.getQuestion()}")
                println("Est-ce vrai ou faux ?")
                match.putAnswer(readln())


                // Ellimination de personnages - JOUEUR 1
                for (i in 0..space) println()
                println("*** TOUR N° ${match.getRound()} - ELIMINATION DE PERSONNAGES ***")
                println(
                    "_ JOUEUR $playerQ // Personnage choisis : ${match.getCharacterPicked()[playerQ-1].prenom} ${match.getCharacterPicked()[playerQ-1].nom}\n_ PLATEAU : ${
                        match.getBoardByName(
                            playerQ - 1
                        )
                    }\n"
                )
                println("Votre question etait : ${match.getQuestion()}")
                println("Votre adversaire vous a repondu : ${match.getAnswer()}")
                println("Voulez vous eliminer un personnage ? y/n")
                if (readln() == "y") {
                    var killList = mutableListOf<Int>()

                    var killAgain = true
                    while (killAgain) {
                        println("Lequel eliminer? (1 par 1, laissez vide pour passer)")
                        var res = readln()
                        if (res != "") {
                            killList.add(res.toInt() - 1)
                        } else {
                            killAgain = false
                        }
                    }
                    if (killList.size > 0) {
                        match.removeCharacter(playerQ - 1, killList)
                    }
                }
            }
            // Tour suivant
            println("*** FIN DU TOUR N° ${match.getRound()} ***")
            println("## PRESS ENTER ##")
            readln()

        }

    }
    println("--- FERMETURE DU JEU ---")
}
*/