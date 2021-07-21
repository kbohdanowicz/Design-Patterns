package visitor

import Buyer
import Vendor

class VendorRestockVisitor : Visitor {

    override fun visit(element: Element) {
        with(element as Vendor) {
            stock = initStock
        }
    }
}