package registerNoReflection.pierogi.fried

import registerNoReflection.PierogiFactory

class CottageCheeseFriedPierogi : FriedPierogi(listOf("cottage cheese")) {

    companion object {
        init {
            PierogiFactory.register("CottageCheeseFriedPierogi") { CottageCheeseFriedPierogi() }
        }
    }
}