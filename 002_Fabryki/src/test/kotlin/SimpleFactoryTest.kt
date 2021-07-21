import org.junit.jupiter.api.Test
import simpleFactory.PierogiFactory

class SimpleFactoryTest {

    private val classic = "classic"
    private val sweetPotato = "sweet potato"
    private val strawberry = "strawberry"
    private val meat = "meat"
    private val cottageCheese = "cottage cheese"

    private val boiled = " boiled"
    private val baked = " baked"
    private val fried = " fried"

    private fun testBlock() {
        with(PierogiFactory) {
            create(classic + boiled)
            create(sweetPotato + boiled)
            create(strawberry + boiled)
            create(meat + boiled)
            create(cottageCheese + boiled)

            create(classic + baked)
            create(sweetPotato + baked)
            create(strawberry + baked)
            create(meat + baked)
            create(cottageCheese + baked)

            create(classic + fried)
            create(sweetPotato + fried)
            create(strawberry + fried)
            create(meat + fried)
            create(cottageCheese + fried)
        }

    }

    @Test
    fun `functional test`() {
        testBlock()
    }

    @Test
    fun `performance test`() {
        repeat(10_000_000) {
            testBlock()
        }
    }
}