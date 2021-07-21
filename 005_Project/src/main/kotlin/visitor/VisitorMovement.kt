package visitor

import misc.Position
import creature.Creature

class VisitorMovement(private val position: Position) : Visitor {

    override fun visit(element: Element) {
        (element as Creature).position = position
    }
}