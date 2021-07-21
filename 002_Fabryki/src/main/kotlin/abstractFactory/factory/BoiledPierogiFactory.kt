package abstractFactory.factory

import abstractFactory.pierogi.BoiledPierogi
import abstractFactory.pierogi.Pierogi

object BoiledPierogiFactory : PierogiFactory("boiling") {

    override fun createClassic(): Pierogi = BoiledPierogi(listOf("potato", "onion"))

    override fun createSweetPotato(): Pierogi = BoiledPierogi(listOf("sweet potato", "onion"))

    override fun createStrawberry(): Pierogi = BoiledPierogi(listOf("strawberry", "vanilla extract"))

    override fun createMeat(): Pierogi = BoiledPierogi(listOf("meat", "onion"))

    override fun createCottageCheese(): Pierogi = BoiledPierogi(listOf("cottage cheese"))
}