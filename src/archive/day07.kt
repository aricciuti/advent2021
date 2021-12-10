import kotlin.math.abs

data class Crab(val position: Int)

fun day07_1(): Int {
    val crabs = linesInFile("resources/day07.txt")[0].split(",").map { Crab(it.toInt()) }
    val maxCrabPos = crabs.maxOf { it.position }
    return (0 .. maxCrabPos).map {it to crabs.sumOf { crab -> abs(crab.position - it) }}.minOf { it.second }
}

fun day07_2(): Int {
    val crabs = linesInFile("resources/day07.txt")[0].split(",").map { Crab(it.toInt()) }
    val maxCrabPos = crabs.maxOf { it.position }
    return (0 .. maxCrabPos).map {it to crabs.sumOf { crab -> val moves = abs(crab.position - it); moves * (moves + 1) / 2 }}.minOf { it.second }
}
