import org.junit.Test
import visitor.BuyerFundsVisitor
import visitor.VendorMarginVisitor
import visitor.VendorRestockVisitor

class Tests {

    private val initStock = 1

    private fun getMockBank(): Bank = Bank(50)

    private fun getMockVendor(): Vendor =
        Vendor(initStock, Product.VEGETABLE, 10f, Bank.INIT_TAX, 500f)

    private fun getAllDesiredPricesByProduct(): Map<Product, Float> =
        Product.values().associateWith { 10000f }


    private fun getMockBuyer(): Buyer =
        Buyer(10000f, getAllDesiredPricesByProduct(), Bank.INIT_TAX)

    @Test
    fun `test vendor restock`() {
        val (vendor, buyer) = getMockVendor() to getMockBuyer()
        vendor sellProductTo buyer
        vendor acceptVisitor VendorRestockVisitor()

        assert(vendor.stock == initStock)
    }

    @Test
    fun `test buyer does not pay for product if stock is empty`() {
        val (vendor, buyer) = getMockVendor() to getMockBuyer()
        vendor.stock = 0
        val fundsBefore = buyer.funds
        vendor sellProductTo buyer

        assert(buyer.funds == fundsBefore)
    }

    @Test
    fun `test vendor can not sell product if stock is empty`() {
        val (vendor, buyer) = getMockVendor() to getMockBuyer()
        vendor.stock = 0
        vendor sellProductTo buyer

        assert(vendor.stock == 0)
    }

    @Test
    fun `test sell product`() {
        val (vendor, buyer) = getMockVendor() to getMockBuyer()
        vendor sellProductTo buyer

        assert(vendor.stock == initStock - 1)
    }

    @Test
    fun `test observe functionality`() {
        val (vendor, bank) = getMockVendor() to getMockBank()
        vendor observe bank

        assert(vendor in bank.observers)
    }

    @Test
    fun `test vendor updates margin correctly`() {
        val vendor = getMockVendor()
        val multiplier = 1.05f
        val marginBefore = vendor.margin
        vendor acceptVisitor VendorMarginVisitor(multiplier)

        assert(vendor.margin == marginBefore * multiplier)
    }


    @Test
    fun `test vendor updates price correctly`() {
        val vendor = getMockVendor()
        vendor acceptVisitor VendorMarginVisitor(1.05f)

        with(vendor) {
            assert(currentPrice == costPlusMargin + costPlusMargin * currentTax)
        }
    }

    @Test
    fun `test buyer pays for product`() {
        val (vendor, buyer) = getMockVendor() to getMockBuyer()
        val fundsBefore = buyer.funds
        vendor sellProductTo buyer

        assert(buyer.funds == fundsBefore - vendor.currentPrice)
    }

    @Test
    fun `test buyer gets funds from visitor`() {
        val buyer = getMockBuyer()
        val amount = 300f
        val fundsBefore = buyer.funds
        buyer acceptVisitor BuyerFundsVisitor(amount)

        assert(buyer.funds == fundsBefore + amount)
    }

    @Test
    fun `test buyer can observe vendor`() {
        val (vendor, buyer) = getMockVendor() to getMockBuyer()
        buyer observe vendor

        assert(buyer in vendor.observers)
    }

    @Test
    fun `test buyer can observe bank`() {
        val (buyer, bank) = getMockBuyer() to getMockBank()
        buyer observe bank

        assert(buyer in bank.observers)
    }

    @Test
    fun `test vendor can observe bank`() {
        val (vendor, bank) = getMockVendor()  to getMockBank()
        vendor observe bank

        assert(vendor in bank.observers)
    }

    @Test
    fun `test bank can observe vendor`() {
        val (bank, vendor) = getMockBank() to getMockVendor()
        bank observe vendor

        assert(bank in vendor.observers)
    }
}