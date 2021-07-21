package registerNoReflection.pierogi.baked

import registerNoReflection.PierogiFactory

class StrawberryBakedPierogi : BakedPierogi(listOf("strawberry", "vanilla extract")) {

    companion object {
        init {
            PierogiFactory.register("StrawberryBakedPierogi") { StrawberryBakedPierogi() }
        }
    }
}