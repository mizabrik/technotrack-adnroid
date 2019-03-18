package name.mizabrik.technotrackhomework

data class IntegerDigits(val digits: IntArray, val negative: Boolean = false)

private val smallNumbersRussian: Array<String> = arrayOf(
    "ноль", "один", "два", "три", "четыре", "пять", "шесть", "семь", "восемь", "девять", "десять",
    "одиннадцать", "двенадцать", "тринадцать", "четырнадцать", "пятнадцать", "шестнадцать",
    "семнадцать", "восемнадцать", "девятнадцать"
)

private fun String.joinWithRemainder(remainder: Int) =
    if (remainder == 0) this else this + " " + remainder.toRussian()

fun Int.toRussian(): String = when (this) {
    in 0..19 -> smallNumbersRussian[this]
    in 20..99 -> {
        val tens = this / 10
        val remainder = this % 10
        when (tens) {
            2 -> "двадцать"
            3 -> "тридцать"
            4 -> "сорок"
            9 -> "девяносто"
            else -> tens.toRussian() + "десят"
        }.joinWithRemainder(remainder)
    }
    in 100..999 -> {
        val hundreds = this / 100
        val remainder = this % 100
        when (hundreds) {
            1 -> "сто"
            2 -> "двести"
            3 -> "триста"
            4 -> "четыреста"
            else -> hundreds.toRussian() + "сот"
        }.joinWithRemainder(remainder)
    }
    1000 -> "тысяча"
    else -> throw NotImplementedError()
}