package factory

import enums.Guild
import misc.Position
import creature.*
import observer.Subject

abstract class CreatureFactory(val guild: Guild) : Subject() {

    protected val human: Human = Human(guild)

    protected val dwarf: Dwarf = Dwarf(guild)

    protected val elf: Elf = Elf(guild)

    protected val orc: Orc = Orc(guild)

    protected val drakken: Drakken = Drakken(guild)

    fun createRandomCreature(): Creature {
        return when ((1..5).random()) {
            1 -> human.copy()
            2 -> dwarf.copy()
            3 -> elf.copy()
            4 -> orc.copy()
            else -> drakken.copy()
        }
    }

    abstract fun createHuman(position: Position = 0 to 0): Human

    abstract fun createDwarf(position: Position = 0 to 0): Dwarf

    abstract fun createElf(position: Position = 0 to 0): Elf

    abstract fun createOrc(position: Position = 0 to 0): Orc

    abstract fun createDrakken(position: Position = 0 to 0): Drakken
}