package registerNoReflection.pierogi.baked

import registerNoReflection.PierogiFactory

class SweetPotatoBakedPierogi : BakedPierogi(listOf("sweet potato", "onion")) {

    companion object {
        init {
            PierogiFactory.register("SweetPotatoBakedPierogi") { SweetPotatoBakedPierogi() }
        }
    }
}