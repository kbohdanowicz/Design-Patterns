package function

import DoubleMatrix
import deepCopy
import setDefaultValues

abstract class BaseMathFunction : PooledObject, Cloneable {

    var matrix: DoubleMatrix? = null

    abstract fun calculate(args: MathFuncArgs)

    override fun cleanUp() {
        matrix?.setDefaultValues()
    }

    override fun deepCopy(): BaseMathFunction {
        val clone = this.clone() as BaseMathFunction
        clone.matrix = matrix?.deepCopy()
        return clone
    }
}