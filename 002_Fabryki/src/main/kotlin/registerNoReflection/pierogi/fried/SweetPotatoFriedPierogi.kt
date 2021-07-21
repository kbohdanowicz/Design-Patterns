package registerNoReflection.pierogi.fried

import registerNoReflection.PierogiFactory

class SweetPotatoFriedPierogi : FriedPierogi(listOf("sweet potato", "onion")) {

    companion object {
        init {
            PierogiFactory.register("SweetPotatoFriedPierogi") { SweetPotatoFriedPierogi() }
        }
    }
}