package factoryMethod.factory

import factoryMethod.pierogi.ClassicFriedPierogi
import factoryMethod.pierogi.CottageCheeseFriedPierogi
import factoryMethod.pierogi.MeatFriedPierogi
import factoryMethod.pierogi.Pierogi
import factoryMethod.pierogi.StrawberryFriedPierogi
import factoryMethod.pierogi.SweetPotatoFriedPierogi

object FriedPierogiFactory : PierogiFactory() {

    override fun createSpecificPierogi(type: String): Pierogi {
        return when(type) {
            "classic" -> ClassicFriedPierogi()
            "sweet potato" -> SweetPotatoFriedPierogi()
            "strawberry" -> StrawberryFriedPierogi()
            "meat" -> MeatFriedPierogi()
            "cottage cheese" -> CottageCheeseFriedPierogi()
            else -> throw Exception("Unknown pierogi type")
        }
    }
}