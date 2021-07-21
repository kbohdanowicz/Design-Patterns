package registerReflection.pierogi.boiled

import registerReflection.PierogiFactory

class MeatBoiledPierogi : BoiledPierogi(listOf("meat", "onion")) {

    companion object {
        init {
            PierogiFactory.register("MeatBoiledPierogi", this::class.java.declaringClass)
        }
    }
}