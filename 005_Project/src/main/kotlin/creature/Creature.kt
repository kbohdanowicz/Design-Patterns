package creature

import enums.Guild
import misc.Position
import observer.Subject
import visitor.Element
import visitor.Visitor

abstract class Creature(
    val strength: Int,
    val dexterity: Int,
    val magicka: Int,
    var guild: Guild,
    var position: Position
) : Subject(), Element, Cloneable {

    override fun accept(visitor: Visitor) {
        visitor.visit(this)
    }

    abstract fun copy(position: Position = 0 to 0): Creature
}