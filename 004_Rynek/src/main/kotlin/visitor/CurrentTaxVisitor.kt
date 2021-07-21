package visitor

import Bank
import Buyer
import Vendor

class CurrentTaxVisitor(private val bank: Bank) : Visitor {

    override fun visit(element: Element) {
        when(element) {
            is Vendor -> {
                with(element) {
                    currentTax = bank.currentTax
                    recalculatePrice()
                }
            }
            is Buyer ->
                element.currentTax = bank.currentTax
        }
    }
}