@file:Suppress("UNCHECKED_CAST")

package pool

import function.PooledObject
import java.util.concurrent.*

class LoosePool<T : PooledObject>(
    minCount: Int,
    maxCount: Int,
    validationInterval: Long,
    objGenerator: () -> T,
) : MyObjectPool<T> {

    private val reservedResource = objGenerator()

    private val pool = ConcurrentLinkedQueue<T>()

    private val executorService = Executors.newSingleThreadScheduledExecutor()

    override val count: Int
        get() = pool.size

    init {
        repeat(minCount) {
            pool.add(reservedResource.deepCopy() as T)
        }

        executorService.scheduleWithFixedDelay({
            val size = pool.size
            if (size < minCount) {
                val countToBeAdded = size + minCount
                repeat(countToBeAdded) {
                    pool.add(reservedResource.deepCopy() as T)
                }
            } else if (size > maxCount) {
                val countToBeRemoved = size - maxCount
                repeat(countToBeRemoved) {
                    pool.poll()
                }
            }
        }, validationInterval, validationInterval, TimeUnit.MILLISECONDS)
    }

    override fun get(): T = pool.poll() ?: reservedResource.deepCopy() as T

    override fun release(obj: T) {
        pool.add(obj.apply { cleanUp() })
    }
}