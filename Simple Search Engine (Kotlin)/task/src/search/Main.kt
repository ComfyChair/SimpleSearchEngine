package search

private const val MENU_STR = "\n=== Menu ===\n1. Find a person\n2. Print all people\n0. Exit"
private const val ENTER_QUERY = "\nEnter a name or email to search all suitable people."
private const val NOT_FOUND: String = "Not found"
private const val INCORRECT_OPTION: String = "\nIncorrect option! Try again."

private lateinit var persons: Array<Person>

fun main() {
    println("Enter the number of people:")
    val noOfLines = readln().toInt()
    println("Enter all people:")
    persons = Array(noOfLines) { Person(readln()) }
    showMenu()
    println("\nBye!")
}

fun showMenu() {
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

private fun findPerson(lines: Array<Person>) {
    println(ENTER_QUERY)
    val query = readln()
    val containingLines = lines.filter { it.data.contains(query, ignoreCase = true) }
    if (containingLines.isEmpty()) {
        println(NOT_FOUND)
    } else {
        containingLines.forEach { it.print() }
    }
}


