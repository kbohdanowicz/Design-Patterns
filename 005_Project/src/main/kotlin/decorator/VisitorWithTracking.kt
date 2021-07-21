package decorator

import misc.Tracker
import creature.Creature
import visitor.Element
import visitor.Visitor

class VisitorWithTracking(visitor: Visitor) : VisitorDecorator(visitor) {

    override fun visit(element: Element) {
        super.visit(element)
        (element as Creature).let {
            it.notifyObservers(it.observers.filterIsInstance<Tracker>())
        }
    }
}