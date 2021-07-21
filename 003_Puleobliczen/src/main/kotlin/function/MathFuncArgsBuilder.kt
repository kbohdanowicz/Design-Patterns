package function

class MathFuncArgsBuilder {

    private var matrixSize: Int = 0
    private var multiplier: Double = 1.0
    private var expFrom: Double = 2.0
    private var expUntil: Double = 8.0

    fun withMatrixSize(size: Int): MathFuncArgsBuilder = this.also { matrixSize = size }

    fun withIterations(mult: Double): MathFuncArgsBuilder = this.also { multiplier = mult }

    fun withExpFrom(exp: Double): MathFuncArgsBuilder = this.also { expFrom = exp }

    fun withExpUntil(exp: Double): MathFuncArgsBuilder = this.also { expUntil = exp }

    fun build(): MathFuncArgs = MathFuncArgs(matrixSize, multiplier, expFrom, expUntil)
}