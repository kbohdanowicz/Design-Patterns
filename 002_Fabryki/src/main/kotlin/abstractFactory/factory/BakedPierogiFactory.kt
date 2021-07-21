package abstractFactory.factory

import abstractFactory.pierogi.BakedPierogi
import abstractFactory.pierogi.Pierogi

object BakedPierogiFactory : PierogiFactory("baking") {

    override fun createClassic(): Pierogi = BakedPierogi(listOf("potato", "onion"))

    override fun createSweetPotato(): Pierogi = BakedPierogi(listOf("sweet potato", "onion"))

    override fun createStrawberry(): Pierogi = BakedPierogi(listOf("strawberry", "vanilla extract"))

    override fun createMeat(): Pierogi = BakedPierogi(listOf("meat", "onion"))

    override fun createCottageCheese(): Pierogi = BakedPierogi(listOf("cottage cheese"))
}