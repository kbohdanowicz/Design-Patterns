import board.crossProduct
import board.randomSubList
import org.junit.jupiter.api.Test

class ExtensionsTest {

    @Test
    fun `test crossProduct()`() {
        val list2d = List(3) { List(3) { it } }
        val left = list2d.indices
        val right = list2d[0].indices
        val result = listOf(
            0 to 0, 0 to 1, 0 to 2,
            1 to 0, 1 to 1, 1 to 2,
            2 to 0, 2 to 1, 2 to 2,
        )
        assert(left.crossProduct(right) == result)
    }

    @Test
    fun `test randomSubList()`() {
        val list = List(5) { it }
        val (minSize, maxSize) = 2 to 3
        val randomSubList = list.randomSubList(minSize, maxSize)
        assert(randomSubList.size in minSize..maxSize )
    }
}