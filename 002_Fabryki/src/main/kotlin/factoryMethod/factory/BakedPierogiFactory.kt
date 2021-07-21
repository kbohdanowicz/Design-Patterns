package factoryMethod.factory

import factoryMethod.pierogi.ClassicBakedPierogi
import factoryMethod.pierogi.CottageCheeseBakedPierogi
import factoryMethod.pierogi.MeatBakedPierogi
import factoryMethod.pierogi.Pierogi
import factoryMethod.pierogi.StrawberryBakedPierogi
import factoryMethod.pierogi.SweetPotatoBakedPierogi

object BakedPierogiFactory : PierogiFactory() {

    override fun createSpecificPierogi(type: String): Pierogi {
        return when(type) {
            "classic" -> ClassicBakedPierogi()
            "sweet potato" -> SweetPotatoBakedPierogi()
            "strawberry" -> StrawberryBakedPierogi()
            "meat" -> MeatBakedPierogi()
            "cottage cheese" -> CottageCheeseBakedPierogi()
            else -> throw Exception("Unknown pierogi type")
        }
    }
}