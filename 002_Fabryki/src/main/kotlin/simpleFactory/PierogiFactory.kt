package simpleFactory

import simpleFactory.pierogi.*

object PierogiFactory {

    fun create(type: String): Pierogi {
        return when(type) {
            "classic boiled" -> ClassicBoiledPierogi()
            "sweet potato boiled" -> SweetPotatoBoiledPierogi()
            "strawberry boiled" -> StrawberryBoiledPierogi()
            "meat boiled" -> MeatBoiledPierogi()
            "cottage cheese boiled" -> CottageCheeseBoiledPierogi()

            "classic baked" -> ClassicBakedPierogi()
            "sweet potato baked" -> SweetPotatoBakedPierogi()
            "strawberry baked" -> StrawberryBakedPierogi()
            "meat baked" -> MeatBakedPierogi()
            "cottage cheese baked" -> CottageCheeseBakedPierogi()

            "classic fried" -> ClassicFriedPierogi()
            "sweet potato fried" -> SweetPotatoFriedPierogi()
            "strawberry fried" -> StrawberryFriedPierogi()
            "meat fried" -> MeatFriedPierogi()
            "cottage cheese fried" -> CottageCheeseFriedPierogi()

            else -> throw Exception("Unknown type of pierogi")
        }
    }
}