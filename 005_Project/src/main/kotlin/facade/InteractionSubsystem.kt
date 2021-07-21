package facade

import board.Board
import board.forEachCreature
import board.get
import board.getOccupiedNearbyCells
import visitor.VisitorAttack

class InteractionSubsystem(private val board: Board) {

    fun eachCreatureAttack() {
        board.forEachCreature {
            val occupiedNearbyCells = board.getOccupiedNearbyCells(it.position)
            if (occupiedNearbyCells.isNotEmpty()) {
                val enemyPosition = occupiedNearbyCells.random()
                val enemy = board[enemyPosition]!!
                it.accept(VisitorAttack(enemy))
            }
        }
    }
}