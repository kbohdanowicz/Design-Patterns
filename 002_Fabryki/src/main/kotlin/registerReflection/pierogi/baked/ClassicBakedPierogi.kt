package registerReflection.pierogi.baked

import registerReflection.PierogiFactory

class ClassicBakedPierogi : BakedPierogi(listOf("potato", "onion")) {

    companion object {
        init {
            PierogiFactory.register("ClassicBakedPierogi", this::class.java.declaringClass)
        }
    }
}