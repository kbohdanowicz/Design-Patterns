package visitor

interface Element {
    infix fun acceptVisitor(visitor: Visitor)
}