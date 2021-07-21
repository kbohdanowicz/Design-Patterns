package factoryMethod.pierogi

abstract class BakedPierogi(stuffing: List<String>) : Pierogi(stuffing, "baking")

class ClassicBakedPierogi : BakedPierogi(listOf("potato", "onion"))

class SweetPotatoBakedPierogi : BakedPierogi(listOf("sweet potato", "onion"))

class StrawberryBakedPierogi : BakedPierogi(listOf("strawberry", "vanilla extract"))

class MeatBakedPierogi : BakedPierogi(listOf("meat", "onion"))

class CottageCheeseBakedPierogi : BakedPierogi(listOf("cottage cheese"))

