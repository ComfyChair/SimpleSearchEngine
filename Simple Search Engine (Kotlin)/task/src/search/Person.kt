package search

data class Person(val data: String) {
    fun getItems(): List<String> {
        return data.split(" ")
            .filterNot { it.isBlank() }
            .map { it.lowercase() }
    }
    override fun toString(): String {
        return this.data
    }
}