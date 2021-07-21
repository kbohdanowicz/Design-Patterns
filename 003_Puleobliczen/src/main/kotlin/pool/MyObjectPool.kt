package pool

import function.PooledObject

interface MyObjectPool<T: PooledObject> {
    val count : Int
    fun get(): T
    fun release(obj: T)
}