package registerReflection.pierogi.baked

import registerReflection.PierogiFactory

class MeatBakedPierogi : BakedPierogi(listOf("meat", "onion")) {

    companion object {
        init {
            PierogiFactory.register("MeatBakedPierogi", this::class.java.declaringClass)
        }
    }
}