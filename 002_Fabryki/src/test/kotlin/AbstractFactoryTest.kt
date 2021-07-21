import abstractFactory.factory.BakedPierogiFactory
import abstractFactory.factory.BoiledPierogiFactory
import abstractFactory.factory.FriedPierogiFactory
import abstractFactory.factory.PierogiFactory
import org.junit.jupiter.api.Test

class AbstractFactoryTest {

    private fun testBlock() {
        with(BakedPierogiFactory) {
            createClassic()
            createSweetPotato()
            createStrawberry()
            createMeat()
            createCottageCheese()
        }

        with(BoiledPierogiFactory) {
            createClassic()
            createSweetPotato()
            createStrawberry()
            createMeat()
            createCottageCheese()
        }

        with(FriedPierogiFactory) {
            createClassic()
            createSweetPotato()
            createStrawberry()
            createMeat()
            createCottageCheese()
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