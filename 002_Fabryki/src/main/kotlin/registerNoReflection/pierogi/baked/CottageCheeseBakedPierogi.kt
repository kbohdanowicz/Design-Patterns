package registerNoReflection.pierogi.baked

import registerNoReflection.PierogiFactory

class CottageCheeseBakedPierogi : BakedPierogi(listOf("cottage cheese")) {

    companion object {
        init {
            PierogiFactory.register("CottageCheeseBakedPierogi") { CottageCheeseBakedPierogi() }
        }
    }
}