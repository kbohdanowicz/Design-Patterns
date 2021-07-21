@file:Suppress("ObjectPropertyName")

import observer.Observer
import observer.Subject

class Bank(maxDays: Int) : Subject(), Observer {

    private var _currentTax: Float = 0.2f

    val currentTax: Float
        get() = _currentTax

    private val dailyRevenueGoal: Float = 200f

    private var currentRevenue: Float = 0f

    var currentDay = 0
    val productsSoldOverTime = Array(maxDays) { 0 }
    val taxesOverTime = mutableListOf<Float>()
    val revenueOverTime = mutableListOf<Float>()

    private fun multiplyTax(multiplier: Float) {
        _currentTax *= multiplier
    }

    fun evaluateTaxes() {
        val ratio = currentRevenue / dailyRevenueGoal
        val taxMultiplier = when {
            ratio < 0.95f -> 1.05f
            ratio > 1.05f -> 0.95f
            else -> ratio
        }
        multiplyTax(taxMultiplier)
        taxesOverTime.add(currentTax)
        revenueOverTime.add(currentRevenue)
        notifyObservers(observers)
        currentRevenue = 0f
    }

    override fun update(subject: Subject) {
        if (subject is Vendor) {
            val soldProductTax = subject.currentPrice * currentTax
            currentRevenue += soldProductTax
            productsSoldOverTime[currentDay] += 1
        }
    }

    companion object {
        const val INIT_TAX: Float = 0.02f
    }
}