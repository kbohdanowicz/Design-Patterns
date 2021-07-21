package registerReflection.pierogi.fried

import registerReflection.PierogiFactory

class SweetPotatoFriedPierogi : FriedPierogi(listOf("sweet potato", "onion")) {

    companion object {
        init {
            PierogiFactory.register("SweetPotatoFriedPierogi", this::class.java.declaringClass)
        }
    }
}