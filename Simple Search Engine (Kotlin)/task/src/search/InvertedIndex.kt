package search

class InvertedIndex{
    private val people: MutableList<Person> = mutableListOf()
    private val index: HashMap<String, MutableSet<Int>> = hashMapOf()

    fun add(person: Person) {
        val pos = people.size
        people.add(person)
        person.getItems().forEach {
            if(index.containsKey(it)) {
                index[it]!!.add(pos)
            } else {
                index[it] = mutableSetOf(pos)
            }
        }
    }

    fun findPerson(wholeQuery: String, strategy: SearchStrategy): Set<Person> {
        val singleQueries = wholeQuery.split(" ").map(String::lowercase)
        val personSet : Set<Person>
        when (strategy) {
            SearchStrategy.ANY -> {
                personSet = mutableSetOf()
                singleQueries.forEach { query ->
                    val queryResult = index[query]?.map { people[it] }
                    queryResult?.let { personSet.addAll(it) }
                }
            }
            SearchStrategy.NONE -> {
                val personIndices : MutableSet<Int> = (0..people.lastIndex).toMutableSet()
                singleQueries.forEach { query ->
                    if (index.containsKey(query)) {
                        personIndices.removeAll(index[query]!!)
                    }
                }
                personSet = personIndices.map { index -> people[index] }.toSet()
            }
            SearchStrategy.ALL -> {
                personSet = people.toMutableSet()
                singleQueries.forEach { query ->
                    if (index.containsKey(query)) {
                        val queryResult = index[query]?.map { people[it] }
                        queryResult?.let { personSet.retainAll(queryResult) }
                    } else {
                     personSet.clear()
                    }
                }
            }
        }
        return personSet
    }

    fun getAll() : List<Person> {
        return people.toList()
    }
}

enum class SearchStrategy {ALL, ANY, NONE}