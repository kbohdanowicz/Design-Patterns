import observer.Observer
import observer.Subject
import visitor.Element
import visitor.CurrentTaxVisitor
import visitor.VendorMarginVisitor
import visitor.Visitor

class Vendor(
    val initStock: Int,
    val product: Product,
    var margin: Float,
    var currentTax: Float,
    private val dailyRevenueGoal: Float
) : Subject(), Observer, Element {

    var stock: Int = initStock

    val costPlusMargin: Float
        get() = product.cost + margin

    private var _currentPrice: Float = costPlusMargin + costPlusMargin * currentTax

    val currentPrice: Float
        get() = _currentPrice

    private var currentRevenue: Float = 0f

    fun recalculatePrice() {
        _currentPrice = costPlusMargin + costPlusMargin * currentTax
    }

    fun evaluateRevenue() {
        val ratio = currentRevenue / dailyRevenueGoal
        val marginMultiplier = when {
            ratio > 1.1f -> 1.1f
            ratio < 0.9f -> 0.9f
            else -> ratio
        }
        acceptVisitor(VendorMarginVisitor(marginMultiplier))
        currentRevenue = 0f
    }

    infix fun sellProductTo(buyer: Buyer) {
        if (stock > 0) {
            stock--
            buyer.funds -= currentPrice
            currentRevenue += margin
            notifyObservers(observers.filterIsInstance<Bank>())
        }
    }

    override fun update(subject: Subject) {
        if (subject is Bank) {
            acceptVisitor(CurrentTaxVisitor(subject))
            notifyObservers(observers.filterIsInstance<Buyer>())
        }
    }

    override fun acceptVisitor(visitor: Visitor) {
        visitor.visit(this)
    }
}