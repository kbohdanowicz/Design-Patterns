package simpleFactory.pierogi

abstract class FriedPierogi(stuffing: List<String>) : Pierogi(stuffing, "frying")

class ClassicFriedPierogi : FriedPierogi(listOf("potato", "onion"))

class SweetPotatoFriedPierogi : FriedPierogi(listOf("sweet potato", "onion"))

class StrawberryFriedPierogi : FriedPierogi(listOf("strawberry", "vanilla extract"))

class MeatFriedPierogi : FriedPierogi(listOf("meat", "onion"))

class CottageCheeseFriedPierogi : FriedPierogi(listOf("cottage cheese"))
