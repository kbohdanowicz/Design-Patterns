package registerReflection.pierogi.fried

import registerReflection.PierogiFactory

class MeatFriedPierogi : FriedPierogi(listOf("meat", "onion")) {

    companion object {
        init {
            PierogiFactory.register("MeatFriedPierogi", this::class.java.declaringClass)
        }
    }
}