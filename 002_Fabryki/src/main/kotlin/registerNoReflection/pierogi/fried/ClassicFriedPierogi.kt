package registerNoReflection.pierogi.fried

import registerNoReflection.PierogiFactory

class ClassicFriedPierogi : FriedPierogi(listOf("potato", "onion")) {

    companion object {
        init {
            PierogiFactory.register("ClassicFriedPierogi") { ClassicFriedPierogi() }
        }
    }
}