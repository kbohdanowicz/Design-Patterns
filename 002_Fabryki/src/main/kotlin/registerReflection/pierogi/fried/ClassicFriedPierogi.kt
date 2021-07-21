package registerReflection.pierogi.fried

import registerReflection.PierogiFactory

class ClassicFriedPierogi : FriedPierogi(listOf("potato", "onion")) {

    companion object {
        init {
            PierogiFactory.register("ClassicFriedPierogi", this::class.java.declaringClass)
        }
    }
}