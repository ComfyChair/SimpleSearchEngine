package search

import java.io.File

private const val MENU_STR = "\n=== Menu ===\n1. Find a person\n2. Print all people\n0. Exit"
private const val SELECT_STRATEGY = "Select a matching strategy: ALL, ANY, NONE"
private const val ENTER_QUERY = "\nEnter a name or email to search all suitable people."
private const val NOT_FOUND: String = "Not found"
private const val INCORRECT_OPTION: String = "\nIncorrect option! Try again."

private var searchIndex = InvertedIndex()

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

fun startProgram() {
    println(MENU_STR)
    var input = readln()
    while (input != "0") {
        when (input) {
            "1" -> startSearch()
            "2" -> printAll()
            else -> println(INCORRECT_OPTION)
        }
        println(MENU_STR)
        input = readln()
    }
}

fun printAll() {
    searchIndex.getAll().forEach(::println)
}

fun startSearch() {
    println(SELECT_STRATEGY)
    val strategyName = readln()
    if (strategyName in SearchStrategy.values().map { it.name }) {
        val strategy : SearchStrategy = SearchStrategy.valueOf(strategyName)
        println(ENTER_QUERY)
        val query = readln()
        val resultList = searchIndex.findPerson(query, strategy)
        if (resultList.isEmpty()) {
            println(NOT_FOUND)
        } else {
            resultList.forEach(::println)
        }
    } else {
        println(INCORRECT_OPTION)
    }
}

private fun loadData(fileName: String) {
    val dataFile = File(fileName)
    dataFile.forEachLine { searchIndex.add(Person(it)) }
}
