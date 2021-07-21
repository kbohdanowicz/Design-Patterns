package registerNoReflection.pierogi.fried

import registerNoReflection.PierogiFactory

class StrawberryFriedPierogi : FriedPierogi(listOf("strawberry", "vanilla extract")) {

    companion object {
        init {
            PierogiFactory.register("StrawberryFriedPierogi") { StrawberryFriedPierogi() }
        }
    }
}