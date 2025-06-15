fun updateGrid(): GridPane {

    var state = this.matchState
    var listSelChar = match.listSelChar
    val listHideChar = match.listHideChar

    val gridCharacter = GridPane()
    gridCharacter.isGridLinesVisible = true

    var index = 1

    for (row in 0 until 4) {
        for (col in 0 until 6) {


            val stack = StackPane()



            when (state) {
                CREE -> {
                    return gridCharacter
                }

                INITIALISATION -> {
                    val picture = getPictureOf(row, col, true)
                    // Un seul sélectionné : on efface tous les autres
                    listSelChar.clear()
                    listSelChar.add(id)

                    // Mettre à jour le style de toutes les cases
                    gridCharacter.children.forEach {
                        if (it is StackPane) {
                            val itsId = it.userData as Int
                            it.style = if (itsId == id)
                                stack.children.add(picture)
                                "-fx-border-color: red; -fx-border-width: 3;"
                            gridCharacter.add(stack, col, row)
                        } else {
                            stack.children.add(picture)
                            "-fx-border-color: black; -fx-border-width: 1;"
                            gridCharacter.add(stack, col, row)
                        }

                    }
                }

                ATTENTE_REFLEXION -> {
                    val picture = getPictureOf(row, col)
                    if (id in listSelChar) {
                        listSelChar.remove(id)
                        stack.children.add(picture)
                        stack.style = "-fx-border-color: black; -fx-border-width: 1;"
                        gridCharacter.add(stack, col, row)
                    } else {
                        listSelChar.add(id)
                        stack.children.add(picture)
                        stack.style = "-fx-border-color: red; -fx-border-width: 3;"
                        gridCharacter.add(stack, col, row)
                    }
                }

                else -> {
                    val picture = if (index in listHideChar) {
                        // Masquer le personnage
                        null
                    } else {
                        // Afficher le personnage
                        getPictureOf(row, col, opponent)
                        stack.children.add(picture)
                        stack.style = "-fx-border-color: black; -fx-border-width: 1;"
                        gridCharacter.add(stack, col, row)
                    }
                }
            }

            index++
        }
    }
    return gridCharacter
}












/*
    fun updateGrid(): GridPane {

        val state = this.matchState
        val listSelChar = mutableListOf<Int>()
        val gridCharacter = GridPane()
        gridCharacter.isGridLinesVisible = true

        var index = 1

        for (row in 0 until 4) {
            for (col in 0 until 6) {
                val stack = StackPane()
                val picture = getPictureOf(row, col, opponent)

                if (index in listSelChar) {
                    picture.opacity = 0.3
                }

                stack.children.add(picture)
                stack.userData = index

                stack.setOnMouseClicked {
                    val id = stack.userData as Int

                    if (selectionUnique) {
                        // Uniquement une case sélectionnée
                        listSelChar.clear()
                        listSelChar.add(id)

                        // Réinitialise le style de toutes les cases
                        for (node in gridCharacter.children) {
                            if (node is StackPane) {
                                node.style = "-fx-border-color: black; -fx-border-width: 1;"
                            }
                        }

                        stack.style = "-fx-border-color: red; -fx-border-width: 3;"

                    } else {
                        // Sélection multiple
                        if (!listSelChar.contains(id)) {
                            listSelChar.add(id)
                            stack.style = "-fx-border-color: red; -fx-border-width: 3;"
                        } else {
                            listSelChar.remove(id)
                            stack.style = "-fx-border-color: black; -fx-border-width: 1;"
                        }
                    }
                }

                gridCharacter.add(stack, col, row)
                index++
            }
        }

        this.listSelChar = listSelChar
        return gridCharacter
    }

*/




















if (index in listSelChar) {
    picture.opacity = 0.3
}

stack.children.add(picture)
stack.userData = index

stack.setOnMouseClicked {
    val id = stack.userData as Int

    if (selectionUnique) {
        // Uniquement une case sélectionnée
        listSelChar.clear()
        listSelChar.add(id)

        // Réinitialise le style de toutes les cases
        for (node in gridCharacter.children) {
            if (node is StackPane) {
                node.style = "-fx-border-color: black; -fx-border-width: 1;"
            }
        }

        stack.style = "-fx-border-color: red; -fx-border-width: 3;"

    } else {
        // Sélection multiple
        if (!listSelChar.contains(id)) {
            listSelChar.add(id)
            stack.style = "-fx-border-color: red; -fx-border-width: 3;"
        } else {
            listSelChar.remove(id)
            stack.style = "-fx-border-color: black; -fx-border-width: 1;"
        }
    }
}

gridCharacter.add(stack, col, row)
index++
}
}

this.listSelChar = listSelChar
return gridCharacter
}


















fun updateGrid(
    match: Match,
    listHideChar: List<Int> = listOf(),
    listSelChar: MutableList<Int> = mutableListOf()
): GridPane {
    val gridCharacter = GridPane()
    gridCharacter.isGridLinesVisible = true

    val etat = match.getMatchState()

    // On déduit la logique de sélection
    val selectionActive: Boolean
    val selectionUnique: Boolean

    when (etat) {
        ETAPE.INITIALISATION -> {
            selectionActive = true
            selectionUnique = true
        }

        ETAPE.ATTENTE_REFLEXION -> {
            selectionActive = true
            selectionUnique = false
        }

        else -> {
            selectionActive = false
            selectionUnique = false
        }
    }

    var index = 1

    for (row in 0 until 4) {
        for (col in 0 until 6) {
            val stack = StackPane()
            val picture = match.getPictureOf(row, col, false)

            if (index in listHideChar) {
                picture.opacity = 0.3
            }

            stack.children.add(picture)
            stack.userData = index

            if (selectionActive) {
                stack.setOnMouseClicked {
                    val id = stack.userData as Int
                    if (selectionUnique) {
                        // Un seul sélectionné : on efface tous les autres
                        listSelChar.clear()
                        listSelChar.add(id)

                        // Mettre à jour le style de toutes les cases
                        gridCharacter.children.forEach {
                            if (it is StackPane) {
                                val itsId = it.userData as Int
                                it.style = if (itsId == id)
                                    "-fx-border-color: red; -fx-border-width: 3;"
                                else
                                    "-fx-border-color: black; -fx-border-width: 1;"
                            }
                        }
                    } else {
                        if (id in listSelChar) {
                            listSelChar.remove(id)
                            stack.style = "-fx-border-color: black; -fx-border-width: 1;"
                        } else {
                            listSelChar.add(id)
                            stack.style = "-fx-border-color: red; -fx-border-width: 3;"
                        }
                    }
                }
            }

            stack.style = if (index in listSelChar)
                "-fx-border-color: red; -fx-border-width: 3;"
            else
                "-fx-border-color: black; -fx-border-width: 1;"

            gridCharacter.add(stack, col, row)
            index++
        }
    }

    return gridCharacter
}
