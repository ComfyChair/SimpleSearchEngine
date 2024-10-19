package search
private const val NOT_FOUND: String = "Not found"

fun main() {
    val noOfLines = readln().toInt()
    val lines = Array(noOfLines) { readln() }
    val noOfQueries = readln().toInt()

    for (i in 0 until noOfQueries) {
        val query = readln()
        val containingLines = lines.filter { it.contains(query, ignoreCase = true) }
        if (containingLines.isEmpty()) {
            println(NOT_FOUND)
        } else {
            containingLines.forEach { println(it) }
        }
    }
}
