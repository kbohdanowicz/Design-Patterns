package board

infix fun Collection<Int>.crossProduct(collection: Collection<Int>): List<Pair<Int, Int>> {
    return this.flatMap { lhsElem -> collection.map { rhsElem -> lhsElem to rhsElem } }
}

infix fun IntRange.crossProduct(range: IntRange): List<Pair<Int, Int>> {
    return this.flatMap { lhsElem -> range.map { rhsElem -> lhsElem to rhsElem } }
}

fun <T> Collection<T>.randomSubList(minSize: Int = 1, maxSize: Int = this.size - 1): List<T> {
    val randomSize = (minSize .. maxSize).random()
    val tempMutList = this.toMutableList()
    return List(randomSize) { _ ->
        tempMutList.random().also {
            tempMutList.remove(it)
        }
    }
}
