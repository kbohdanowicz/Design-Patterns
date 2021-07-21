package visitor

import Buyer

class BuyerFundsVisitor(private val amount: Float): Visitor {

    override fun visit(element: Element) {
        (element as Buyer).funds += amount
    }
}