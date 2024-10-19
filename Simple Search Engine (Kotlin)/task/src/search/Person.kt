package search

data class Person(val data: String)

fun Person.print() {
    println(this.data)
}

fun Collection<Person>.printAll() {
    println("=== List of people ===")
    this.forEach { it.print() }
}