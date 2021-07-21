package facade

import board.*

class MovementSubsystem(private val board: Board) {

    fun moveEachCreatureRandomly() {
        board.forEachCreature {
            board.moveCreatureToRandomNearbyCell(it)
        }
    }
}