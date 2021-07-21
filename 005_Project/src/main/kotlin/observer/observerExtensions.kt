package observer

fun Observer.observe(subject: Subject) {
    if (this !in subject.observers) {
        subject.attachObserver(this)
    }
}

fun Observer.stopObserving(subject: Subject) {
    subject.detachObserver(this)
}