package visitor

import creature.Creature
import enums.Guild
import facade.World

class VisitorAttack(private val enemy: Creature) : Visitor {

    override fun visit(element: Element) {
        val creature = element as Creature
        println("$creature attacks $enemy")
        if (creature.guild == Guild.MAGE) {
            attackPsionic(creature)
        } else {
            attackNormal(creature)
        }
    }

    private fun attackPsionic(attacker: Creature) {
        val attackerMagicka = attacker.magicka + attacker.guild.magickaBonus
        val enemyMagicka = enemy.magicka + enemy.guild.magickaBonus

        val threshold = 0.6
        when {
            attackerMagicka < enemyMagicka -> {
                if (enemyMagicka * threshold > attackerMagicka) {
                    attacker.notifyObservers(attacker.observers.filterIsInstance<World>())
                }
            }
            attackerMagicka > enemyMagicka -> {
                if (attackerMagicka * threshold > enemyMagicka) {
                    enemy.notifyObservers(attacker.observers.filterIsInstance<World>())
                }
            }
        }
    }

    private fun attackNormal(attacker: Creature) {
        val attackerStrength = attacker.strength + attacker.guild.attackBonus
        val enemyStrength = enemy.strength + enemy.guild.attackBonus

        when {
            attackerStrength < enemyStrength -> {
                attacker.notifyObservers(attacker.observers.filterIsInstance<World>())
            }
            attackerStrength > enemyStrength -> {
                enemy.notifyObservers(attacker.observers.filterIsInstance<World>())
            }
        }
    }
}