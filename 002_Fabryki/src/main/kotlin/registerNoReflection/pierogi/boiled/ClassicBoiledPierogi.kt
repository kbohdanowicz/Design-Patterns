package registerNoReflection.pierogi.boiled

import registerNoReflection.PierogiFactory

class ClassicBoiledPierogi : BoiledPierogi(listOf("potato", "onion")) {

    companion object {
        init {
            PierogiFactory.register("ClassicBoiledPierogi") { ClassicBoiledPierogi() }
        }
    }
}