package singleton_per_thread

class PerThreadSingleton private constructor() {

    init {
        println("Created PerThreadSingleton()")
    }

    companion object {

        private val threadLocal = ThreadLocal.withInitial { PerThreadSingleton() }

        fun getInstance(): PerThreadSingleton {
            return threadLocal.get()
        }
    }
}