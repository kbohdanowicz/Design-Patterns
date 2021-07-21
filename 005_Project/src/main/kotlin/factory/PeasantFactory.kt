package factory

import misc.Position
import creature.*
import enums.Guild

object PeasantFactory : CreatureFactory(Guild.PEASANT)  {

    override fun createHuman(position: Position): Human = human.copy(position) as Human

    override fun createDwarf(position: Position): Dwarf = dwarf.copy(position) as Dwarf

    override fun createElf(position: Position): Elf = elf.copy(position) as Elf

    override fun createOrc(position: Position): Orc = orc.copy(position) as Orc

    override fun createDrakken(position: Position): Drakken = drakken.copy(position) as Drakken
}