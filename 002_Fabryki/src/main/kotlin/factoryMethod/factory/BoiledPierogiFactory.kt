package factoryMethod.factory

import factoryMethod.pierogi.ClassicBoiledPierogi
import factoryMethod.pierogi.CottageCheeseBoiledPierogi
import factoryMethod.pierogi.MeatBoiledPierogi
import factoryMethod.pierogi.Pierogi
import factoryMethod.pierogi.StrawberryBoiledPierogi
import factoryMethod.pierogi.SweetPotatoBoiledPierogi

object BoiledPierogiFactory : PierogiFactory() {

    override fun createSpecificPierogi(type: String): Pierogi {
        return when(type) {
            "classic" -> ClassicBoiledPierogi()
            "sweet potato" -> SweetPotatoBoiledPierogi()
            "strawberry" -> StrawberryBoiledPierogi()
            "meat" -> MeatBoiledPierogi()
            "cottage cheese" -> CottageCheeseBoiledPierogi()
            else -> throw Exception("Unknown pierogi type")
        }
    }
}