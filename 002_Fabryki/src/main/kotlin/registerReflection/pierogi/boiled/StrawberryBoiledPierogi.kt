package registerReflection.pierogi.boiled

import registerReflection.PierogiFactory

class StrawberryBoiledPierogi : BoiledPierogi(listOf("strawberry", "vanilla extract")) {

    companion object {
        init {
            PierogiFactory.register("StrawberryBoiledPierogi", this::class.java.declaringClass)
        }
    }
}