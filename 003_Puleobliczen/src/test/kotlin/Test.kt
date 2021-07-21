@file:Suppress("UNCHECKED_CAST")

import function.*
import org.junit.jupiter.api.Test
import pool.LoosePool
import pool.StrictPool
import thread.MyThread
import thread.MyThreadBuilder
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

class Test {
    private val threadCount = List(1000) { it }
    private val mathFuncArgs = MathFuncArgs(300, 5.0, 2.0, 8.0)

    private val objCountArray = Array(threadCount.size) { 0 }

    private val executor = Executors.newFixedThreadPool(threadCount.size)!!

    private fun printResultsWhenFinished() {
        try {
            executor.awaitTermination(300, TimeUnit.SECONDS)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
        while (true) {
            if (executor.isTerminated) {
                with(objCountArray) {
                    println(
                        """
                        ${average()}
                        ${maxOrNull()}
                        """.trimIndent()
                    )
                }
                return
            }
        }
    }

    private fun executeTest(threads: List<MyThread>) {
        threads.forEach {
            executor.execute(it)
        }
        executor.shutdown()

        printResultsWhenFinished()
    }

    private fun getThreadsWithLoosePool(maxCount: Int, validationInterval: Long): List<MyThread> {
        val pool = LoosePool(30, maxCount, validationInterval) { MathFunction1() }
        return List(threadCount.size) {
            MyThreadBuilder()
                .withMathFunc1(pool, 3)
                .withMathFuncArgs(mathFuncArgs)
                .withObjectCountArray(objCountArray)
                .build()
        }
    }

    private fun getThreadsWithStrictPool(maxCount: Int): List<MyThread> {
        val pool = StrictPool(30, maxCount) { MathFunction1() }
        return List(threadCount.size) {
            MyThreadBuilder()
                .withMathFunc1(pool, 3)
                .withMathFuncArgs(mathFuncArgs)
                .withObjectCountArray(objCountArray)
                .build()
        }
    }

    @Test
    fun `loose pool, 100 threads max, validation every 10ms`() {
        val threads = getThreadsWithLoosePool(100, 10L)
        executeTest(threads)
    }

    @Test
    fun `loose pool, 100 threads max, validation every 400ms`() {
        val threads = getThreadsWithLoosePool(100, 400L)
        executeTest(threads)
    }

    @Test
    fun `loose pool, 100 threads max, validation every 1000ms`() {
        val threads = getThreadsWithLoosePool(100, 1000L)
        executeTest(threads)
    }

    @Test
    fun `loose pool, 400 threads max, validation every 10ms`() {
        val threads = getThreadsWithLoosePool(400, 10L)
        executeTest(threads)
    }

    @Test
    fun `loose pool, 400 threads max, validation every 400ms`() {
        val threads = getThreadsWithLoosePool(400, 400L)
        executeTest(threads)
    }

    @Test
    fun `loose pool, 400 threads max, validation every 1000ms`() {
        val threads = getThreadsWithLoosePool(400, 1000L)
        executeTest(threads)
    }

    @Test
    fun `loose pool, 900 threads max, validation every 10ms`() {
        val threads = getThreadsWithLoosePool(900, 10L)
        executeTest(threads)
    }

    @Test
    fun `loose pool, 900 threads max, validation every 400ms`() {
        val threads = getThreadsWithLoosePool(900, 400L)
        executeTest(threads)
    }

    @Test
    fun `loose pool, 900 threads max, validation every 1000ms`() {
        val threads = getThreadsWithLoosePool(900, 1000L)
        executeTest(threads)
    }

    @Test
    fun `strict pool, 100 threads max`() {
        val threads = getThreadsWithStrictPool(100)
        executeTest(threads)
    }

    @Test
    fun `strict pool, 400 threads max`() {
        val threads = getThreadsWithStrictPool(400)
        executeTest(threads)
    }

    @Test
    fun `strict pool, 900 threads max`() {
        val threads = getThreadsWithStrictPool(900)
        executeTest(threads)
    }
}
