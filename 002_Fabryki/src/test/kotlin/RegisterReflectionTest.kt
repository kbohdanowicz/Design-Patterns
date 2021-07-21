import org.junit.jupiter.api.Test
import registerReflection.PierogiFactory

class RegisterReflectionTest {

    private val classic = "Classic"
    private val sweetPotato = "SweetPotato"
    private val strawberry = "Strawberry"
    private val meat = "Meat"
    private val cottageCheese = "CottageCheese"

    private val pierogi = "Pierogi"

    private val boiled = "Boiled$pierogi"
    private val baked = "Baked$pierogi"
    private val fried = "Fried$pierogi"

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