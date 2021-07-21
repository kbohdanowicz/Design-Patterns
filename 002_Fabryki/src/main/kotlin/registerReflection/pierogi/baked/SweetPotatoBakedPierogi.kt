package registerReflection.pierogi.baked

import registerReflection.PierogiFactory

class SweetPotatoBakedPierogi : BakedPierogi(listOf("sweet potato", "onion")) {

    companion object {
        init {
            PierogiFactory.register("SweetPotatoBakedPierogi", this::class.java.declaringClass)
        }
    }
}