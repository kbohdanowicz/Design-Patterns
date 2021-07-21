package creature

import enums.Guild
import misc.Position

class Human(guild: Guild, position: Position = 0 to 0)
    : Creature(10, 10, 10, guild, position) {

    override fun copy(position: Position): Creature {
        return (this.clone() as Creature).also { it.position = position }
    }
}