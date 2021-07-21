package abstractFactory.factory

import abstractFactory.pierogi.Pierogi

abstract class PierogiFactory(val cookingMethod: String) {

    abstract fun createClassic(): Pierogi

    abstract fun createSweetPotato(): Pierogi

    abstract fun createStrawberry(): Pierogi

    abstract fun createMeat(): Pierogi

    abstract fun createCottageCheese(): Pierogi
}
