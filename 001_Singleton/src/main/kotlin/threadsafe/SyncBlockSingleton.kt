package threadsafe

class SyncBlockSingleton private constructor() {

    init {
        println("Created SyncBlockSingleton()")
    }

    companion object {

        private var instance: SyncBlockSingleton? = null

        fun getInstance(): SyncBlockSingleton {
            if (instance == null) {
                synchronized(SyncBlockSingleton::class.java) {
                    if (instance == null) {
                        instance = SyncBlockSingleton()
                    }
                }
            }
            return instance as SyncBlockSingleton
        }
    }
}