package function

import DoubleMatrix
import createDoubleMatrix
import mapInPlace
import kotlin.math.*
import kotlin.random.Random

class MathFunction3 : BaseMathFunction() {

    override fun calculate(args: MathFuncArgs) {
        with(args) {
            matrix = createDoubleMatrix(matrixSize) { it.toDouble() }
            matrix!!.mapInPlace {
                val randomExp = Random.nextDouble(expFrom, expUntil)
                tan((it * multiplier).pow(randomExp))
            }
        }
    }
}