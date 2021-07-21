import observer.Observer
import observer.Subject
import visitor.CurrentTaxVisitor
import visitor.Element
import visitor.Visitor

class Buyer(
    var funds: Float,
    val desiredPricesByProduct: Map<Product, Float>,
    var currentTax: Float
) : Observer, Element {

    private fun buyProductIfPriceIsRight(vendor: Vendor) {
        val desiredProduct = desiredPricesByProduct[vendor.product]
        desiredProduct?.let { desiredPrice ->
            val productPrice = vendor.currentPrice
            if (funds - productPrice >= 0 && productPrice <= desiredPrice) {
                buyProductFrom(vendor)
            }
        }
    }

    private fun buyProductFrom(vendor: Vendor) {
        vendor.sellProductTo(this)
    }

    override fun update(subject: Subject) {
        when (subject) {
            is Bank ->
                acceptVisitor(CurrentTaxVisitor(subject))
            is Vendor ->
                buyProductIfPriceIsRight(subject)
        }
    }

    override fun acceptVisitor(visitor: Visitor) {
        visitor.visit(this)
    }
}