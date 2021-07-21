package singleton_per_thread

import java.util.*

class ThreadTest : Thread() {

    var action: () -> Unit = { }

    val threadName: String
        get() = "[${Thread.currentThread().name}]: "

    override fun run() {
        action()
    }

    fun sleep(min: Int, max: Int) {
        try {
            val time = Random().nextInt(max - min + 1) + min
            sleep(time.toLong())
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
    }
}