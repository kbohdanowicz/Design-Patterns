package registerReflection.pierogi.boiled

import registerReflection.PierogiFactory

class CottageCheeseBoiledPierogi : BoiledPierogi(listOf("cottage cheese")) {

    companion object {
        init {
            PierogiFactory.register("CottageCheeseBoiledPierogi", this::class.java.declaringClass)
        }
    }
}