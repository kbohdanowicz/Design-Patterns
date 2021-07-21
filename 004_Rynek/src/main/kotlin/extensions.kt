@file:Suppress("LocalVariableName")

import observer.Observer
import observer.Subject
import kotlin.random.Random

fun <T> Collection<T>.without(item: T): List<T> {
    return this.toMutableList().also { it.remove(item) }.toList()
}

infix fun Observer.observe(subject: Subject) {
    subject.attachObserver(this)
}

fun <T> Collection<T>.randomSubList(minSize: Int = 1, maxSize: Int = this.size - 1): List<T> {
    val _maxSize = if (maxSize == this.size - 1) maxSize + 1 else maxSize
    val randomSize = Random.nextInt(minSize, _maxSize)
    val tempMutList = this.toMutableList()
    return List(randomSize) { _ ->
        tempMutList.random().also {
            tempMutList.remove(it)
        }
    }
}