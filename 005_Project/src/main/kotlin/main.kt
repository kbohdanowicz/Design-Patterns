import factory.WarriorFactory
import facade.World
import factory.MageFactory
import factory.PeasantFactory
import factory.RogueFactory
import kweb.Kweb
import kweb.li
import kweb.ul
import misc.Tracker
import observer.observe

fun main() {

    val boardSize = 7
    val turns = 3

    val peasants = List(1) { PeasantFactory.createHuman() }
    val warriors = List(2) { WarriorFactory.createHuman() }
    val rogues = List(2) { RogueFactory.createHuman() }
    val mages = List(2) { MageFactory.createHuman() }
    val initialCreatures = peasants + warriors + rogues + mages

    val tracker = Tracker()
    for (creature in initialCreatures) {
        tracker.track(creature)
    }

    val world = World(boardSize, turns, initialCreatures)
    tracker.observe(world)

    world.show(tracker)
    world.start()
    println()
    world.show(tracker)

    println()
    for ((key, value) in tracker.positionListsByCreature) {
        val idx = tracker.positionListsByCreature.keys.indexOf(key) + 1
        println("$idx -> $value")
    }
}
