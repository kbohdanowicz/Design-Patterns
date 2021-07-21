package serialization

import java.io.Serializable

class SerializableSingleton private constructor() : Serializable {

    init {
        println("Created SerializableSingleton()")
    }

    var number = 1
    var string = "test_1"

    private fun readResolve(): Any {
        update(this)
        return getInstance()
    }

    private fun update(deserialized: SerializableSingleton) {
        getInstance().also {
            it.number = deserialized.number
            it.string = deserialized.string
        }
    }

    companion object {

        private var instance: SerializableSingleton? = null

        fun getInstance(): SerializableSingleton {
            if (instance == null) {
                instance = SerializableSingleton()
            }
            return instance as SerializableSingleton
        }
    }
}