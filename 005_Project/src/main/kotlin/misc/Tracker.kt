package misc

import creature.Creature
import facade.World
import observer.Observer
import observer.Subject
import observer.observe

class Tracker : Observer {

    val positionListsByCreature = mutableMapOf<Creature, MutableList<Position>>()

    fun track(creature: Creature) {
        observe(creature)
        positionListsByCreature[creature] = mutableListOf()
    }

    override fun update(subject: Subject) {
        when (subject) {
            is World -> {
                val trackedCreatures = positionListsByCreature.keys
                val notTrackedCreatures = subject.creatures.filterNot { it in trackedCreatures }
                for (creature in notTrackedCreatures) {
                    track(creature)
                }
            }
            is Creature -> {
                positionListsByCreature[subject]?.add(subject.position)
                    ?: throw Exception("Can not add positions, creature is not observed")
            }
        }
    }
}