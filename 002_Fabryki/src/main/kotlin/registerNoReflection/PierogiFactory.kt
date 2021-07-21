package registerNoReflection

import registerNoReflection.pierogi.Pierogi
import registerNoReflection.pierogi.baked.*
import registerNoReflection.pierogi.boiled.*
import registerNoReflection.pierogi.fried.*

object PierogiFactory {

    private val registeredPierogis = mutableMapOf<String, () -> Pierogi>()

    init {
        ClassicBoiledPierogi
        SweetPotatoBoiledPierogi
        StrawberryBoiledPierogi
        MeatBoiledPierogi
        CottageCheeseBoiledPierogi

        ClassicBakedPierogi
        SweetPotatoBakedPierogi
        StrawberryBakedPierogi
        MeatBakedPierogi
        CottageCheeseBakedPierogi

        ClassicFriedPierogi
        SweetPotatoFriedPierogi
        StrawberryFriedPierogi
        MeatFriedPierogi
        CottageCheeseFriedPierogi
    }

    fun register(pierogiID: String, pierogiCreator: () -> Pierogi) {
        registeredPierogis[pierogiID] = pierogiCreator
    }

    fun create(pierogiID: String): Pierogi {
        return registeredPierogis[pierogiID]?.invoke() ?: throw Exception("Pierogi type not registered")
    }
}