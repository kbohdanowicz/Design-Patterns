package function

import DoubleMatrix
import createDoubleMatrix
import mapInPlace
import kotlin.math.*
import kotlin.random.Random

class MathFunction2 : BaseMathFunction() {

    override fun calculate(args: MathFuncArgs) {
        with(args) {
            matrix = createDoubleMatrix(matrixSize) { it.toDouble() }
            matrix!!.mapInPlace {
                val randomExp = Random.nextDouble(expFrom, expUntil)
                ceil((it * multiplier).pow(randomExp))
            }
        }
    }
}