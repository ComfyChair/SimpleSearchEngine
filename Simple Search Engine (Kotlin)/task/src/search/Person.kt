package search

data class Person(val data: String) {
    fun getItems(): List<String> {
        return data.split(" ").map { it.lowercase() }
    }
    override fun toString(): String {
        return this.data
    }
}