package visitor

import Buyer
import Vendor

class VendorMarginVisitor(private val multiplier: Float) : Visitor {

    override fun visit(element: Element) {
        with(element as Vendor) {
            margin *= multiplier
            recalculatePrice()
            notifyObservers(observers.filterIsInstance<Buyer>())
        }
    }
}