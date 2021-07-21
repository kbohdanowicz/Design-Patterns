package registerNoReflection.pierogi.boiled

import registerNoReflection.PierogiFactory

class MeatBoiledPierogi : BoiledPierogi(listOf("meat", "onion")) {

    companion object {
        init {
            PierogiFactory.register("MeatBoiledPierogi") { MeatBoiledPierogi() }
        }
    }
}