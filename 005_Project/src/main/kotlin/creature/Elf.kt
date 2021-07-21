package creature

import enums.Guild
import misc.Position

class Elf(guild: Guild, position: Position = 0 to 0)
    : Creature(8, 16, 11, guild, position) {

    override fun copy(position: Position): Creature {
        return (this.clone() as Creature).also { it.position = position }
    }
}