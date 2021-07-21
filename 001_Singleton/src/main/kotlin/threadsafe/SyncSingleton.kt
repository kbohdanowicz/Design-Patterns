package threadsafe

class SyncSingleton private constructor() {

    init {
        println("Created SyncSingleton()")
    }

    companion object {

        private var instance: SyncSingleton? = null

        @Synchronized
        fun getInstance(): SyncSingleton {
            if (instance == null) {
                instance = SyncSingleton()
            }
            return instance as SyncSingleton
        }
    }
}