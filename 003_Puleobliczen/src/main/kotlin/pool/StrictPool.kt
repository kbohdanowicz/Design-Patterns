@file:Suppress("UNCHECKED_CAST")

package pool

import function.PooledObject
import java.util.*

class StrictPool<T : PooledObject>(
    minCount: Int,
    private val maxCount: Int,
    objGenerator: () -> T
) : MyObjectPool<T> {

    private class MyLock : Object()

    private val reservedResource = objGenerator()

    private val available = LinkedList<T>()

    private val inUse = LinkedList<T>()

    private val waitingLocks = LinkedList<MyLock>()

    private val availableCount: Int
        get() = available.size

    private val inUseCount: Int
        get() = inUse.size

    override val count: Int
        get() = availableCount + inUseCount

    init {
        repeat(minCount) {
            available.add(reservedResource.deepCopy() as T)
        }
    }

    private fun waitForAvailable() {
        val lock = MyLock()
        synchronized(waitingLocks) {
            waitingLocks.add(lock)
        }
        synchronized(lock) {
            lock.wait()
        }
    }

    override fun get(): T {
        if (availableCount == 0 && count == maxCount) {
            waitForAvailable()
        }

        return synchronized(available) {
            if (available.isNotEmpty()) {
                available.first.also {
                    inUse.add(it)
                    available.removeFirst()
                }
            } else {
                (reservedResource.deepCopy() as T).also {
                    inUse.add(it)
                }
            }
        }
    }

    override fun release(obj: T) {
        obj.cleanUp()

        synchronized(available) {
            available.add(obj)
            inUse.remove(obj)

            if (waitingLocks.isNotEmpty()) {
                val lock = waitingLocks.firstOrNull()
                lock?.let {
                    synchronized(it) {
                        it.notify()
                        waitingLocks.removeFirst()
                    }
                }
            }
        }
    }
}
