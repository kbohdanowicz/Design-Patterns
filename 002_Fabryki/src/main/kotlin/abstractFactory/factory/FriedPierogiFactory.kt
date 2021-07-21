package abstractFactory.factory

import abstractFactory.pierogi.FriedPierogi
import abstractFactory.pierogi.Pierogi

object FriedPierogiFactory : PierogiFactory("frying") {

    override fun createClassic(): Pierogi = FriedPierogi(listOf("potato", "onion"))

    override fun createSweetPotato(): Pierogi = FriedPierogi(listOf("sweet potato", "onion"))

    override fun createStrawberry(): Pierogi = FriedPierogi(listOf("strawberry", "vanilla extract"))

    override fun createMeat(): Pierogi = FriedPierogi(listOf("meat", "onion"))

    override fun createCottageCheese(): Pierogi = FriedPierogi(listOf("cottage cheese"))
}