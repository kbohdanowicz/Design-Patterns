fun <T> Array<T>.mapInPlace(transform: (T) -> T) {
    for ((idx, item) in this.withIndex()) {
        this[idx] = transform(item)
    }
}

/*
suspend fun <A, B> Iterable<A>.parallelMap(transform: suspend (A) -> B): List<B> {
    return coroutineScope {
        map { async { transform(it) } }.awaitAll()
    }
}

suspend fun `parallel map Test`() {
    val pTime = measureTimeMillis {
        (1..100).parallelMap {
            delay(1000)
            it * 2
        }
    }
    val time = measureTimeMillis {
        (1..100).map {
            delay(1)
            it * 2
        }
    }
    println("pTime: $pTime, time: $time")
}
 */