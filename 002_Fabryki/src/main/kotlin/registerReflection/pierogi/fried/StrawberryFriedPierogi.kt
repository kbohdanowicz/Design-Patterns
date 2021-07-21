package registerReflection.pierogi.fried

import registerReflection.PierogiFactory

class StrawberryFriedPierogi : FriedPierogi(listOf("strawberry", "vanilla extract")) {

    companion object {
        init {
            PierogiFactory.register("StrawberryFriedPierogi", this::class.java.declaringClass)
        }
    }
}