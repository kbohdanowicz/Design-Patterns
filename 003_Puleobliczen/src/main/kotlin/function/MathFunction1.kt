package function

import createDoubleMatrix
import mapInPlace
import kotlin.math.*
import kotlin.random.Random

class MathFunction1 : BaseMathFunction() {

    override fun calculate(args: MathFuncArgs) {
        with(args) {
            matrix = createDoubleMatrix(matrixSize) { it.toDouble() }
            matrix!!.mapInPlace {
                val randomExp = Random.nextDouble(expFrom, expUntil)
                sqrt((it * multiplier).pow(randomExp))
            }
        }
    }
}