package creature

import enums.Guild
import misc.Position

class Dwarf(guild: Guild, position: Position = 0 to 0)
    : Creature(13, 8, 9, guild, position) {

    override fun copy(position: Position): Creature {
        return (this.clone() as Creature).also { it.position = position }
    }
}