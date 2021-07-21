package registerNoReflection.pierogi.fried

import registerNoReflection.PierogiFactory

class MeatFriedPierogi : FriedPierogi(listOf("meat", "onion")) {

    companion object {
        init {
            PierogiFactory.register("MeatFriedPierogi") { MeatFriedPierogi() }
        }
    }
}