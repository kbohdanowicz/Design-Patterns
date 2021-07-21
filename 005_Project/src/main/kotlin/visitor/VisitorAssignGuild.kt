package visitor

import enums.Guild
import creature.Creature

class VisitorAssignGuild(private val guild: Guild) : Visitor {

    override fun visit(element: Element) {
        (element as Creature).guild = guild
    }
}