fun main() {
    fun part1(input: List<String>): Int {
        val gamma = mutableListOf<String>()
        val epsilon = mutableListOf<String>()

        input.map {
            it.toCharArray().map { char ->
                char.toString()
            }
        }.fold(mutableListOf()) { x: MutableList<MutableList<String>>, y ->
            y.forEachIndexed { index, value ->
                if (x.size <= index) {
                    x.add(index, mutableListOf(value))
                } else {
                    x[index].add(value)
                }
            }
            x
        }.forEach {
            val zeroCount = it.count() { checkZero ->
                checkZero == "0"
            }
            val oneCount = it.count() { checkOne ->
                checkOne == "1"
            }
            if (zeroCount > oneCount) {
                gamma.add("0")
                epsilon.add("1")
            } else {
                gamma.add("1")
                epsilon.add("0")
            }
        }

        val gammaInt = Integer.parseInt(gamma.joinToString(""), 2)
        val epsilonInt = Integer.parseInt(epsilon.joinToString(""), 2)

        return gammaInt.times(epsilonInt)
    }

    fun part2(input: List<String>): Int {
        // TIL: tailrec optimizes recursive functions
        tailrec fun findCorrectNumber(input: List<String>, filter: (Int, Int) -> Boolean, position: Int = 0): List<String> {
            val ones = input.count { it[position].digitToInt() == 1 }
            val zeroes = input.size - ones
            val mostCommonOrOne = if (ones >= zeroes) 1 else 0
            val filtered = input.filter { filter(it[position].digitToInt(), mostCommonOrOne) }
            return if (filtered.size == 1) filtered else findCorrectNumber(filtered, filter, position + 1)
        }

        val oxygen = findCorrectNumber(input, { digit, mostCommonOrOne -> digit == mostCommonOrOne }).single().toInt(2)
        val co2 = findCorrectNumber(input, { digit, mostCommonOrOne -> digit != mostCommonOrOne }).single().toInt(2)
        return oxygen * co2
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day03_test")
    check(part1(testInput) == 198)
    check(part2(testInput) == 230)

    val input = readInput("Day03")
    println(part1(input))
    println(part2(input))
}