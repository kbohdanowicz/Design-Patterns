package enums

enum class Bonus(private val value: IntRange) {
    LOW(1..2),
    MEDIUM(3..4),
    HIGH(5..8);

    operator fun invoke() = value
}
