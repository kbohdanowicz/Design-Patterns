package factoryMethod.factory

import factoryMethod.pierogi.Pierogi

abstract class PierogiFactory {

    protected abstract fun createSpecificPierogi(type: String): Pierogi

    fun create(type: String): Pierogi {
        return createSpecificPierogi(type)
    }
}