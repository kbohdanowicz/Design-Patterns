package facade

import board.Board
import board.clearHalfOfCreaturesRandomly
import board.remove
import creature.Creature
import observer.stopObserving

class DeletionSubsystem(
    private val world: World,
    private val board: Board,
    private val creatures: MutableList<Creature>
) {

    fun remove(creature: Creature) {
        world.stopObserving(creature)
        board.remove(creature)
        creatures.remove(creature)
    }

    fun removeHalfCreaturesRandomly() {
        val removedPositions = board.clearHalfOfCreaturesRandomly()
        val creaturesToRemove = creatures.filter { it.position in removedPositions }
        creatures.removeAll(creaturesToRemove)
        for (creature in creaturesToRemove) {
            world.stopObserving(creature)
        }
    }
}