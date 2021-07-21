@file:Suppress("ReplaceArrayEqualityOpWithArraysEquals")

package board

import misc.Position
import creature.Creature
import decorator.VisitorWithTracking
import visitor.VisitorMovement


typealias Board = Array<Array<Creature?>>

// initializers

fun createEmptyBoard(size: Int): Board = Array(size) { Array(size) { null } }

fun createBoard(size: Int, init: (Int, Int) -> Creature?): Board {
    return Array(size) { x -> Array(size) { y -> init(x, y) } }
}

// operators

operator fun Board.get(position: Position): Creature? = this[position.first][position.second]

operator fun Board.set(position: Position, creature: Creature?) {
    this[position.first][position.second] = creature
}

// properties

val Board.boardSize: Int
    get() = this.size + this[0].size

val Board.randomAvailableCell: Position
    get() = this.getAvailableCells(this.allCells).random()

val Board.allCells: List<Position>
    get() {
        val rowIndices = this.indices.toList()
        val colIndices = this[0].indices.toList()
        return rowIndices crossProduct colIndices
    }

val Board.creatureCount: Int
    get() = this.getOccupiedCells(this.allCells).count()

// functions

fun Board.forEachCreature(func: (Creature) -> Unit) {
    for (row in this) {
        for (creature in row) {
            creature?.let {
                func(creature)
            }
        }
    }
}

fun Board.moveCreatureTo(creature: Creature, position: Position) {
    this.remove(creature)
    this.assignAt(creature, position)
}

fun Board.remove(creature: Creature) {
    this[creature.position] = null
}

fun Board.assignAt(creature: Creature, position: Position) {
    this[position] = creature
    creature.accept(VisitorWithTracking(VisitorMovement(position)))
}

val xd = List(1) {}
fun Board.clearHalfOfCreaturesRandomly(): List<Position> {
    val occupiedCells = getOccupiedCells(this.allCells)
    val randomPositions = occupiedCells.shuffled().take(occupiedCells.size / 2)
    randomPositions.forEach { (x, y) -> this[x][y] = null }
    return randomPositions
}

fun Board.moveCreatureToRandomNearbyCell(creature: Creature) {
    val availableNearbyCells = getAvailableNearbyCells(creature.position)
    if (availableNearbyCells.isNotEmpty()) {
        val randomAvailableNearbyCell = availableNearbyCells.random()
        this.moveCreatureTo(creature, randomAvailableNearbyCell)
    }
}

fun Board.getAvailableNearbyCells(position: Position): List<Position> {
    val nearbyCells = this.getNearbyCells(position)
    return this.getAvailableCells(nearbyCells)
}

fun Board.getAvailableCells(positions: List<Position>): List<Position> {
    return positions.filter { (x, y) -> this[x][y] == null }
}

fun Board.getOccupiedNearbyCells(position: Position): List<Position> {
    val nearbyCells = this.getNearbyCells(position)
    return this.getOccupiedCells(nearbyCells)
}

fun Board.getOccupiedCells(positions: List<Position>): List<Position> {
    return positions.filter { (x, y) -> this[x][y] != null  }
}

fun Board.getNearbyCells(position: Position): List<Position> {
    val (x, y) = position
    val potentialNearbyCells = listOf(
        x - 1 to y - 1,
        x - 1 to y,
        x - 1 to y + 1,
        x to y - 1,
        x to y + 1,
        x + 1 to y - 1,
        x + 1 to y,
        x + 1 to y + 1
    )
    val nearbyCells = potentialNearbyCells.toMutableList()
    for (cell in potentialNearbyCells) {
        try {
            this[cell]
        } catch (e: ArrayIndexOutOfBoundsException) {
            nearbyCells.remove(cell)
        }
    }
    return nearbyCells
}
