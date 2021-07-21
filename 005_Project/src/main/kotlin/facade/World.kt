@file:Suppress("ObjectPropertyName")

package facade

import misc.Tracker
import board.*
import creature.Creature
import observer.Observer
import observer.Subject

@Suppress("ReplaceArrayEqualityOpWithArraysEquals")
class World(
    boardSize: Int,
    private val turns: Int,
    initialCreatures: List<Creature>
) : Subject(), Observer {

    private val board = createEmptyBoard(boardSize)

    private val _creatures = mutableListOf<Creature>()

    val creatures: List<Creature>
        get() = _creatures

    private val movementSubsystem = MovementSubsystem(board)

    private val interactionSubsystem = InteractionSubsystem(board)

    private val deletionSubsystem = DeletionSubsystem(this, board, _creatures)

    private val additionSubsystem = AdditionSubsystem(this, board, _creatures)

    init {
        for (creature in initialCreatures) {
            additionSubsystem.add(creature)
        }
    }

    fun start() {
        for (turn in 1..turns) {
            balanceSelf()
            movementSubsystem.moveEachCreatureRandomly()
            //interactionSubsystem.eachCreatureAttack()
        }
    }

    private fun balanceSelf() {
        if (creatures.size > board.boardSize * 0.7) {
            deletionSubsystem.removeHalfCreaturesRandomly()
        }
        val countsByGuild =
            creatures
                .groupBy { it.guild }
                .map { (guild, creatures) -> guild to creatures.size }

        for ((guild, count) in countsByGuild) {
            if (count <= 1) {
                val randomCreature = guild.factory.createRandomCreature()
                additionSubsystem.add(randomCreature)
                notifyObservers()
            }
        }
    }

    override fun update(subject: Subject) {
        when (subject) {
            is Creature -> {
                deletionSubsystem.remove(subject)
            }
        }
    }

    fun show(tracker: Tracker) {
        println(board.map { row ->
            val prefix = if (row == board[0]) "[" else "\n ["
            val mappedArr = row.map { creature ->
                if (creature is Creature) {
                    (tracker.positionListsByCreature.keys.indexOf(creature) + 1).toString()
                } else {
                    "_"
                }
            }
            mappedArr.joinToString(",", prefix, "]")
        }.toList())
    }
}