@file:Suppress("UNCHECKED_CAST")

package thread

import function.*
import pool.MyObjectPool

typealias MathFuncPool = MyObjectPool<BaseMathFunction>

class MyThreadBuilder {

    private var mathFuncArgs = MathFuncArgs(2, 1.0, 2.0, 8.0)

    private var mathFunc1PoolAndCount: Pair<MathFuncPool, Int>? = null

    private var mathFunc2PoolAndCount: Pair<MathFuncPool, Int>? = null

    private var mathFunc3PoolAndCount: Pair<MathFuncPool, Int>? = null

    private var objCountArray: Array<Int>? = null

    fun withMathFunc1(pool: MyObjectPool<MathFunction1>, count: Int): MyThreadBuilder {
        return this.also {
            mathFunc1PoolAndCount = (pool as MathFuncPool) to count
        }
    }

    fun withMathFunc2(pool: MyObjectPool<MathFunction2>, count: Int): MyThreadBuilder {
        return this.also {
            mathFunc2PoolAndCount = (pool as MathFuncPool) to count
        }
    }

    fun withMathFunc3(pool: MyObjectPool<MathFunction3>, count: Int): MyThreadBuilder {
        return this.also {
            mathFunc3PoolAndCount = (pool as MathFuncPool) to count
        }
    }

    fun withMathFuncArgs(args: MathFuncArgs): MyThreadBuilder = this.also { mathFuncArgs = args }

    fun withObjectCountArray(arr: Array<Int>): MyThreadBuilder = this.also { objCountArray = arr }

    fun build(): MyThread {
        val poolsWithCount = run {
            val tempList = mutableListOf<Pair<MathFuncPool, Int>>()
            mathFunc1PoolAndCount?.let { tempList.add(it) }
            mathFunc2PoolAndCount?.let { tempList.add(it) }
            mathFunc3PoolAndCount?.let { tempList.add(it) }
            tempList.toList()
        }
        return MyThread(mathFuncArgs, poolsWithCount, objCountArray)
    }
}