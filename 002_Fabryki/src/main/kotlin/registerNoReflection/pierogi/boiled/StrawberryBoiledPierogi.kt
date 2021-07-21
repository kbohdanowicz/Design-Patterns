package registerNoReflection.pierogi.boiled

import registerNoReflection.PierogiFactory

class StrawberryBoiledPierogi : BoiledPierogi(listOf("strawberry", "vanilla extract")) {

    companion object {
        init {
            PierogiFactory.register("StrawberryBoiledPierogi") { StrawberryBoiledPierogi() }
        }
    }
}