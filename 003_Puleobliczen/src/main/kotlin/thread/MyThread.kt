package thread

import function.*

class MyThread(
    private val args: MathFuncArgs,
    private val poolsWithCount: List<Pair<MathFuncPool, Int>>,
    private val objCountArray: Array<Int>?
) : Thread() {

    private val threadIdOffset = 21

    override fun run() {
        for ((pool, funcCount) in poolsWithCount) {
            val threadId = id.toInt() - threadIdOffset

            val mathFunctions = List(funcCount) { pool.get() }

            try {
                objCountArray?.set(threadId, pool.count)
            } catch(ex: ArrayIndexOutOfBoundsException) {
                println(ex)
                println(id)
            }

            mathFunctions.forEach { it.calculate(args) }
            mathFunctions.forEach { pool.release(it) }
        }
    }
}