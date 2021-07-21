import org.junit.Test
import visitor.BuyerFundsVisitor
import visitor.VendorMarginVisitor
import visitor.VendorRestockVisitor
import kotlin.random.Random

class SimulationTest {

    private val maxDays = 200

    private fun eventLoop(buyers: List<Buyer>, vendors: List<Vendor>, bank: Bank) {
        for (day in 1..maxDays) {
            vendors.forEach {
                it acceptVisitor VendorRestockVisitor()
            }

            buyers.forEach {
                it acceptVisitor BuyerFundsVisitor(300f)
            }

            vendors.forEach {
                val randomMargin = Random.nextDouble(0.98, 1.02).toFloat()
                it.acceptVisitor(VendorMarginVisitor(randomMargin))
            }

            // SALE
            val rand = Random.nextInt(5,10)
            if (day % rand == 0) {
                vendors.random().apply {
                    acceptVisitor(VendorMarginVisitor(0.4f))
                    acceptVisitor(VendorMarginVisitor(1.4f))
                }
            }

            vendors.forEach {
                it.evaluateRevenue()
            }

            bank.evaluateTaxes()
            bank.currentDay++
        }
        with(bank) {
            println(
                """
                    
                taxes over time: $taxesOverTime
                avg: ${taxesOverTime.average()}
                """.trimIndent()
            )
            println("""
                
                revenue over time: $revenueOverTime
                avg: ${revenueOverTime.average()}
                """.trimIndent()
            )
            println("""
                
                products sold over time: ${productsSoldOverTime.toList()}
                avg: ${productsSoldOverTime.average()}
                """.trimIndent()
            )
        }
    }

    private fun randomDesiredPrice() = Random.nextDouble(100.0, 150.0)

    private val allProducts = Product.values().toList()

    private fun getRandomDesiredPricesByProduct(): Map<Product, Float> {
        val desiredProducts = allProducts.randomSubList()
        return desiredProducts.associateWith { randomDesiredPrice().toFloat() }
    }

    private fun getBuyersToVendorsToBank(): Triple<List<Buyer>, List<Vendor>, Bank> {
        val buyers = List(10) {
            Buyer(500.0f, getRandomDesiredPricesByProduct(), Bank.INIT_TAX)
        }

        val initStock = 30
        val dailyRevenueGoal = 1500f
        val vendors = listOf(
            Vendor(initStock, Product.VEGETABLE, 90.0f, Bank.INIT_TAX, dailyRevenueGoal),
            Vendor(initStock, Product.CHEESE, 80.0f, Bank.INIT_TAX, dailyRevenueGoal),
            Vendor(initStock, Product.MEAT, 100.0f, Bank.INIT_TAX, dailyRevenueGoal),
            Vendor(initStock, Product.FRUIT, 110f, Bank.INIT_TAX, dailyRevenueGoal),
            Vendor(initStock, Product.BREAD, 120.0f, Bank.INIT_TAX, dailyRevenueGoal),
        )

        val bank = Bank(maxDays)

        vendors.forEach { vendor ->
            buyers.forEach { buyer ->
                buyer observe bank
                if (vendor.product in buyer.desiredPricesByProduct.keys) {
                    buyer observe vendor
                }
            }
            vendor observe bank
            bank observe vendor
        }
        return Triple(buyers, vendors, bank)
    }

    @Test
    fun `test 1`() {
        val (buyers, vendors, bank) = getBuyersToVendorsToBank()
        eventLoop(buyers, vendors, bank)
    }
}