package enums

import factory.*

enum class Guild(
    private val _attackBonus: IntRange,
    private val _dexterityBonus: IntRange,
    private val _magickaBonus: IntRange
) {
    PEASANT(Bonus.LOW(), Bonus.LOW(), Bonus.LOW()),
    WARRIOR(Bonus.HIGH(), Bonus.MEDIUM(), Bonus.LOW()),
    ROGUE(Bonus.MEDIUM(), Bonus.HIGH(), Bonus.LOW()),
    MAGE(Bonus.LOW(), Bonus.MEDIUM(), Bonus.HIGH());

    val attackBonus: Int
        get() = _attackBonus.random()

    val dexterityBonus: Int
        get() = _dexterityBonus.random()

    val magickaBonus: Int
        get() = _magickaBonus.random()

    val factory: CreatureFactory
        get() = when(this) {
            PEASANT -> PeasantFactory
            WARRIOR -> WarriorFactory
            ROGUE -> RogueFactory
            MAGE -> MageFactory
        }
}