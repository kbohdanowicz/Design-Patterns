package facade

import board.Board
import board.assignAt
import board.randomAvailableCell
import creature.Creature
import misc.Tracker
import observer.observe

class AdditionSubsystem(
    private val world: World,
    private val board: Board,
    private val creatures: MutableList<Creature>
) {

    fun add(creature: Creature) {
        world.observe(creature)
        board.assignAt(creature, board.randomAvailableCell)
        creatures.add(creature)
    }
}