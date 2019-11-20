import java.time.LocalDate
import kotlin.math.roundToInt

const val kelvinToCelsius = 273.15
fun Int.toCelsius(): Int {
    return (this - kelvinToCelsius).roundToInt()
}

fun Int.toFahrenheit(): Int {
    return ((this * (9 / 5)) - 459.67).roundToInt()
}

class DateIterator(
    val startDate: LocalDate,
    val endDateInclusive: LocalDate,
    val stepDays: Long
) : Iterator<LocalDate> {
    private var currentDate = startDate

    override fun hasNext() = currentDate <= endDateInclusive

    override fun next(): LocalDate {
        val next = currentDate
        currentDate = currentDate.plusDays(stepDays)
        return next

    }

}

class DateProgression(
    override val start: LocalDate,
    override val endInclusive: LocalDate,
    val stepDays: Long = 1
) :
    Iterable<LocalDate>, ClosedRange<LocalDate> {

    override fun iterator(): Iterator<LocalDate> =
        DateIterator(start, endInclusive, stepDays)

    infix fun step(days: Long) = DateProgression(start, endInclusive, days)

}