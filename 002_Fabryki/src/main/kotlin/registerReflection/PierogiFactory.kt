package registerReflection

import registerReflection.pierogi.Pierogi
import registerReflection.pierogi.baked.*
import registerReflection.pierogi.boiled.*
import registerReflection.pierogi.fried.*

object PierogiFactory {

    private val registeredPierogis = mutableMapOf<String, Class<*>>()

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

    fun register(pierogiID: String, pierogiClass: Class<*>) {
        registeredPierogis[pierogiID] = pierogiClass
    }

    fun create(productID: String): Pierogi {
        val pierogiClass = registeredPierogis[productID] as Class<*>
        val pierogiConstructor = pierogiClass.declaredConstructors[0]
        return pierogiConstructor.newInstance() as Pierogi
    }
}