package visitor

interface Element {
    fun accept(visitor: Visitor)
}