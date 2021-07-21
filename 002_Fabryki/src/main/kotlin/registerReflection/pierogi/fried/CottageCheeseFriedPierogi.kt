package registerReflection.pierogi.fried

import registerReflection.PierogiFactory

class CottageCheeseFriedPierogi : FriedPierogi(listOf("cottage cheese")) {

    companion object {
        init {
            PierogiFactory.register("CottageCheeseFriedPierogi", this::class.java.declaringClass)
        }
    }
}