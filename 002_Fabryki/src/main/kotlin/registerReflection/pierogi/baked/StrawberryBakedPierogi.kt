package registerReflection.pierogi.baked

import registerReflection.PierogiFactory

class StrawberryBakedPierogi : BakedPierogi(listOf("strawberry", "vanilla extract")) {

    companion object {
        init {
            PierogiFactory.register("StrawberryBakedPierogi", this::class.java.declaringClass)
        }
    }
}