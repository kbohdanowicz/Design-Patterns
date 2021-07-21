package registerNoReflection.pierogi.baked

import registerNoReflection.PierogiFactory

class MeatBakedPierogi : BakedPierogi(listOf("meat", "onion")) {

    companion object {
        init {
            PierogiFactory.register("MeatBakedPierogi") { MeatBakedPierogi() }
        }
    }
}