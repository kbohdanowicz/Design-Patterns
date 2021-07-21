package registerReflection.pierogi.boiled

import registerReflection.PierogiFactory

class SweetPotatoBoiledPierogi : BoiledPierogi(listOf("sweet potato", "onion")) {

    companion object {
        init {
            PierogiFactory.register("SweetPotatoBoiledPierogi", this::class.java.declaringClass)
        }
    }
}