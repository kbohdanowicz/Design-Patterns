package decorator

import visitor.Element
import visitor.Visitor

abstract class VisitorDecorator (private val visitor: Visitor) : Visitor {
    override fun visit(element: Element) {
        visitor.visit(element)
    }
}