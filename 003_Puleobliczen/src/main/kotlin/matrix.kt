typealias DoubleMatrix = Array<Array<Double>>

fun createDoubleMatrix(size: Int, init: (Int) -> Double): DoubleMatrix {
    return Array(size) { Array(size) { init(it) } }
}

fun DoubleMatrix.setDefaultValues() {
    val tempMatrix = createDoubleMatrix(this.size) { it.toDouble() }
    for ((rowIdx, row) in tempMatrix.withIndex()) {
        for ((idx, item) in row.withIndex()) {
            this[rowIdx][idx] = item
        }
    }
}

fun DoubleMatrix.mapInPlace(transform: (Double) -> Double) {
    for (row in this) {
        row.mapInPlace { transform(it) }
    }
}

fun DoubleMatrix.deepCopy(): DoubleMatrix {
    return this.map { row -> row.toList().toTypedArray() }.toTypedArray()
}
