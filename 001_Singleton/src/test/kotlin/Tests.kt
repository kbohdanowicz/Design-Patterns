import org.junit.jupiter.api.Test
import serialization.SerializableSingleton
import singleton_per_thread.PerThreadSingleton
import singleton_per_thread.ThreadTest
import threadsafe.SyncBlockSingleton
import threadsafe.SyncSingleton
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.ObjectInputStream
import java.io.ObjectOutputStream

class Tests {

    @Test
    fun `singleton per thread test`() {
        println("\n[Singleton per thread]")
        for (i in 1..3) {
            ThreadTest().apply {
                action = {
                    val instance1 = PerThreadSingleton.getInstance()

                    this.sleep(50, 100)

                    val instance2 = PerThreadSingleton.getInstance()

                    val message = if (instance1 == instance2) "Equal" else "Not equal"
                    println(threadName + message)

                    assert(instance1 == instance2)
                }
            }.start()
        }

        Thread.sleep(1000L)
        println()
    }

    @Test
    fun `serializable singleton test`() {
        println("[Serialization]")
        val singleton = SerializableSingleton.getInstance()

        val serializedNumber = 2
        val serializedString = "test_2"

        singleton.apply {
            number = serializedNumber
            string = serializedString
        }

        val oos = ObjectOutputStream(FileOutputStream("singleton.ser"))
        oos.writeObject(singleton)
        oos.close()

        singleton.apply {
            number = 3
            string = "test_3"
        }

        val osi1 = ObjectInputStream(FileInputStream("singleton.ser"))
        val desSingleton1 = osi1.readObject() as SerializableSingleton
        osi1.close()

        val osi2 = ObjectInputStream(FileInputStream("singleton.ser"))
        val desSingleton2 = osi2.readObject() as SerializableSingleton
        osi2.close()

        println(
            "Deserialized Singleton 1: " +
                "{" +
                " hashcode: ${desSingleton1.hashCode()}," +
                " number: ${desSingleton1.number}," +
                " string: ${desSingleton1.string} " +
                "}"
        )
        println(
            "Deserialized Singleton 2: " +
                "{" +
                " hashcode: ${desSingleton2.hashCode()}," +
                " number: ${desSingleton2.number}," +
                " string: ${desSingleton2.string} " +
                "}"
        )

        assert(desSingleton1.hashCode() == desSingleton2.hashCode())

        assert(desSingleton1.number == serializedNumber)
        assert(desSingleton1.string == serializedString)

        assert(desSingleton2.number == serializedNumber)
        assert(desSingleton2.string == serializedString)

        println()
    }

    @Test
    fun `sync singleton test`() {
        println("[Thread safe]")

        val size = 200_000
        val hashCodes = Array(size) { 0 }

        val threads = List(size) { idx ->
            ThreadTest().apply {
                action = {
                    val hashCode = SyncSingleton.getInstance().hashCode()
                    hashCodes[idx] = hashCode
                }
            }
        }
        threads.forEach { it.start() }

        Thread.sleep(1000L)

        assert(hashCodes[0] != 0)
        assert(hashCodes.all { it == hashCodes[0] })

        println()
    }

    @Test
    fun `sync block singleton test`() {
        println("[Thread safe]")

        val size = 200_000
        val hashCodes = Array(size) { 0 }

        val threads = List(size) { idx ->
            ThreadTest().apply {
                action = {
                    val hashCode = SyncBlockSingleton.getInstance().hashCode()
                    hashCodes[idx] = hashCode
                }
            }
        }
        threads.forEach { it.start() }

        Thread.sleep(1000L)

        assert(hashCodes[0] != 0)
        assert(hashCodes.all { it == hashCodes[0] })

        println()
    }
}