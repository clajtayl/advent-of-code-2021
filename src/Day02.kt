fun main() {
    fun part1(input: List<String>): Int {
        var depth = 0
        var horizontal = 0

        input.map { dataPoint -> dataPoint.split(" ").let { splitString -> Pair(splitString[0], splitString[1].toInt()) } }
            .forEach { pair ->
                when (pair.first) {
                    "forward" -> {
                        horizontal += pair.second
                    }
                    "up" -> {
                        depth -= pair.second
                    }
                    "down" -> {
                        depth += pair.second
                    }
                }
            }

        return depth.times(horizontal)
    }

    fun part2(input: List<String>): Int {
        var depth = 0
        var horizontal = 0
        var aim = 0

        input.map { dataPoint -> dataPoint.split(" ").let { splitString -> Pair(splitString[0], splitString[1].toInt()) } }
            .forEach { pair ->
                when (pair.first) {
                    "forward" -> {
                        horizontal += pair.second
                        depth += aim.times(pair.second)
                    }
                    "up" -> {
                        aim -= pair.second
                    }
                    "down" -> {
                        aim += pair.second
                    }
                }
            }

        return depth.times(horizontal)
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day02_test")
    check(part1(testInput) == 150)
    check(part2(testInput) == 900)

    val input = readInput("Day02")
    println(part1(input))
    println(part2(input))
}