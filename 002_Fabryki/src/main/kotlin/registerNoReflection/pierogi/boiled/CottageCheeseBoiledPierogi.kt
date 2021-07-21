package registerNoReflection.pierogi.boiled

import registerNoReflection.PierogiFactory

class CottageCheeseBoiledPierogi : BoiledPierogi(listOf("cottage cheese")) {

    companion object {
        init {
            PierogiFactory.register("CottageCheeseBoiledPierogi") { CottageCheeseBoiledPierogi() }
        }
    }
}