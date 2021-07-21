package registerNoReflection.pierogi.baked

import registerNoReflection.PierogiFactory

class ClassicBakedPierogi : BakedPierogi(listOf("potato", "onion")) {

    companion object {
        init {
            PierogiFactory.register("ClassicBakedPierogi") { ClassicBakedPierogi() }
        }
    }
}