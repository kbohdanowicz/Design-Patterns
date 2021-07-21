import board.*
import creature.Human
import enums.Guild
import misc.Position
import org.junit.jupiter.api.Test

internal class BoardTest {

    private fun getMockHuman(position: Position = 0 to 0): Human = Human(Guild.PEASANT,position)

    //[[1, 1, 1],
    // [0, 0, 0],
    // [1, 1, 1]]

    private fun getMockBoard(): Board {
        return createBoard(3) { x, y ->
            if (x % 2 == 0) {
                getMockHuman(x to y)
            } else {
                null
            }
        }
    }

    @Test
    fun `test createBoard()`() {
        val size = 3
        val board = createBoard(size) { x, y ->
            if (x % 2 == 0) {
                getMockHuman(x to y)
            } else {
                null
            }
        }
        println(board.map { it.toList() }.toList())
        assert(board.size == size && board[0].size == size )
    }

    @Test
    fun `test boardSize`() {
        val board = getMockBoard()
        assert(board.boardSize == board.size + board[0].size)
    }

    @Test
    fun `test moveCreatureTo()`() {
        val board = getMockBoard()
        val creature = board[0 to 0]!!
        board.moveCreatureTo(creature, 2 to 2)
        assert(board[0 to 0] == null)
        assert(board[2 to 2] == creature)
    }

    @Test
    fun `test assign()`() {
        val (board, human) = getMockBoard() to getMockHuman()
        board.assignAt(human, human.position)
        assert(board[human.position] == human)
    }

    @Test
    fun `test remove()`() {
        val board = getMockBoard()
        board.remove(board[0 to 0]!!)
        assert(board[0 to 0] == null)
    }

    @Test
    fun `test clearHalfOfCellsRandomly()`() {
        val board = getMockBoard()
        val creaturesCountBefore = board.creatureCount
        board.clearHalfOfCreaturesRandomly()
        val creaturesCountAfter = board.creatureCount
        assert(creaturesCountBefore == creaturesCountAfter * 2)
    }

    @Test
    fun `test moveCreatureToRandomNearbyCell()`() {
        val board = getMockBoard()
        val creature = board[0 to 0]!!
        val positionBefore = creature.position
        val availableNearbyCells = board.getAvailableNearbyCells(positionBefore)
        board.moveCreatureToRandomNearbyCell(creature)
        assert(creature.position in availableNearbyCells)
        assert(creature.position != positionBefore)
    }

    @Test
    fun `test getAvailableNearbyCells()`() {
        val board = getMockBoard()
        val position = board[0 to 0]!!.position
        val expectedAvailableCells = listOf(1 to 0, 1 to 1)
        val availableCells = board.getAvailableNearbyCells(position)
        assert(availableCells == expectedAvailableCells)
    }


    @Test
    fun `test getOccupiedNearbyCells()`() {
        val board = getMockBoard()
        val position = board[0 to 0]!!.position
        val expectedOccupiedCells = listOf(0 to 1)
        val occupiedCells = board.getOccupiedNearbyCells(position)
        assert(occupiedCells == expectedOccupiedCells)
    }

    @Test
    fun `test getNearbyCells()`() {
        val board = getMockBoard()
        val position = board[0 to 0]!!.position
        val expectedNearbyCells = listOf(0 to 1, 1 to 0, 1 to 1)
        val nearbyCells2 = board.getNearbyCells(position)
        assert(nearbyCells2 == expectedNearbyCells)
    }
}