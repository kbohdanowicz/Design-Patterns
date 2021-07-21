package factoryMethod.pierogi

abstract class BoiledPierogi(stuffing: List<String>) : Pierogi(stuffing, "boiling")

class ClassicBoiledPierogi : BoiledPierogi(listOf("potato", "onion"))

class SweetPotatoBoiledPierogi : BoiledPierogi( listOf("sweet potato", "carrot"))

class StrawberryBoiledPierogi : BoiledPierogi(listOf("strawberry", "vanilla extract"))

class MeatBoiledPierogi : BoiledPierogi(listOf("meat", "onion"))

class CottageCheeseBoiledPierogi : BoiledPierogi(listOf("cottage cheese"))