package search

import java.io.File

private const val MENU_STR = "\n=== Menu ===\n1. Find a person\n2. Print all people\n0. Exit"
private const val ENTER_QUERY = "\nEnter a name or email to search all suitable people."
private const val NOT_FOUND: String = "Not found"
private const val INCORRECT_OPTION: String = "\nIncorrect option! Try again."

private var persons = mutableListOf<Person>()

fun main(args: Array<String>) {
    if (args.size >= 2) {
        val command = args[0]
        if (command == "--data") {
            loadData(args[1])
            startProgram()
            println("\nBye!")
        } else {
            println("Unknown command: $command")
        }
    } else {
        println("Not enough arguments. Bye!")
    }
}

private fun loadData(fileName: String) {
    val dataFile = File(fileName)
    dataFile.forEachLine { persons.add(Person(it)) }
}

fun startProgram() {
    println(MENU_STR)
    var input = readln()
    while (input != "0") {
        when (input) {
            "1" -> findPerson(persons)
            "2" -> persons.printAll()
            else -> println(INCORRECT_OPTION)
        }
        println(MENU_STR)
        input = readln()
    }
}

private fun findPerson(lines: Collection<Person>) {
    println(ENTER_QUERY)
    val query = readln()
    val containingLines = lines.filter { it.data.contains(query, ignoreCase = true) }
    if (containingLines.isEmpty()) {
        println(NOT_FOUND)
    } else {
        containingLines.forEach { it.print() }
    }
}


