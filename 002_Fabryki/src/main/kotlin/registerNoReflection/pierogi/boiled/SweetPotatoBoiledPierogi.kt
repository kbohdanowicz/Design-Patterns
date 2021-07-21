package registerNoReflection.pierogi.boiled

import registerNoReflection.PierogiFactory

class SweetPotatoBoiledPierogi : BoiledPierogi(listOf("sweet potato", "onion")) {

    companion object {
        init {
            PierogiFactory.register("SweetPotatoBoiledPierogi") { SweetPotatoBoiledPierogi() }
        }
    }
}