package search

class InvertedIndex {
    private val people: MutableList<Person> = mutableListOf()
    private val index: HashMap<String, MutableList<Int>> = hashMapOf()

    fun add(person: Person) {
        val pos = people.size
        people.add(person)
        person.getItems().forEach {
            if(index.containsKey(it)) {
                index[it]!!.add(pos)
            } else {
                index[it] = mutableListOf(pos)
            }
        }
    }

    fun findPerson(query: String): List<Person>? {
        return index[query.lowercase()]?.map { people[it] }
    }

    fun getAll() : List<Person> {
        return people.toList()
    }
}
