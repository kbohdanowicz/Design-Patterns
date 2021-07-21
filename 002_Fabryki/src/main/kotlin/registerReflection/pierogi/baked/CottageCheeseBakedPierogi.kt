package registerReflection.pierogi.baked

import registerReflection.PierogiFactory

class CottageCheeseBakedPierogi : BakedPierogi(listOf("cottage cheese")) {

    companion object {
        init {
            PierogiFactory.register("CottageCheeseBakedPierogi", this::class.java.declaringClass)
        }
    }
}