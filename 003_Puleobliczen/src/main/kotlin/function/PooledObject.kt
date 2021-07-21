package function

interface PooledObject {
    fun cleanUp()
    fun deepCopy(): PooledObject
}