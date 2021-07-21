package observer

abstract class Subject {
    private val _observers = mutableListOf<Observer>()

    val observers: List<Observer>
        get() = _observers

    infix fun attachObserver(observer: Observer) {
        _observers.add(observer)
    }

    infix fun detachObserver(observer: Observer) {
        _observers.remove(observer)
    }

    fun notifyObservers(observers: List<Observer> = _observers) {
        observers.forEach {
            it.update(this)
        }
    }
}