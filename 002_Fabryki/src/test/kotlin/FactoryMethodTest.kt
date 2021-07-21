import factoryMethod.factory.BakedPierogiFactory
import factoryMethod.factory.BoiledPierogiFactory
import factoryMethod.factory.FriedPierogiFactory
import org.junit.jupiter.api.Test

class FactoryMethodTest {

    private val classic = "classic"
    private val sweetPotato = "sweet potato"
    private val strawberry = "strawberry"
    private val meat = "meat"
    private val cottageCheese = "cottage cheese"

    private fun testBlock() {
        with(BoiledPierogiFactory) {
            create(classic)
            create(sweetPotato)
            create(strawberry)
            create(meat)
            create(cottageCheese)
        }

        with(BakedPierogiFactory) {
            create(classic)
            create(sweetPotato)
            create(strawberry)
            create(meat)
            create(cottageCheese)
        }

        with(FriedPierogiFactory) {
            create(classic)
            create(sweetPotato)
            create(strawberry)
            create(meat)
            create(cottageCheese)
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