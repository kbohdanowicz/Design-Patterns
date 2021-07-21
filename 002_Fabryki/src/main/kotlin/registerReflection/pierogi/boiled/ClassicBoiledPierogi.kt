package registerReflection.pierogi.boiled

import registerReflection.PierogiFactory

class ClassicBoiledPierogi : BoiledPierogi(listOf("potato", "onion")) {

    companion object {
        init {
            PierogiFactory.register("ClassicBoiledPierogi", this::class.java.declaringClass)
        }
    }
}